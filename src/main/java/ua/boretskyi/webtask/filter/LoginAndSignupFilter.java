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
 * Servlet Filter implementation class LoginAndSignupFilter
 */
@WebFilter(urlPatterns = { "/login", "/signup" })
public class LoginAndSignupFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user == null) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect("profile");
		}
	}

}
