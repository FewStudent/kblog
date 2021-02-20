package club.laky.blogger.service;

import club.laky.blogger.pojo.BlogPraise;

public interface BlogPraiseService {
	
	/** 添加一个赞 */
	public int insert(BlogPraise blogPraise);
	
	/** 修改一个赞的信息 */
	public int update(BlogPraise blogPraise);
	
	/** 获得一篇文章赞的数量 */
	public int queryCountByArticlesId(Integer articlesId);
	
	/** 根据文章id和用户id获得一个赞 */
	public BlogPraise queryById(Integer articlesId, Integer userId);
	
}
