package club.laky.blogger.service;

import java.util.List;

import club.laky.blogger.pojo.BlogArticlesLabel;
import club.laky.blogger.util.PageHelper;

public interface BlogArticlesLabelService {

	/** 添加一个文章标签 */
	public int insertTx(BlogArticlesLabel articlesLabel);

	/** 删除一个文章标签 */
	public int deleteTx(Integer id);

	/** 修改一个文章标签 */
	public int updateTx(Integer state,Integer id);

	/** 查找一篇文章的所有标签：根据文章id */
	public BlogArticlesLabel queryByIdTx(Integer id);

	/** 查找一篇文章的所有标签：根据文章id */
	public List<BlogArticlesLabel> queryByArticlesIdTx(Integer articlesId);

	/** 查找一篇文章的所有标签：根据文章标题 */
	public PageHelper<BlogArticlesLabel> queryByAticlesTitleTx(String title, Integer state, Integer page,
			Integer pageLimit);

	/**
	 * 查询所有文章标签的具体内容
	 *
	 * @return 返回的结果集
	 * @param title 模糊查询的标题
	 * @param label 模糊查询的标签
	 * @param page 数据库limit的起始页
	 * @param limit 数据库limit的数量
	 */
	public PageHelper<BlogArticlesLabel> queryAllTx(String title, String label,Integer state, Integer page, Integer limit);

	/** 查重 */
	public BlogArticlesLabel queryByAIdAndLId(Integer articlesId,Integer labelId);

	/** 删除一篇文章的所有标签 */
	public int deleteByBlogId(Integer id);
}
