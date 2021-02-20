package club.laky.blogger.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import club.laky.blogger.pojo.BlogComment;
import club.laky.blogger.service.BlogCommentService;
import club.laky.blogger.util.CommonResponse;
import club.laky.blogger.util.JsonUtils;
import club.laky.blogger.util.PageHelper;
import club.laky.blogger.util.TableJson;

@Controller
@RequestMapping("/admin/comment")
public class AdminBlogCommentController {

	@Autowired
	BlogCommentService service;
	
	/** 
	 * 前往用户管理页面的请求
	 * */
	@RequestMapping("manage")
	public ModelAndView manage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView andView = new ModelAndView();
		andView.addObject("_path",request.getServletContext().getContextPath());
		andView.addObject("user",request.getSession().getAttribute("user"));
		andView.setViewName("/blog/admin/admin_comment");
		return andView;
	}
	
	/** layui的表格数据
	 * @return	返回符合layui表格格式的数据
	 * @param 	page	页数
	 * @param 	limit 	单页数量
	 * @param 	content	搜索内容
	 * @param 	state	用户状态
	 */
	@ResponseBody
	@RequestMapping(value="/page",produces = "text/html;charset=UTF-8")
	public String page(int page,int limit,Integer state, String content) {
		PageHelper<BlogComment> comments = service.queryByContent(content, state, page, limit);
		/* 将数据转换成符合layui表格的数据 */
		TableJson json = new TableJson(0,"数据获取成功",comments.getTotalPage(),comments.getData());
		return JsonUtils.objectToJson(json);
	}
	
	/** 评论改为可见的的请求
	 * @return	返回数据库修改条数
	 * @param	id	评论id
	 * */
	@ResponseBody
	@RequestMapping("allow")
	public Integer allow(@RequestParam Integer id) {
		BlogComment comment = new BlogComment();
		comment.setCommentState(1);
		comment.setCommentId(id);
		return service.update(comment);
	}
	
	/** 屏蔽评论的请求
	 * @return	返回数据库修改条数
	 * @param	id	被屏蔽评论的id
	 * */
	@ResponseBody
	@RequestMapping("down")
	public Integer freeze(@RequestParam Integer id) {
		BlogComment comment = new BlogComment();
		comment.setCommentState(0);
		comment.setCommentId(id);
		return service.update(comment);
	}
	
	@ResponseBody
	@RequestMapping("delete")
	public CommonResponse delete(Integer id) {
		int result = service.delete(id);
		if(result == 0) {
			return CommonResponse.error("删除失败");
		}
		return CommonResponse.isOk(result); 
	}
}
