package ua.boretskyi.webtask.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.boretskyi.webtask.dao.entity.User;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		System.out.println("profile servlet");
		if(session.getAttribute("user") == null) {
			System.out.println("No such user exists");
			resp.sendRedirect("login");
		}else {
			User user = (User) session.getAttribute("user");
			String role = user.getRole();
			System.out.println(role);
			if(role.equals("admin")) {
				req.getRequestDispatcher("admin.jsp").forward(req, resp);;
			} else if(role.equals("client")) {
				req.getRequestDispatcher("user-profile.jsp").forward(req, resp);;
			}
			
		}

	}
	

	
}
