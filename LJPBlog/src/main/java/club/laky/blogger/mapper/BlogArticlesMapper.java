package club.laky.blogger.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import club.laky.blogger.pojo.BlogArticles;
import tk.mybatis.mapper.common.Mapper;

public interface BlogArticlesMapper extends Mapper<BlogArticles> {
    /**
     * 携带评论的文章信息
     */
    public BlogArticles queryArticlesAndCommentsById(Integer id);

    /**
     * 携带评论、标签、作者信息的文章信息
     */
    public BlogArticles queryAllInfoByArticlesId(Integer id);

    /**
     * 携带作者信息的
     */
    public BlogArticles queryArticlesAndUserById(Integer id);

    /**
     * 携带作者信息的所有文章，分页
     *
     * @param content 模糊查询的内容
     * @param artType 文章的类型
     * @param type    排序的类型：1、点击量;2、点赞量;3、评论数;
     * @param state   文章的状态：0、在审核;1、正常;2、屏蔽
     * @param first   数据库limit的起始位
     * @param limit   数据库limit的数量
     * @return 查询的结果集合
     */
    public List<BlogArticles> queryAll(@Param("content") String content, @Param("artType") String artType,
                                       @Param("type") Integer type, @Param("state") Integer state, @Param("first") Integer first,
                                       @Param("limit") Integer limit);

    /**
     * 携带作者信息的所有文章，分页
     *
     * @param content 模糊查询的内容
     * @param artType 文章的类型
     * @param type    排序的类型：1、点击量;2、点赞量;3、评论数;
     * @param state   文章的状态：0、在审核;1、正常;2、屏蔽
     * @param first   数据库limit的起始位
     * @param limit   数据库limit的数量
     * @return 查询的结果集合
     */
    public List<BlogArticles> queryAllNotLable(@Param("content") String content, @Param("artType") String artType,
                                               @Param("type") Integer type, @Param("state") Integer state, @Param("first") Integer first,
                                               @Param("limit") Integer limit);

    /**
     * 查询数量
     *
     * @param content 模糊查询的内容
     * @param artType 文章的类型
     * @param state   文章的状态：0、在审核;1、正常;2、屏蔽
     * @return 查询的结果集合
     **/
    public int queryCountAll(@Param("content") String content, @Param("artType") String artType,
                             @Param("state") Integer state);

    /**
     * 按照标签查找文章
     */
    public List<BlogArticles> queryByLabel(@Param("label") String label, @Param("first") Integer first,
                                           @Param("limit") Integer limit);

    /**
     * 修改文章的状态
     *
     * @param state 文章的状态
     * @param id    文章的id
     * @return 影响数据库条数
     */
    public int update(@Param("state") Integer state, @Param("id") Integer id);


    /**
     * 获取一条需要修改的数据
     *
     * @param id
     * @return 返回修改修改的文章
     */
    public BlogArticles selectOneToUpdate(@Param("id") Integer id);
}