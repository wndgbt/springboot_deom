package com.common.filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.common.generic.PageModelContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 处理pageNo,pageSize,pageOrderBy参数
 * @author tiger
 *
 */
public class PagingInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		/**
		 * 为PageModelContext注入pageNo和pageSize参数
		 */

		if (request.getParameter("pageNo") != null) {
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));
			if (pageNo < 1) {
				pageNo = 1;
			}
			PageModelContext.setPageNo(pageNo);
		}else{
			PageModelContext.setPageNo(1);
		}
		if (request.getParameter("pageSize") != null) {
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			if (pageSize > 500) {
				pageSize = 500;
			}
			PageModelContext.setPageSize(pageSize);
		}else{
			PageModelContext.setPageSize(20);
		}
		
		if (request.getParameter("pageOrderBy") != null) {
			String pageOrderBy = request.getParameter("pageOrderBy");
			PageModelContext.setPageOrderBy(pageOrderBy);
		}
		
		if (request.getParameter("pageOrderType") != null) {
			String pageOrderType = request.getParameter("pageOrderType");
			PageModelContext.setPageOrderType(pageOrderType);
		}
		
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView mav)
			throws Exception {
		// System.out.println("postHandle");
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception excptn)
			throws Exception {
		// System.out.println("afterCompletion");
		PageModelContext.removePageNoThread();
		PageModelContext.removePageSizeThread();
		PageModelContext.removePageOrderByThread();
		PageModelContext.removePageOrderTypeThread();
		
	}

}
