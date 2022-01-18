package ua.boretskyi.webtask.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.boretskyi.webtask.dao.DAOFactory;
import ua.boretskyi.webtask.dao.UserDAO;
import ua.boretskyi.webtask.dao.entity.User;
import ua.boretskyi.webtask.logic.DBException;

@WebServlet("/*")
public class TestServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
		DAOFactory f = DAOFactory.getInstance();
		UserDAO udao = f.getUserDAO();
		try {
			PrintWriter writer = resp.getWriter();
			List<User> users = udao.findAll();

			User user = new User();
			writer.append("<html><body>");
			
			writer.append("<ol>");
			for(User u : users) {
				writer.append("<li>");
				writer.append(u.toString());
				writer.append("</li>");
			}
			
			writer.append("</ol>");
			writer.append("</body></html>");
			
		} catch (DBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
