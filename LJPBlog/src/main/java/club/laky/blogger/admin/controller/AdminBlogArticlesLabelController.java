package club.laky.blogger.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import club.laky.blogger.pojo.BlogArticlesLabel;
import club.laky.blogger.service.BlogArticlesLabelService;
import club.laky.blogger.util.JsonUtils;
import club.laky.blogger.util.PageHelper;
import club.laky.blogger.util.TableJson;

@RequestMapping("/admin/articlesLabel")
@Controller
public class AdminBlogArticlesLabelController {

	@Autowired
	BlogArticlesLabelService service;
	
	/**
	 * 前往文章标签管理页面的请求
	 */
	@RequestMapping("manage")
	public ModelAndView manage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView andView = new ModelAndView();
		andView.addObject("_path", request.getServletContext().getContextPath());
		andView.addObject("user", request.getSession().getAttribute("user"));
		andView.setViewName("/blog/admin/admin_articles_label");
		return andView;
	}
	
	/**
	 * layui的表格数据
	 * 
	 * @return 返回符合layui表格格式的数据
	 * @param page:页数
	 * @param limit:单页数量
	 * @param title:文章标题
	 * @param state:标签状态
	 */
	@ResponseBody
	@RequestMapping(value = "/page", produces = "text/html;charset=UTF-8")
	public String page(int page, int limit, Integer state, String title,String label) {

		System.out.println("--------------------");
		System.out.println(state);
		System.out.println(title);
		System.out.println(label);
		System.out.println("--------------------");

		PageHelper<BlogArticlesLabel> labels;
		labels = service.queryAllTx(title,label,state, page, limit);
		/* 将数据转换成符合layui表格的数据 */
		TableJson json = new TableJson(0,"获取数据成功",labels.getTotolCount(),labels.getData());

		return JsonUtils.objectToJson(json);
	}
	
	@RequestMapping("allow")
	@ResponseBody
	public Integer allow(@RequestParam Integer id) {
		return service.updateTx(1,id);
	}

	/**
	 * 屏蔽文章标签的请求
	 * 
	 * @return 返回数据库修改条数
	 * @param id:被屏蔽文章标签的id
	 */
	@ResponseBody
	@RequestMapping("down")
	public Integer freeze(@RequestParam Integer id) {
		return service.updateTx(0,id);
	}

}
