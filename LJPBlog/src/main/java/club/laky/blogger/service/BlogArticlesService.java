package club.laky.blogger.service;

import club.laky.blogger.pojo.BlogArticles;
import club.laky.blogger.util.PageHelper;

public interface BlogArticlesService {
	
	/** 发表一篇文章 */
	public int insert(BlogArticles articles) ;
	
	/** 修改一篇文章状态 */
	public int update(BlogArticles articles);
	
	/** 完全修改一篇文章 */
	public int updateAll(BlogArticles articles);
	
	/** 删除一篇文章 */
	public int delete(Integer id);
	
	/** 获得一个需要修改的文章 */
	public BlogArticles queryOneToUpdate(Integer id);
	
	/** 根据id查找一篇文章 */
	public BlogArticles queryById(Integer id);
	
	/** 查找所有的文章*/
	public PageHelper<BlogArticles> queryAll();
	
	/** 根据内容查找文章 */
	public PageHelper<BlogArticles> queryByContent(String content,String artType,Integer type, Integer state,Integer page,Integer pagelimit);
	
	/**  获得文章数量 */
	public Integer queryCount();
	
	/** 根据标签名查询文章 */
	public PageHelper<BlogArticles> queryByLabelName(String labelName);
	
	/** 不带标签的根据内容查找文章  */
	public PageHelper<BlogArticles> queryByCotentNotLabel(String content,String artType,Integer type, Integer state,Integer page,Integer pagelimit);
	
}
