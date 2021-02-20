package club.laky.blogger.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club.laky.blogger.mapper.BlogLabelMapper;
import club.laky.blogger.service.BlogLabelService;
import club.laky.blogger.pojo.BlogLabel;
import club.laky.blogger.util.PageHelper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class BlogLabelServiceImpl implements BlogLabelService{
	
	@Autowired
	BlogLabelMapper mapper;

	/** 添加一种标签 */
	@Override
	public int insert(BlogLabel blogLabel) {
		if(queryByName(blogLabel.getLabelName()) != null) {
			return -1;
		}
		return mapper.insert(blogLabel);
	}

	/** 删除一种标签 */
	@Override
	public int delete(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	/** 修改一种标签 */
	@Override
	public int update(BlogLabel blogLabel) {
		return mapper.updateByPrimaryKeySelective(blogLabel);
	}

	/** 根据id获得一种标签*/
	@Override
	public BlogLabel queryById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	/** 获得所有的标签 */
	@Override
	public PageHelper<BlogLabel> queryAll() {
		Example example = new Example(BlogLabel.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("labelState",1);
		List<BlogLabel> labels = mapper.selectByExample(example);
		return new PageHelper<>(labels.size(),0,0,labels);
	}

	/** 获得点击量多的标签 */
	@Override
	public List<BlogLabel> qeuryByPickCount() {
		Example example = new Example(BlogLabel.class);
		example.setOrderByClause("label_pick_count DESC");
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("labelState",1);
		RowBounds bounds = new RowBounds(1, 5);
		return mapper.selectByExampleAndRowBounds(example, bounds);
	}

	/** 模糊查询 */
	@Override
	public PageHelper<BlogLabel> queryByLikeName(Integer state,String likeName,Integer page,Integer pagelimit){
		Example example = new Example(BlogLabel.class);
		Criteria criteria = example.createCriteria();
		List<BlogLabel> lists;
		if(null != state) {
			criteria.andEqualTo("labelState",state);
		}
		if(!"".equals(likeName) && null != likeName) {
			criteria.andLike("labelName", "%" + likeName + "%");
		}
		if (page != null || pagelimit != null) {
			RowBounds bounds = new RowBounds((page - 1)*pagelimit, pagelimit);
			lists = mapper.selectByExampleAndRowBounds(example, bounds);
		}else {
			lists = mapper.selectByExample(example);
		}
		int totalCount = mapper.selectCountByExample(example);
		return new PageHelper<>(totalCount,page,pagelimit,lists);
	}

	@Override
	public BlogLabel queryByName(String name) {
		Example example = new Example(BlogLabel.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("labelName",name);
		return mapper.selectOneByExample(example);
	}

	@Override
	public int updateQuickCount(String labelName) {
		return mapper.updateQuickCount(labelName);
	}
	
}
