package cn.xinguan.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录拦截过滤
 * 
 * @author MingJun Chen
 * 
 */
public class LoginFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		// 1.将reqeust对象转换
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// 2.从Session中得到admin
		Object user = req.getSession().getAttribute("admin");

		// 3.判断用户信息是否存在
		if (user == null) {
			// req.getRequestDispatcher("/login.jsp").forward(req, response);
			resp.sendRedirect("login.jsp");
		} else {
			chain.doFilter(request, response);// 放行
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
}
