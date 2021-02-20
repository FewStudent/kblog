package club.laky.blogger.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club.laky.blogger.mapper.BlogArticlesLabelMapper;
import club.laky.blogger.pojo.BlogArticlesLabel;
import club.laky.blogger.service.BlogArticlesLabelService;
import club.laky.blogger.util.PageHelper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class BlogArticlesLabelServiceImpl implements BlogArticlesLabelService
{

	@Autowired
	BlogArticlesLabelMapper mapper;

	@Override
	public int insertTx(BlogArticlesLabel articlesLabel) {
		articlesLabel.setBalState(1);
		int result = mapper.insertSelective(articlesLabel);
		return result;
	}

	@Override
	public int deleteTx(Integer id) {
		int result = mapper.deleteByPrimaryKey(id);
		return result;
	}

	@Override
	public int updateTx(Integer state, Integer id) {
		int result = mapper.update(state, id);
		return result;
	}

	@Override
	public List<BlogArticlesLabel> queryByArticlesIdTx(Integer articlesId) {
		Example example = new Example(BlogArticlesLabel.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("articlesId", articlesId);
		criteria.andEqualTo("labelState", 1);
		return mapper.selectByExample(example);
	}

	@Override
	public PageHelper<BlogArticlesLabel> queryByAticlesTitleTx(String title, Integer state, Integer page,
			Integer pageLimit) {
		Example example = new Example(BlogArticlesLabel.class);
		Criteria criteria = example.createCriteria();
		if (title != null && !"".equals(title)) {
			criteria.andLike("articlesTitle", "%" + title + "%");
		}
		if (state != null) {
			criteria.andEqualTo("labelState", state);
		}
		int count = mapper.selectCountByExample(example);
		List<BlogArticlesLabel> lists = mapper.selectByExample(example);
		return new PageHelper<>(count, page, pageLimit, lists);
	}

	@Override
	public BlogArticlesLabel queryByIdTx(Integer id) {
		Example example = new Example(BlogArticlesLabel.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("blogLAId", id);
		return mapper.selectOneByExample(example);
	}

	@Override
	public PageHelper<BlogArticlesLabel> queryAllTx(String title, String label, Integer state, Integer page,
			Integer limit) {
		List<BlogArticlesLabel> lists = mapper.queryAll(title, label, state, (page - 1) * limit, limit);
		int count = mapper.queryAllCount(title, label, state);
		return new PageHelper<>(count, page, limit, lists);
	}

	@Override
	public BlogArticlesLabel queryByAIdAndLId(Integer articlesId, Integer labelId) {
		Example example = new Example(BlogArticlesLabel.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("articlesId",articlesId);
		criteria.andEqualTo("labelId",labelId);
		return mapper.selectOneByExample(example);
	}

	@Override
	public int deleteByBlogId(Integer id) {
		Example example = new Example(BlogArticlesLabel.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("articlesId",id);
		return mapper.deleteByExample(example);
	}

}
