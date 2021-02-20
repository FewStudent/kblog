package club.laky.blogger.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club.laky.blogger.mapper.BlogUserMapper;
import club.laky.blogger.service.BlogUserService;
import club.laky.blogger.pojo.BlogUser;
import club.laky.blogger.util.MD5Utils;
import club.laky.blogger.util.PageHelper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class BlogUserServiceImpl implements BlogUserService {

    @Autowired
    BlogUserMapper mapper;

    /**
     * 登陆服务 下面为返回码 0：输入账号或者密码为空 1：账号密码正确 2：用户不存在 3：用户冻结 4：密码错误
     */
    @Override
    public int login(BlogUser user) {
        int result = 0;
        String password = user.getUserPassword();
        String userName = user.getUserName();

        if (null == password || "".equals(password) || null == userName || "".equals(userName)) {
            /* 用户名或密码为空 */
            return 0;
        }

        Example example = new Example(BlogUser.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", userName);
        BlogUser temp = mapper.selectOneByExample(example);

        if (temp == null) {
            /* 该用户不存在 */
            result = 2;
        } else if (temp.getUserState() == 0) {
            /* 用户冻结 */
            result = 3;
        } else if (!password.equals(temp.getUserPassword())) {
            /* 密码错误 */
            result = 4;
        } else {
            /* 密码正确 */
            result = 1;
        }
        return result;
    }

    /**
     * 添加一个博客用户 用户注册后会自动补齐一些信息
     */
    @Override
    public int insert(BlogUser user) {
        /* 密码MD5加密 */
        if (user.getUserPassword().length() < 32) {
            user.setUserPassword(MD5Utils.getMD5(user.getUserPassword()));
        }
        /* 用户状态设置 */
        if (null == user.getUserState()) {
            user.setUserState(1);
        }
        /* 用户权限设置 */
        if (null == user.getUserPower()) {
            user.setUserPower(2);
        }
        /* 用户的创建时间 */
        if (null == user.getUserCreatDate() || "".equals(user.getUserCreatDate())) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            user.setUserCreatDate(dateFormat.format(calendar.getTime()).toString());
        }
        /* 用户等级初始化 */
        if (null == user.getUserLevel()) {
            user.setUserLevel(0);
            user.setUserExperience(0);
            user.setUserAnotherName("[我还没有名字]");
        }
        return mapper.insert(user);
    }

    /**
     * 删除一个博客用户 一般不使用 一般只屏蔽
     */
    @Override
    public int delete(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改一个博客用户信息
     */
    @Override
    public int update(BlogUser user) {
        return mapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 查找所有博客用户
     */
    @Override
    public PageHelper<BlogUser> queryAll(Integer page, Integer pageLimit) {
        List<BlogUser> users = mapper.selectAll();
        return new PageHelper<>(users.size(), 0, 0, users);
    }

    /**
     * 根据id查询一个用户
     */
    @Override
    public BlogUser queryById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 用户注册验证 下面是返回码 0：用户名重复 1：可用用户
     */
    @Override
    public int regist(BlogUser user) {
        String userName = user.getUserName();
        Example example = new Example(BlogUser.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", userName);
        BlogUser temp = mapper.selectOneByExample(example);
        System.out.println(temp);
        if (temp == null) {
            /* 用户名可用 */
            return 1;
        } else {
            /* 用户名重复 */
            return 0;
        }

    }

    /**
     * 根据用户名查找用户
     */
    @Override
    public BlogUser searchByuserName(String userName) {
        Example example = new Example(BlogUser.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", userName);
        return mapper.selectOneByExample(example);
    }

    /**
     * 根据条件查询
     *
     * @param content 用户别名
     * @param state   用户的状态
     * @param power   用户的权限
     * @return 返回用户集合
     */
    @Override
    public PageHelper<BlogUser> queryByAnotherName(String content, Integer state, Integer power, Integer page, Integer pageLimit) {
        Example example = new Example(BlogUser.class);
        Criteria criteria = example.createCriteria();

        if (content != null && !"".equals(content)) {
            criteria.andLike("userAnotherName", "%" + content + "%");
        }
        if (state != null) {
            criteria.andEqualTo("userState", state);
        }
        if (power != null) {
            criteria.andEqualTo("userPower", power);
        }
        int count = mapper.selectCountByExample(example);
        RowBounds bounds = new RowBounds((page - 1) * pageLimit, pageLimit);
        List<BlogUser> users = mapper.selectByExampleAndRowBounds(example, bounds);
        return new PageHelper<>(count, page, pageLimit, users);
    }

    @Override
    public BlogUser queryMyCommentsById(Integer id, Integer page, Integer pageLimit) {
        BlogUser user;
        if (page != null && pageLimit != null) {
            user = mapper.queryCommentsByUserId(id, (page - 1) * pageLimit, pageLimit);
        } else {
            user = mapper.queryCommentsByUserId(id, null, null);
        }
        return user;
    }

    @Override
    public BlogUser queryMyArticlesById(Integer id, Integer page, Integer pageLimit) {
        BlogUser user;
        if (page != null && pageLimit != null) {
            user = mapper.queryArticlesByUserId(id, (page - 1) * pageLimit, pageLimit);
        } else {
            user = mapper.queryArticlesByUserId(id, null, null);
        }
        return user;
    }

}
