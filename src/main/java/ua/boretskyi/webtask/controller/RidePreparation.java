package ua.boretskyi.webtask.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.boretskyi.webtask.dao.entity.Car;
import ua.boretskyi.webtask.dao.entity.Ride;
import ua.boretskyi.webtask.dao.entity.RideBean;
import ua.boretskyi.webtask.dao.entity.User;
import ua.boretskyi.webtask.logic.CarManager;
import ua.boretskyi.webtask.logic.DBException;
import ua.boretskyi.webtask.logic.RideBuilder;
import ua.boretskyi.webtask.logic.RideManager;

@WebServlet("/ride")
public class RidePreparation extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		CarManager manager = new CarManager();
		RideManager rm = new RideManager();
		List<Car> suitableCars = new ArrayList<>(); 
		List<Car> allCars = new ArrayList<>();
		RideBean bean = (RideBean)session.getAttribute("rideInfo");
		try {
			suitableCars = manager.findSpecificCars(bean.getNumberOfPassengers(), bean.getTypeOfCar());
			
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		if(req.getParameter("carid") != null) {
			
			int id = Integer.parseInt((String) req.getParameter("carid"));
			try {
				Car selected = manager.findCar(id);
				session.setAttribute("car", selected);
				bean.setTypeOfCar(selected.getType());
			} catch (DBException e) {
				e.printStackTrace();
				String message = "Failed to find a car with id " + id;
				forwardToErrorPage(req, resp, message);
				return;
			}
		} else {
			session.removeAttribute("car");
		}
		
		if(req.getParameter("allAvailable") != null || suitableCars.isEmpty()){
			try {
				allCars = manager.findAllAvailableCars();
			} catch (DBException e) {
				e.printStackTrace();
			}
		}
		
		session.setAttribute("suitableCars", suitableCars);

		session.setAttribute("allAvailableCars", allCars);
		session.setAttribute("rideInfo", bean);
		req.getRequestDispatcher("ride-confirmation.jsp").forward(req, resp);
		
		
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		
		RideManager rm = new RideManager();
			if(session.getAttribute("car") != null && session.getAttribute("rideInfo") != null) {
			RideBuilder rb = new RideBuilder();
			Ride ride = rb.buildRide((RideBean)session.getAttribute("rideInfo"), (Car)session.getAttribute("car"), (User)session.getAttribute("user"));

			try {
				rm.createRide(ride);
				session.setAttribute("successMessage", "The ride was successfully created");
				resp.sendRedirect("profile");
				return;
			} catch (DBException e) {
				e.printStackTrace();
			}
			session.removeAttribute("car");
			session.removeAttribute("rideInfo");
			return;
			} else {
				req.getRequestDispatcher("error.jsp").forward(req, resp);
			}
	}
	
	

	private void forwardToErrorPage(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("message", message);
		req.getRequestDispatcher("error.jsp").forward(req, resp);
	}
}
