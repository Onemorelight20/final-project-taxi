package ua.boretskyi.webtask.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.boretskyi.webtask.dao.entity.Car;
import ua.boretskyi.webtask.dao.entity.Ride;
import ua.boretskyi.webtask.dao.entity.User;
import ua.boretskyi.webtask.logic.CarManager;
import ua.boretskyi.webtask.logic.DBException;
import ua.boretskyi.webtask.logic.RideManager;
import ua.boretskyi.webtask.logic.UserManager;

@WebServlet("/ride-info")
public class RideInfo extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = 0;
		try{
			id = Integer.parseInt(req.getParameter("id"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			String message = "You don`t have rights to view this info";
			forwardToErrorPageWithMessage(req, resp, message);
			return;
		}
		HttpSession session = req.getSession();
		RideManager rm = new RideManager();
		UserManager um = new UserManager();
		CarManager cm = new CarManager();
		User user = (User) session.getAttribute("user");
		Ride ride = null;
		User driver = null;
		Car car = null;
		try {
			ride = rm.findRide(id);
			driver = um.findUser(ride.getDriverId());
			car = cm.findCar(ride.getCarId());
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		if(ride == null || driver == null || car == null || ride.getUserId() != user.getId()) {
			String message = "You don`t have rights to view this info";
			forwardToErrorPageWithMessage(req, resp, message);
			return;
		}
		
		req.setAttribute("ride", ride);
		req.setAttribute("driver", driver);
		req.setAttribute("car", car);
		req.getRequestDispatcher("ride-info.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	private void forwardToErrorPageWithMessage(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("message", message);
		req.getRequestDispatcher("error.jsp").forward(req, resp);
	}
}
