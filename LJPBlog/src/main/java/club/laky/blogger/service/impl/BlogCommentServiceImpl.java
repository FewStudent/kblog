package club.laky.blogger.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club.laky.blogger.mapper.BlogCommentMapper;
import club.laky.blogger.service.BlogCommentService;
import club.laky.blogger.pojo.BlogComment;
import club.laky.blogger.util.IDConstructor;
import club.laky.blogger.util.PageHelper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class BlogCommentServiceImpl implements BlogCommentService {

	@Autowired
	BlogCommentMapper mapper;
	
	/**
	 * 发表一条评论
	 * */
	@Override
	public int insert(BlogComment blogComment) {
		if(null == blogComment.getCommentTime()|| "".equals(blogComment.getCommentTime())) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			blogComment.setCommentTime(dateFormat.format(Calendar.getInstance().getTime()).toString());
		}
		if(null == blogComment.getCommentState()) {
			blogComment.setCommentState(1);
		}
		return mapper.insert(blogComment);
	}

	/**
	 * 修改一条评论
	 * */
	@Override
	public int update(BlogComment blogComment) {
		return mapper.updateByPrimaryKeySelective(blogComment);
	}

	/**
	 * 删除一条评论 一般不适用
	 * 一般修改评论的状态即可
	 * */
	@Override
	public int delete(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	/** 查找与文章有关的所有评论 */
	@Override
	public PageHelper<BlogComment> queryByArticlesId(Integer id, Integer page, Integer limit) {
		Example example = new Example(BlogComment.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("commentBlogId", id);
		criteria.andEqualTo("commentState",1);
		
		int count = mapper.selectCountByExample(example);
		RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
		List<BlogComment> blogComments = mapper.selectByExampleAndRowBounds(example,rowBounds);
		return new PageHelper<>(count,page,limit,blogComments);
	}

	/** 根据Id查找一条评论 */
	@Override
	public BlogComment queryById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据文章的id获得评论数目
	 * */
	@Override
	public int queryCountByBlogId(Integer id) {
		Example example = new Example(BlogComment.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("commentBlogId",id);
		criteria.andEqualTo("commentState",1);
		return mapper.selectCountByExample(example);
	}

	/**	根据条件模糊查询评论
	 * @return	返回评论的集合
	 * @param	content		评论内容
	 * @param	state		评论的状态
	 * */
	@Override
	public PageHelper<BlogComment> queryByContent(String content, Integer state,Integer page,Integer limit) {
		List<BlogComment> comments;
		if(page != null && limit != null) {
			comments = mapper.queryAll(content, state, (page-1)*limit, limit);
		}else {
			comments = mapper.queryAll(content, state, null, null);
		}
		int count = mapper.queryCountAll(content, state);
		return new PageHelper<>(count,page,limit,comments);
	}

	/**
	 * 查找所有的评论
	 * */
	@Override
	public List<BlogComment> queryAll() {
		return mapper.selectAll();
	}

	/**
	 * @return 返回我评论的所有集合
	 * @param id 获取我的id
	 * */
	@Override
	public PageHelper<BlogComment> queryMyComment(Integer id,Integer page,Integer limit) {
		Example example = new Example(BlogComment.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("commentObserverId",id);
		criteria.andEqualTo("commentState",1);
		
		int count = mapper.selectCountByExample(example);
		RowBounds bounds = new RowBounds((page - 1) * limit, limit);
		List<BlogComment> list = mapper.selectByExampleAndRowBounds(example, bounds);
		
		return new PageHelper<>(count,page,limit,list);
	}

}
