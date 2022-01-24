package ua.boretskyi.webtask.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.boretskyi.webtask.dao.entity.Car;
import ua.boretskyi.webtask.dao.entity.Ride;
import ua.boretskyi.webtask.logic.DBException;
import ua.boretskyi.webtask.logic.RideManager;

@WebServlet("/test")
public class TestServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
		RideManager manager = new RideManager();
		try {
			PrintWriter writer = resp.getWriter();
			
		Car.Type[] e = Car.Type.values();
		
			List<Ride> rides = manager.findAll();
			writer.append("<html><body>");
			writer.append("<ol>");
			for(Ride r : rides) {
				writer.append("<li>");
				writer.append(r.toString());
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
