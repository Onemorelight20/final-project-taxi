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
 * Servlet Filter implementation class AdminSecurityFilter
 */
@WebFilter(urlPatterns = {"/stats"})
public class AdminSecurityFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;	
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null || user.getRole() != User.Role.ADMIN) {
			response.sendRedirect("index.jsp");
		} else {
			chain.doFilter(request, response);
		}
		
	}


}
