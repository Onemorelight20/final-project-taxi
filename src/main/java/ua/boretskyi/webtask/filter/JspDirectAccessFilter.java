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

@WebFilter("*.jsp")
public class JspDirectAccessFilter implements Filter {
	private static final String MAIN_PAGE = "index.jsp";

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");

		if (path.equals("/" + MAIN_PAGE))
			chain.doFilter(request, response);
		else
			response.sendRedirect(MAIN_PAGE);

	}

}
