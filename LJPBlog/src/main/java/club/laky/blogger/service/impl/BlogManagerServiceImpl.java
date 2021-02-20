package club.laky.blogger.service.impl;

import club.laky.blogger.mapper.BlogManagerMapper;
import club.laky.blogger.pojo.BlogManager;
import club.laky.blogger.service.BlogManagerService;
import club.laky.blogger.util.CommonResponse;
import club.laky.blogger.util.MD5Utils;
import club.laky.blogger.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class BlogManagerServiceImpl implements BlogManagerService {
    @Autowired
    BlogManagerMapper mapper;

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(BlogManager manager) {
        if ("".equals(manager.getPassword())) {
            manager.setPassword(null);
        }
        if (manager.getPassword() != null) {
            manager.setPassword(MD5Utils.getMD5(manager.getPassword()));
        }
        return mapper.updateByPrimaryKeySelective(manager);
    }

    @Override
    public int insert(BlogManager manager) {
        if(queryOneByAccount(manager.getAccount()) != null){
            return -1;
        }
        manager.setPassword(MD5Utils.getMD5(manager.getPassword()));
        return mapper.insertSelective(manager);
    }

    @Override
    public BlogManager queryOneById(Integer id) {
        Example example = new Example(BlogManager.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        return mapper.selectOneByExample(example);
    }

    @Override
    public PageHelper<BlogManager> queryByFiled(String nickName, Integer power, Integer state, Integer page, Integer limit) {
        PageHelper pageHelper = new PageHelper();
        if (page == null || limit == null) {
            pageHelper.setData(mapper.getByFiled(state, power, nickName, null, null));
        } else {
            pageHelper.setData(mapper.getByFiled(state, power, nickName, (page - 1) * limit, limit));
        }
        pageHelper.setTotolCount(mapper.getCountByFiled(state, power, nickName, null, null));
        return pageHelper;
    }

    @Override
    public CommonResponse verify(BlogManager manager) {
        BlogManager temp = null;
        manager.setPassword(MD5Utils.getMD5(manager.getPassword()));
        temp = queryOneByAccount(manager.getAccount());

        if (temp == null) {
            //用户不存在
            return CommonResponse.error("用户不存在");
        } else if (!temp.getPassword().equals(manager.getPassword())) {
            //密码错误
            return CommonResponse.error("密码错误");
        } else if (temp.getState() == 2) {
            //冻结
            return CommonResponse.error("账号冻结");
        }

        return CommonResponse.isOk(temp);
    }

    @Override
    public BlogManager queryOneByAccount(String account) {
        Example example = new Example(BlogManager.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("account", account);
        return mapper.selectOneByExample(example);
    }
}
