package club.laky.blogger.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import club.laky.blogger.service.BlogArticlesLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import club.laky.blogger.service.BlogArticlesService;
import club.laky.blogger.service.BlogLabelService;
import club.laky.blogger.service.BlogPraiseService;
import club.laky.blogger.util.CommonResponse;
import club.laky.blogger.util.PageHelper;
import club.laky.blogger.pojo.BlogArticles;
import club.laky.blogger.pojo.BlogUser;

@Controller
@RequestMapping("/articles")
public class BlogArticlesController {

    @Autowired
    BlogArticlesService service;
    @Autowired
    BlogPraiseService praiseService;
    @Autowired
    BlogLabelService labelService;
    @Autowired
    BlogArticlesLabelService balService;

    /**
     * 添加文章的请求
     *
     * @param articles：添加的文章实体
     * @return 返回数据库修改条数
     */
    @RequestMapping("insert")
    @ResponseBody
    public CommonResponse insert(@RequestBody BlogArticles articles) {
        /* 将文章内容的换行 转换为WEB端能识别的存放到数据库 */
        articles.setArticlesContent(articles.getArticlesContent().replaceAll("\n", "<br>"));
        int result = service.insert(articles);
        if (result == 0) {
            return CommonResponse.error("添加失败");
        }
        return CommonResponse.isOk(result);
    }


    @RequestMapping("toUpdate")
    public ModelAndView uoUpdate(HttpServletRequest request, HttpServletResponse response, Integer id) {
        ModelAndView andView = new ModelAndView();
        andView.addObject("_path", request.getServletContext().getContextPath());
        andView.addObject("user", request.getSession().getAttribute("user"));
        andView.addObject("id", id);
        andView.setViewName("/blog/report");
        return andView;
    }

    @ResponseBody
    @RequestMapping("updateOne")
    public CommonResponse getUploadOne(Integer id) {
        BlogArticles articles = service.queryOneToUpdate(id);
        if (articles == null) {
            return CommonResponse.error("没有查询到你想要的数据");
        }
        return CommonResponse.isOk(articles);
    }

    /**
     * 修改文章内容
     *
     * @param articles：文章实体
     */
    @ResponseBody
    @RequestMapping("update")
    public CommonResponse update(HttpServletRequest request, @RequestBody BlogArticles articles) {
        BlogUser user = (BlogUser) request.getSession().getAttribute("user");
        if (!articles.getArticlesAuthorId().equals(user.getUserId())) {
            return CommonResponse.error("你无权修改该文章");
        }
        articles.setArticlesContent(articles.getArticlesContent().replaceAll("\n", "<br>"));
        int result = service.update(articles);
        if (result == 0) {
            return CommonResponse.error("修改失败");
        }
        return CommonResponse.isOk(result);
    }

    /**
     * 修改文章内容
     *
     * @param articles：文章实体
     */
    @ResponseBody
    @RequestMapping("updateAll")
    public CommonResponse updateAll(HttpServletRequest request, @RequestBody BlogArticles articles) {
        BlogUser user = (BlogUser) request.getSession().getAttribute("user");
        if (!articles.getArticlesAuthorId().equals(user.getUserId())) {
            return CommonResponse.error("你无权修改该文章");
        }
        articles.setArticlesContent(articles.getArticlesContent().replaceAll("\n", "<br>"));
        int result = service.updateAll(articles);
        if (result == 0) {
            return CommonResponse.error("修改失败");
        }
        return CommonResponse.isOk(result);
    }

    /**
     * 删除文章的请求 一般不使用
     *
     * @param id:文章的id
     */
    @RequestMapping("delete")
    @ResponseBody
    public CommonResponse delete(HttpServletRequest request, @RequestParam Integer id, @RequestParam Integer authorId) {
        BlogUser user = (BlogUser) request.getSession().getAttribute("user");
        if (!user.getUserId().equals(authorId)) {
            return CommonResponse.error("你无权删除别人的文章");
        }
        int result = service.delete(id);
        if (result == 0) {
            return CommonResponse.error("删除失败");
        }
        //删除文章的所有标签
        balService.deleteByBlogId(id);
        return CommonResponse.isOk(result);
    }

    /**
     * 查看文章详情
     *
     * @param id   文章id
     * @param type 访问形式,已作废
     * @return
     */
    @RequestMapping("toContent")
    public ModelAndView toContent(HttpServletRequest request, HttpServletResponse response, Integer id, Integer type,
                                  Integer page) {
        /** 初始化访问页面 */
        ModelAndView andView = new ModelAndView();
        String context_path = request.getServletContext().getContextPath();

        /** 获得文章与用户实体 */
        BlogArticles articles = service.queryById(id);
        BlogUser blogUser = (BlogUser) request.getSession().getAttribute("user");

        articles.setArticlesViewCount(articles.getArticlesViewCount() + 1);
        andView.addObject("comments", articles.getBlogComments());
        andView.addObject("labels", articles.getBlogLabels());
        andView.addObject("user", blogUser);

        /** 刷新文章的评论数量和点赞量 */
        articles.setArticlesCommentCount(articles.getBlogComments().size());
        articles.setArticlesPraiseCount(praiseService.queryCountByArticlesId(id));
        service.updateAll(articles);

        /** 获取文章信息 以及文章评论信息 */
        andView.addObject("_path", context_path);
        andView.addObject("articles", articles);

        /** 登陆用户对该文章的点赞情况 */
        if (praiseService.queryById(id, blogUser.getUserId()) == null) {
            andView.addObject("praise", null);
        } else {
            if (praiseService.queryById(id, blogUser.getUserId()).getPraiseFlag() == 0) {
                andView.addObject("praise", null);
            } else {
                andView.addObject("praise", 1);
            }
        }

        andView.setViewName("/blog/Content");
        return andView;
    }

    /**
     * 访问博客主页的请求
     */
    @RequestMapping("main")
    public ModelAndView toMain(HttpServletRequest request) {
        ModelAndView andView = new ModelAndView();
        String context_path = request.getServletContext().getContextPath();

        andView.addObject("_path", context_path);/* 项目路径数据 */
        andView.addObject("user", request.getSession().getAttribute("user"));/* 用户信息 */
        andView.addObject("labelName", null);
        andView.setViewName("/blog/Index");
        return andView;
    }

    /**
     * 前往发表文章页面的请求
     */
    @RequestMapping("write")
    public ModelAndView write(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView andView = new ModelAndView();
        andView.addObject("_path", request.getServletContext().getContextPath());
        andView.addObject("user", request.getSession().getAttribute("user"));
        andView.addObject("id", null);
        andView.setViewName("/blog/report");
        return andView;
    }

    /**
     * 搜索文章内容的请求
     *
     * @param content   模糊查询的内容
     * @param type      获得数据的排列类型
     * @param orderType 排序类型
     */
    @ResponseBody
    @RequestMapping("search")
    public CommonResponse search(String content, Integer orderType, String type) {
        PageHelper<BlogArticles> helper = service.queryByContent(content, type, orderType, 1, null, null);
        if (helper.getTotolCount() == 0) {
            return CommonResponse.error("没有数据");
        }
        return CommonResponse.isOk(helper.getData());
    }

    @ResponseBody
    @RequestMapping("byLabel")
    public CommonResponse qeuryByLabelName(String labelName) {
        PageHelper<BlogArticles> helper = service.queryByLabelName(labelName);
        if (helper.getTotolCount() == 0) {
            return CommonResponse.error("该标签没有对应的文章");
        }
        return CommonResponse.isOk(helper.getData());
    }

    @ResponseBody
    @RequestMapping("all")
    public CommonResponse queryAll() {
        PageHelper<BlogArticles> helper = service.queryByContent(null, null, null, 1, null, null);
        if (helper.getTotolCount() == 0) {
            return CommonResponse.error("看来还没有人发表文章呢....");
        }
        return CommonResponse.isOk(helper.getData());
    }

    @RequestMapping("labelArticles")
    public ModelAndView queryByLabelId(String labelName, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView andView = new ModelAndView();
        String content_path = request.getServletContext().getContextPath();
        labelService.updateQuickCount(labelName);
        andView.addObject("labelName", labelName);
        andView.addObject("_path", content_path);
        andView.addObject("user", request.getSession().getAttribute("user"));
        andView.setViewName("/blog/Index");
        return andView;
    }
}
