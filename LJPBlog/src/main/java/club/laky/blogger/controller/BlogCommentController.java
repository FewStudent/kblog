package club.laky.blogger.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import club.laky.blogger.service.BlogCommentService;
import club.laky.blogger.util.CommonResponse;
import club.laky.blogger.pojo.BlogComment;

@Controller
@RequestMapping("/comment")
public class BlogCommentController {

	@Autowired
	BlogCommentService service;
	
	/**	发表一条评论的请求
	 * @return	返回数据库变化条数
	 * @param	blogComment		评论实体
	 * */
	@RequestMapping("insert")
	@ResponseBody
	public CommonResponse insert(@RequestBody BlogComment blogComment) {
		if(blogComment.getCommentContent() != null && !"".equals(blogComment.getCommentContent())) {
			blogComment.setCommentContent(blogComment.getCommentContent().replaceAll("\"", "\'"));
		}
		int result = service.insert(blogComment);
		if(result == 0) {
			return CommonResponse.error("添加失败咯");
		}
		return CommonResponse.isOk(result);
	}
	
	/**	修改一条评论的请求
	 * 	@param	blogComment		评论实体
	 * */
	@RequestMapping("update")
	@ResponseBody
	public CommonResponse update(@RequestBody BlogComment blogComment) {
		if(blogComment.getCommentContent() != null && !"".equals(blogComment.getCommentContent())) {
			blogComment.setCommentContent(blogComment.getCommentContent().replaceAll("\"", "\'"));
		}
		int result = service.update(blogComment);
		if (result == 0) {
			return CommonResponse.error("修改失败");
		}
		return CommonResponse.isOk(result);
	}
	
	/**	删除一条评论的请求	一般不适用
	 * 	@param	id		评论的id
	 * */
	@RequestMapping("delete")
	@ResponseBody
	public CommonResponse delete(Integer id) {
		int result = service.delete(id);
		if(result == 0) {
			return CommonResponse.error("删除失败");
		}
		return CommonResponse.isOk(result);
	}
	
	/**
	 * 获得我的所有评论
	 * @param	id	我的id
	 * */
	@RequestMapping("mycomment")
	public ModelAndView getMyComment(HttpServletRequest request,HttpServletResponse response,Integer id,Integer page,Integer limit) {
		ModelAndView andView = new ModelAndView();
		andView.addObject("_path",request.getServletContext().getContextPath());
		andView.addObject("user",request.getSession().getAttribute("user"));
		andView.addObject("mycomments",service.queryMyComment(id,page,limit));
		andView.setViewName("/blog/mycomment");
		return andView;
		
	}
}
