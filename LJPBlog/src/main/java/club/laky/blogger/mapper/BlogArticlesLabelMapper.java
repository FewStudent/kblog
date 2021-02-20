package club.laky.blogger.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import club.laky.blogger.pojo.BlogArticlesLabel;
import tk.mybatis.mapper.common.Mapper;

public interface BlogArticlesLabelMapper extends Mapper<BlogArticlesLabel> {

	/**
	 * 查询所有文章标签的具体内容
	 * 
	 * @return 返回的结果集
	 * @param title 模糊查询的标题
	 * @param label 模糊查询的标签
	 * @param state 查询的状态
	 * @param first 数据库limit的起始页
	 * @param limit 数据库limit的数量
	 *
	 */
	public List<BlogArticlesLabel> queryAll(@Param("title") String title, @Param("label") String label,
			@Param("state") Integer state, @Param("first") Integer first, @Param("limit") Integer limit);

	/**
	 * @return 返回的数量
	 * @param title 模糊查询的标题
	 * @param label 模糊查询的标签
	 * @param state 查询的状态
	 */
	public int queryAllCount(@Param("title") String title, @Param("label") String label, @Param("state") Integer state);

	/**
	 * 动态更新
	 * 
	 * @return 返回影响条数
	 */
	public int update(@Param("state") Integer state, @Param("id") Integer id);

}