package club.laky.blogger.mapper;

import org.apache.ibatis.annotations.Param;

import club.laky.blogger.pojo.BlogUser;
import tk.mybatis.mapper.common.Mapper;

public interface BlogUserMapper extends Mapper<BlogUser> {
	
	/** 携带用户评论数据的用户信息,
	 * 如果有first和limit参数则进行分页，
	 * 否则就全部查询  */
	public BlogUser queryCommentsByUserId(@Param("userId")Integer id,@Param("first")Integer first,@Param("limit")Integer limit);
	
	/** 携带用户文章数据的用户信息,
	 * 如果有first和limit参数则进行分页，
	 * 否则就全部查询  */
	public BlogUser queryArticlesByUserId(@Param("userId")Integer id,@Param("first")Integer first,@Param("limit")Integer limit);
}