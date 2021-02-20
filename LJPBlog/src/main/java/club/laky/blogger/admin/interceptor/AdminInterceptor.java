package club.laky.blogger.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import club.laky.blogger.pojo.BlogManager;

public class AdminInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		BlogManager blogManager = (BlogManager) session.getAttribute("admin");
		/** 用户未登陆 拦截并重定向到登陆页面 */
		if(blogManager == null) {
			response.sendRedirect(request.getContextPath()+"/admin/manager/login");
			return false;
		}
		/** 用户权限不足 拦截并重定向到主页 */
		/*if(blogManager.getPower() == 2) {
			response.sendRedirect(request.getContextPath()+"/articles/main");
			return false;
		}*/
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
