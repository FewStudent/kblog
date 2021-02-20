package club.laky.blogger.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import club.laky.blogger.pojo.BlogLabel;
import club.laky.blogger.service.BlogLabelService;
import club.laky.blogger.util.CommonResponse;
import club.laky.blogger.util.IDConstructor;
import club.laky.blogger.util.JsonUtils;
import club.laky.blogger.util.PageHelper;
import club.laky.blogger.util.TableJson;

@RequestMapping("/admin/label")
@Controller
public class AdminBlogLabelController {

	@Autowired
	BlogLabelService service;

	// 跳转到添加标签页面
	@RequestMapping("toInsert")
	public ModelAndView toInsert(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView andView = new ModelAndView();
		andView.addObject("_path", request.getServletContext().getContextPath());
		andView.addObject("user", request.getSession().getAttribute("user"));
		andView.setViewName("/blog/admin_update/admin_add_Label");
		return andView;
	}

	// 进行添加操作
	@RequestMapping("insert")
	@ResponseBody
	public CommonResponse Insert(@RequestParam String labelName, HttpServletRequest request,
			HttpServletResponse response) {
		BlogLabel blogLabel = new BlogLabel();
		blogLabel.setLabelName(labelName);
		blogLabel.setLabelPickCount(0);
		blogLabel.setLabelState(1);
		int result = service.insert(blogLabel);
		return CommonResponse.isOk(result);
	}

	/**
	 * 前往标签管理页面的请求
	 */
	@RequestMapping("manage")
	public ModelAndView manage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView andView = new ModelAndView();
		andView.addObject("_path", request.getServletContext().getContextPath());
		andView.addObject("user", request.getSession().getAttribute("user"));
		andView.setViewName("/blog/admin/admin_label");
		return andView;
	}

	/**
	 * layui的表格数据
	 * 
	 * @return 返回符合layui表格格式的数据
	 * 
	 * @param page,页数
	 * @param limit,单页数量
	 * @param content,标签名
	 * @param state,标签状态
	 */
	@ResponseBody
	@RequestMapping(value = "/page", produces = "text/html;charset=UTF-8")
	public String page(int page, int limit, Integer state, String likeName) {
		PageHelper<BlogLabel> pageHelper;
		pageHelper = service.queryByLikeName(state, likeName, page, limit);
		/* 将数据转换成符合layui表格的数据 */
		TableJson json = new TableJson(0, "获取数据成功", pageHelper.getTotolCount(), pageHelper.getData());
		return JsonUtils.objectToJson(json);
	}

	/**
	 * 恢复标签
	 * 
	 * @return 返回数据库修改条数
	 * @param id
	 *            被恢复标签的id
	 */
	@RequestMapping("allow")
	@ResponseBody
	public Integer allow(@RequestParam Integer id) {
		BlogLabel label = new BlogLabel();
		label.setLabelId(id);
		label.setLabelState(1);
		return service.update(label);
	}

	/**
	 * 屏蔽标签
	 * 
	 * @return 返回数据库修改条数
	 * @param id
	 *            被屏蔽标签的id
	 */
	@ResponseBody
	@RequestMapping("down")
	public Integer freeze(@RequestParam Integer id) {
		BlogLabel label = new BlogLabel();
		label.setLabelId(id);
		label.setLabelState(0);
		return service.update(label);
	}
}
