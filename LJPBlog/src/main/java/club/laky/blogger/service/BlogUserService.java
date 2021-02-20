package club.laky.blogger.service;

import club.laky.blogger.pojo.BlogUser;
import club.laky.blogger.util.PageHelper;

public interface BlogUserService {

	/** 用户登陆 */
	public int login(BlogUser user);
	
	/** 注册验证 */
	public int regist(BlogUser user);
	
	/** 添加用户 */
	public int insert(BlogUser user);
	
	/** 删除用户 */
	public int delete(Integer id);
	
	/** 修改用户信息 */
	public int update(BlogUser user);
	
	/** 根据ID查找用户*/
	public BlogUser queryById(Integer id);
	
	/** 根据账号查找用户 */
	public BlogUser searchByuserName(String userName);
	
	/** 查找所有的用户 */
	public PageHelper<BlogUser> queryAll(Integer page,Integer pageLimit);
	
	/** 根据用户别名查找用户	模糊查询 */
	public PageHelper<BlogUser> queryByAnotherName(String content,Integer state,Integer power,Integer page,Integer pageLimit);
	
	/** 根据用户id查询用户的评论*/
	public BlogUser queryMyCommentsById(Integer id,Integer page,Integer pageLimit);
	
	/** 根据用户id查询用户的文章 */
	public BlogUser queryMyArticlesById(Integer id,Integer page,Integer pageLimit);

}
