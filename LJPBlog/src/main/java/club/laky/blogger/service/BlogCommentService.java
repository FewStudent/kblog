package club.laky.blogger.service;

import java.util.List;

import club.laky.blogger.pojo.BlogComment;
import club.laky.blogger.util.PageHelper;

public interface BlogCommentService {
	
	/** 添加一条评论 */
	public int insert(BlogComment blogComment);
	
	/** 修改一条评论 */
	public int update(BlogComment blogComment);
	
	/** 删除一条评论 */
	public int delete(Integer id);
	
	/** 根据id获得一条评论 */
	public BlogComment queryById(Integer id);
	
	/** 根据文章id获得评论数目 */
	public int queryCountByBlogId(Integer id);
	
	/** 根据条件模糊查询 */
	public PageHelper<BlogComment> queryByContent(String content,Integer state,Integer page,Integer limit);
	
	/** 查询所有的评论 */
	public List<BlogComment> queryAll();
	
	/** 查找我发表的评论 */
	public PageHelper<BlogComment> queryMyComment(Integer id,Integer page,Integer limit);
	
	/** 获得和文章有关的评论 */
	public PageHelper<BlogComment> queryByArticlesId(Integer id,Integer page,Integer limit);
}
