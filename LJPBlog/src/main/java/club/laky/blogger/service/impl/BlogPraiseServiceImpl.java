package club.laky.blogger.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club.laky.blogger.mapper.BlogPraiseMapper;
import club.laky.blogger.service.BlogPraiseService;
import club.laky.blogger.pojo.BlogPraise;
import club.laky.blogger.util.IDConstructor;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class BlogPraiseServiceImpl implements BlogPraiseService {

	@Autowired
	BlogPraiseMapper mapper;
	
	/** 添加一个赞 */
	@Override
	public int insert(BlogPraise blogPraise) {
		if(null == blogPraise.getPraiseDate() || "".equals(blogPraise.getPraiseDate())) {
			blogPraise.setPraiseDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime()).toString());
		}
		return mapper.insert(blogPraise);
	}

	/** 修改一个赞的信息 */
	@Override
	public int update(BlogPraise blogPraise) {
		return mapper.updateByPrimaryKey(blogPraise);
	}

	/** 根据文章id获得文章赞的数量 */
	@Override
	public int queryCountByArticlesId(Integer articlesId) {
		Example example = new Example(BlogPraise.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("praiseArticlesId",articlesId);
		criteria.andEqualTo("praiseFlag",1);
		return mapper.selectCountByExample(example);
	}

	/** 根据id获得一个赞的信息 */
	@Override
	public BlogPraise queryById(Integer articlesId, Integer userId) {
		Example example = new Example(BlogPraise.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("praiseUserId",userId);
		criteria.andEqualTo("praiseArticlesId",articlesId);
		return mapper.selectOneByExample(example);
	}

}
