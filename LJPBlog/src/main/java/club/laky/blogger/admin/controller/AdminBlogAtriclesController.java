package club.laky.blogger.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import club.laky.blogger.pojo.BlogArticles;
import club.laky.blogger.service.BlogArticlesService;
import club.laky.blogger.util.CommonResponse;
import club.laky.blogger.util.JsonUtils;
import club.laky.blogger.util.PageHelper;
import club.laky.blogger.util.TableJson;

@Controller
@RequestMapping("/admin/articles")
public class AdminBlogAtriclesController {

	@Autowired
	BlogArticlesService service;

	/** 删除一篇文章 */
	@ResponseBody
	@RequestMapping("delete")
	public CommonResponse delete(Integer id) {
		int result = service.delete(id);
		if (result == 0) {
			return CommonResponse.error("删除失败");
		}
		return CommonResponse.isOk(result);
	}

	/**
	 * 前往文章管理页面的请求
	 */
	@RequestMapping("manage")
	public ModelAndView manage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView andView = new ModelAndView();
		andView.addObject("_path", request.getServletContext().getContextPath());
		andView.addObject("user", request.getSession().getAttribute("user"));
		andView.setViewName("/blog/admin/admin_articles");
		return andView;
	}

	/**
	 * layui的表格数据
	 * 
	 * @return 返回符合layui表格格式的数据
	 * @param page:页数
	 * @param limit:单页数量
	 * @param content:文章相关内容
	 *            如标题、内容
	 * @param state:文章状态
	 */
	@ResponseBody
	@RequestMapping(value = "/page", produces = "text/html;charset=UTF-8")
	public String page(int page, int limit, Integer state, String content, String type) {
		PageHelper<BlogArticles> pageHelper = service.queryByCotentNotLabel(content, type, null, state, page, limit);
		/* 将数据转换成符合layui表格的数据 */
		TableJson json = new TableJson(0, "获取数据成功", pageHelper.getTotolCount(), pageHelper.getData());
		return JsonUtils.objectToJson(json);
	}

	/**
	 * 审核通过或恢复文章的请求
	 * 
	 * @return 返回数据库修改条数
	 * @param id:审核通过或恢复文章的id
	 */
	@RequestMapping("allow")
	@ResponseBody
	public Integer allow(@RequestParam Integer id) {
		BlogArticles articles = new BlogArticles();
		articles.setArticlesId(id);
		articles.setArticlesState(1);
		return service.update(articles);
	}

	/**
	 * 屏蔽文章的请求
	 * 
	 * @return 返回数据库修改条数
	 * @param id:被屏蔽文章的id
	 */
	@ResponseBody
	@RequestMapping("down")
	public Integer freeze(@RequestParam Integer id) {
		BlogArticles articles = new BlogArticles();
		articles.setArticlesId(id);
		articles.setArticlesState(2);
		return service.update(articles);
	}
}
