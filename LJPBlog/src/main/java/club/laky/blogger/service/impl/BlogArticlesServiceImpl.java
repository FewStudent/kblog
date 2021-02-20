package club.laky.blogger.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club.laky.blogger.mapper.BlogArticlesMapper;
import club.laky.blogger.service.BlogArticlesService;
import club.laky.blogger.pojo.BlogArticles;
import club.laky.blogger.util.IDConstructor;
import club.laky.blogger.util.PageHelper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class BlogArticlesServiceImpl implements BlogArticlesService {

    @Autowired
    BlogArticlesMapper mapper;

    /**
     * 添加一篇文章
     */
    @Override
	public int insert(BlogArticles articles) {
        if (null == articles.getArticlesCreateDate() || "".equals(articles.getArticlesCreateDate())) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            articles.setArticlesCreateDate(dateFormat.format(calendar.getTime()).toString());
        }
        articles.setArticlesViewCount(0);
        articles.setArticlesPraiseCount(0);
        articles.setArticlesCommentCount(0);

        if (articles.getArticlesImgUrl().length() < 11) {
            articles.setArticlesImgUrl("");
        }

        /* 发表时文章选项未用 设为默认 文章可见、不置顶、可评论 */
        if (null == articles.getArticlesState()) {
            articles.setArticlesState(1);
        }
        if (null == articles.getArticlesIsstick()) {
            articles.setArticlesIsstick(0);
        }
        if (null == articles.getArticlesCommentable()) {
            articles.setArticlesCommentable(1);
        }
        return mapper.insert(articles);
    }

    /**
     * 修改一篇文章
     */
    @Override
    public int update(BlogArticles articles) {
        return mapper.update(articles.getArticlesState(), articles.getArticlesId());
    }

    /**  */

    /**
     * 删除一篇文章 一般不用
     */
    @Override
    public int delete(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id查找一篇文章
     */
    @Override
    public BlogArticles queryById(Integer id) {
        return mapper.queryAllInfoByArticlesId(id);
    }

    /**
     * 获得所有的文章
     */
    @Override
    public PageHelper<BlogArticles> queryAll() {
        List<BlogArticles> lists = mapper.selectAll();
        int count = lists.size();
        return new PageHelper<>(count, 0, 0, lists);
    }

    /**
     * 直接搜索得到的文章
     *
     * @param content 查询内容
     * @param artType 文章的类型
     * @param type    排序类型 1、点击量 2、点赞量 3、评论数
     * @param state   文章状态
     */
    @Override
	public PageHelper<BlogArticles> queryByContent(String content, String artType, Integer type, Integer state,
												   Integer page, Integer pagelimit) {
        List<BlogArticles> articles;
        if (page != null && pagelimit != null) {
            articles = mapper.queryAll(content, artType, type, state, (page - 1) * pagelimit, pagelimit);
        } else {
            articles = mapper.queryAll(content, artType, type, state, null, null);
            page = pagelimit = 0;
        }
        return new PageHelper<BlogArticles>(articles.size(), page, pagelimit, articles);
    }

    @Override
    public Integer queryCount() {
        Example example = new Example(BlogArticles.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("articlesState", 1);
        return mapper.selectCountByExample(example);
    }

    @Override
    public PageHelper<BlogArticles> queryByLabelName(String labelName) {
        List<BlogArticles> blogArticles = mapper.queryByLabel(labelName, null, null);
        return new PageHelper<>(blogArticles.size(), 0, 0, blogArticles);
    }

    @Override
    public int updateAll(BlogArticles articles) {
        return mapper.updateByPrimaryKeySelective(articles);
    }

    @Override
    public PageHelper<BlogArticles> queryByCotentNotLabel(String content, String artType, Integer type, Integer state,
                                                          Integer page, Integer pagelimit) {
        List<BlogArticles> articles;
        if (page != null && pagelimit != null) {
            articles = mapper.queryAllNotLable(content, artType, type, state, (page - 1) * pagelimit, pagelimit);
        } else {
            articles = mapper.queryAllNotLable(content, artType, type, state, null, null);
            page = pagelimit = 0;
        }
        return new PageHelper<BlogArticles>(articles.size(), page, pagelimit, articles);
    }

    @Override
    public BlogArticles queryOneToUpdate(Integer id) {
        return mapper.selectOneToUpdate(id);
    }

}
