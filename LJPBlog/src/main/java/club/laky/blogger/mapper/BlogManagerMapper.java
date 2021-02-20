package club.laky.blogger.mapper;

import club.laky.blogger.pojo.BlogManager;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BlogManagerMapper extends Mapper<BlogManager> {

    /** 根据查询获得的管理员的数据 */
    public List<BlogManager> getByFiled(@Param("state") Integer state,
                                        @Param("power") Integer power, @Param("name") String name,
                                        @Param("first") Integer first, @Param("limit") Integer limit);

    /** 根据条件查询获得的管理员数量 */
    public int getCountByFiled(@Param("state") Integer state,
                               @Param("power") Integer power, @Param("name") String name,
                               @Param("first") Integer first, @Param("limit") Integer limit);
}