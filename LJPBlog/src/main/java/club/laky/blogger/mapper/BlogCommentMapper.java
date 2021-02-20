package club.laky.blogger.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import club.laky.blogger.pojo.BlogComment;
import tk.mybatis.mapper.common.Mapper;

public interface BlogCommentMapper extends Mapper<BlogComment> {

    /**
     * 查询一条评论的所有信息,包括评论的文字、评论者信息
     */
    public BlogComment queryAllInfoById(Integer id);

    /**
     * 查询所有的评论信息
     *
     * @param content 模糊查询的内容,包括可以是评论者、文章标题、评论内容
     * @param state   评论的状态
     * @param first   数据库limit的起始位
     * @param limit   数据库limit的数量
     * @return 返回结果集
     */
    public List<BlogComment> queryAll(@Param("content") String content, @Param("state") Integer state,
                                      @Param("first") Integer first, @Param("limit") Integer limit);

    /**
     * 查询所有的评论信息
     *
     * @param content 模糊查询的内容,包括可以是评论者、文章标题、评论内容
     * @param state   评论的状态
     * @return 返回结果集
     */
    public int queryCountAll(@Param("content") String content, @Param("state") Integer state);

}