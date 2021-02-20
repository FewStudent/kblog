package club.laky.blogger.service;

import club.laky.blogger.pojo.BlogManager;
import club.laky.blogger.util.CommonResponse;
import club.laky.blogger.util.PageHelper;

public interface BlogManagerService {

    /**
     * 根据id删除一条记录
     */
    public int deleteById(Integer id);

    /**
     * 根据id修改一条记录
     */
    public int updateById(BlogManager manager);

    /**
     * 选择性添加一条记录
     */
    public int insert(BlogManager manager);

    /**
     * 根据id获取一条记录
     */
    public BlogManager queryOneById(Integer id);

    /**
     * 根据查询条件获取管理员信息
     * */
    public PageHelper<BlogManager> queryByFiled(String nickName,Integer power,Integer state,Integer page,Integer limit);

    /**
     * 登陆验证
     * */
    public CommonResponse verify(BlogManager manager);


    /**
     * 根据账号查询一个管理员
     * */
    public BlogManager queryOneByAccount(String account);
}
