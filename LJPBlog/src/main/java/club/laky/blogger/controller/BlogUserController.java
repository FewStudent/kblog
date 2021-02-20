package club.laky.blogger.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import club.laky.blogger.pojo.BlogComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import club.laky.blogger.service.BlogUserService;
import club.laky.blogger.pojo.BlogUser;
import club.laky.blogger.util.CommonResponse;
import club.laky.blogger.util.MD5Utils;

@Controller
@RequestMapping("/user")
public class BlogUserController {

	@Autowired
	BlogUserService service;
	
	/**	添加用户的请求
	 * @param	user	需要添加的用户实体
	 * */
	@RequestMapping("insert")
	@ResponseBody
	public Integer insert(HttpServletRequest request,HttpServletResponse response,@RequestBody BlogUser user) {
		int result = service.insert(user);
		if(result == 1) {
			/*	如果添加成功 即：注册用户成功 解除拦截器的拦截	*/
			request.getSession().setAttribute("user", service.searchByuserName(user.getUserName()));
		}
		return result;
	}
	
	/**	修改用户信息
	 * @return	返回数据库变化条数
	 * @param	user	用户实体
	 * */
	@RequestMapping("update")
	@ResponseBody
	public Integer update(HttpServletRequest request,HttpServletResponse response,@RequestBody BlogUser user) {
		BlogUser blogUser = (BlogUser) request.getSession().getAttribute("user");
		
		int result = service.update(user);
		
		/*	如果修改的用户信息和当前会话中的用户一致，那么会话中用户也同意更新	*/
		if(user.getUserId().equals(blogUser.getUserId())) {
			request.getSession().setAttribute("user", service.queryById(user.getUserId()));
		}
		return result;
	}
	
	/**	删除用户的请求	一般不使用
	 * @return	返回数据库变化条数
	 * @param	id		需要被删除的用户id
	 * */
	@RequestMapping("delete")
	@ResponseBody
	public Integer delete(Integer id) {
		return service.delete(id);
	}
	
	/**	获得一个用户的信息
	 * @return	返回一个用户的信息
	 * @param	id		需要获取用户的id
	 * */
	@RequestMapping("getOne")
	@ResponseBody
	public BlogUser queryById(Integer id) {
		return service.queryById(id);
	}
	
	/**	
	 * 跳转到登陆页面的请求
	 * */
	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView andView = new ModelAndView();
		String content_path = request.getServletContext().getContextPath();
		andView.addObject("_path",content_path);
		andView.setViewName("/login");
		return andView;
	}
	
	/**	用户登陆的验证请求
	 * @return	返回用户验证的结果
	 * @param	userName		用户名
	 * @param	userPassword	用户密码
	 * */
	@RequestMapping("verify")
	@ResponseBody
	public Integer verify(HttpSession session,@RequestParam String userName, String  userPassword) {
		BlogUser user = new BlogUser();
		user.setUserName(userName);
		user.setUserPassword(MD5Utils.getMD5(userPassword));
		Integer result = service.login(user);
		if(result == 1) {
			/*	如果用户验证成功,将用户信息存放在会话中	*/
			session.setAttribute("user", service.searchByuserName(userName));
		}
		return result;
	}
	
	/** 
	 * 前往用户注册页面 
	 * */
	@RequestMapping("toRegist")
	public ModelAndView toRegist(HttpServletRequest request) {
		ModelAndView andView = new ModelAndView();
		String context_path = request.getServletContext().getContextPath();
		andView.addObject("_path",context_path);
		andView.setViewName("/regist");
		return andView;
	}
	
	/**	用户注册的验证
	 * 	@return		返回注册验证的结果
	 * 	@param		userName	注册的用户名
	 * */
	@RequestMapping("regist")
	@ResponseBody
	public Integer regist(HttpServletRequest request,@RequestParam String userName) {
		BlogUser user = new BlogUser();
		user.setUserName(userName);
		int result = service.regist(user);
		if(result == 1) {
			/*	如果用户验证成功,将用户信息存放在会话中	*/
			request.getSession().setAttribute("user", service.searchByuserName(userName));
		}
		return result;
	}
	
	/**	
	 * 用户登陆成功或注册成功后前往的首页请求
	 * */
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView andView = new ModelAndView();
		String context_path = request.getServletContext().getContextPath();
		andView.addObject("_path",context_path);
		andView.addObject("user", request.getSession().getAttribute("user"));
		andView.setViewName("/blog/Index");
		return andView;
	}
	
	/**	查看用户信息的请求
	 * @param	id		用户的id
	 * 
	 * */
	@RequestMapping("userInformation")
	public ModelAndView information(HttpServletRequest request,HttpServletResponse response,Integer id) {
		ModelAndView andView = new ModelAndView();
		andView.addObject("_path",request.getServletContext().getContextPath());
		
		BlogUser sessionUser = (BlogUser) request.getSession().getAttribute("user");
		
		/** 没有id 或者id和会话中用户的一致,则访问用户的信息;否则访问其他用户的信息 */
		if(id == null || sessionUser.getUserId().equals(id)) {
			andView.setViewName("/blog/userInformation"); 
		}else {
			andView.addObject("otherUser",service.queryById(id));
			andView.setViewName("/blog/otherUserInformation");
		}
		andView.addObject("user",sessionUser);
		
		return andView;
	}
	
	/**	
	 * 退出登录的请求
	 * */
	@RequestMapping("out")
	public ModelAndView out(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView andView = new ModelAndView();
		
		/* 清理会话中用户的信息	*/
		request.getSession().setAttribute("user", null);
		andView.addObject("_path",request.getServletContext().getContextPath());
		andView.setViewName("/login");
		return andView;
	}
	
	@RequestMapping("mycomment")
	public ModelAndView myComment(HttpServletRequest request,HttpServletResponse response,Integer id) {
		ModelAndView andView = new ModelAndView();
		andView.addObject("_path",request.getServletContext().getContextPath());
		andView.addObject("user",request.getSession().getAttribute("user"));
		andView.setViewName("/blog/mycomment");
		return andView;
	}
	
	@RequestMapping("getmyc")
	@ResponseBody
	public CommonResponse getMyComments(Integer id) {
		BlogUser user = service.queryMyCommentsById(id,null,null);
		if(user.getComments().size() == 0) {
			return CommonResponse.error("没有数据哦");
		}else {
			for (BlogComment comment : user.getComments()) {
				if(comment.getArticlesTitle() == null || "".equals(comment.getArticlesTitle())){
					comment.setArticlesTitle("该文章已经被删除");
				}
			}
			return CommonResponse.isOk(user.getComments());
		}
	}
	
	@RequestMapping("toMyA")
	public ModelAndView toMyComment(HttpServletRequest request,HttpServletResponse response,Integer id) {
		ModelAndView andView = new ModelAndView();
		andView.addObject("_path",request.getServletContext().getContextPath());
		andView.addObject("user",request.getSession().getAttribute("user"));
		andView.setViewName("/blog/myarticles");
		return andView;
	}
	
	@ResponseBody
	@RequestMapping("myArticles")
	public CommonResponse myArticles(HttpServletRequest request) {
		BlogUser sessionUser = (BlogUser) request.getSession().getAttribute("user");
		BlogUser user = service.queryMyArticlesById(sessionUser.getUserId(),null,null);
		System.out.println(user.getUserId());
		if(user.getArticles().size() == 0) {
			return CommonResponse.error("你还没有发表过文章呢");
		}
		return CommonResponse.isOk(user.getArticles());
	}

}
