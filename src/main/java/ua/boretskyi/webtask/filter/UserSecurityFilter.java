package ua.boretskyi.webtask.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.boretskyi.webtask.dao.entity.User;

/**
 * Servlet Filter implementation class UserSecurityFilter
 */
@WebFilter(urlPatterns = {"/profile", "/cancel-ride", "/ride-info", "/ride"})
public class UserSecurityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;	
		
		String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", ""); 
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if(path.equals("/profile")) {
			if(user == null) {
				response.sendRedirect("login");
			} else {
				chain.doFilter(request, response);
			}
		} else if(user == null || user.getRole() != User.Role.CLIENT) {
			response.sendRedirect("index.jsp");
		}
		else {
		chain.doFilter(request, response);
		}
	}

 
}
