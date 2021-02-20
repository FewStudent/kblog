package club.laky.blogger.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import club.laky.blogger.pojo.BlogUser;
import club.laky.blogger.service.BlogUserService;
import club.laky.blogger.util.JsonUtils;
import club.laky.blogger.util.PageHelper;
import club.laky.blogger.util.TableJson;

@Controller
@RequestMapping("/admin/user")
public class AdminBlogUserController {

	@Autowired
	BlogUserService service;
	
	/** 
	 * 前往用户管理页面的请求
	 * */
	@RequestMapping("manage")
	public ModelAndView manage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView andView = new ModelAndView();
		andView.addObject("_path",request.getServletContext().getContextPath());
		andView.addObject("user",request.getSession().getAttribute("user"));
		andView.setViewName("/blog/admin/admin_user");
		return andView;
	}
	
	/** layui的表格数据
	 * @return	返回符合layui表格格式的数据
	 * @param 	page	页数
	 * @param 	limit 	单页数量
	 * @param 	power	用户权限
	 * @param 	state	用户状态
	 */
	@ResponseBody
	@RequestMapping(value="/page",produces = "text/html;charset=UTF-8")
	public String page(int page,int limit,Integer power, Integer state, String content) {
		PageHelper<BlogUser> pageHelper;
		if(state != null || power != null || (content != null && !"".equals(content))) {
			pageHelper = service.queryByAnotherName(content, state, power, page, limit);
		}else {
			pageHelper = service.queryAll(page, limit);
		}
		
		/* 将数据转换成符合layui表格的数据 */
		TableJson json = new TableJson(0,"获取数据成功",pageHelper.getTotolCount(),pageHelper.getData());
		
		return JsonUtils.objectToJson(json);
	}
	
	/** 授权用户的请求
	 * @return	返回数据库修改条数
	 * @param	id	授权用户的id
	 * */
	@RequestMapping("allow")
	@ResponseBody
	public Integer allow(@RequestParam Integer id) {
		BlogUser blogUser = new BlogUser();
		blogUser.setUserId(id);
		blogUser.setUserPower(1);
		return service.update(blogUser);
	}
	
	/** 冻结用户的请求
	 * @return	返回数据库修改条数
	 * @param	id	授权用户的id
	 * */
	@ResponseBody
	@RequestMapping("freeze")
	public Integer freeze(@RequestParam Integer id) {
		BlogUser blogUser = new BlogUser();
		blogUser.setUserId(id);
		blogUser.setUserState(0);
		return service.update(blogUser);
	}
	
	/** 用户解冻的请求
	 * @return	返回数据库修改条数
	 * @param	id	用户的id
	 * */
	@ResponseBody
	@RequestMapping("thaw")
	public Integer thaw(@RequestParam Integer id) {
		BlogUser blogUser = new BlogUser();
		blogUser.setUserId(id);
		blogUser.setUserState(1);
		return service.update(blogUser);
	}
	
	/**	收回用户权利的请求
	 * @return	返回数据库修改条数
	 * @param	id	收回用户权利的请求
	 * */
	@ResponseBody
	@RequestMapping("relieve")
	public Integer relieve(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer id) {
		BlogUser blogUser = new BlogUser();
		blogUser.setUserId(id);
		blogUser.setUserPower(2);
		return service.update(blogUser);
	}
}
