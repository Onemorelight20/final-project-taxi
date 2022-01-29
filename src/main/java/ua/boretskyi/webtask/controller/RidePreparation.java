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
		CarManager carManager = new CarManager();
		List<Car> suitableCars = new ArrayList<>(); 
		List<Car> allCars = new ArrayList<>();
		RideBuilder rideBuilder = (RideBuilder)session.getAttribute("rideInfo");
		
		try {
			suitableCars = carManager.findSpecificCars(rideBuilder.getNumberOfPassengers(), rideBuilder.getTypeOfCar());
			allCars = carManager.findAllAvailableCarsWithSeatsAvailable(rideBuilder.getNumberOfPassengers());
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		if(req.getParameter("carid") != null) {
			int id = 0;
			try {
				id = Integer.parseInt((String) req.getParameter("carid"));
				Car selected = carManager.findCar(id);
				session.setAttribute("car", selected);
				rideBuilder.setTypeOfCar(selected.getType());
			} catch (NumberFormatException | DBException e) {
				e.printStackTrace();
				String message = "Failed to find a car with id " + id;
				forwardToErrorPage(req, resp, message);
				return;
			} 
		} else {
			session.removeAttribute("car");
		}

		
		if(req.getParameter("severalCars") != null ) {
			try {
				List<Car> pack = carManager.findSeveralAvailableCarOfSelectedType(rideBuilder.getNumberOfPassengers(), rideBuilder.getTypeOfCar());
				session.setAttribute("pack", pack);
			} catch (DBException e) {
				e.printStackTrace();
			}
		}
		
		session.setAttribute("suitableCars", suitableCars);

		System.out.println(allCars);
		session.setAttribute("allAvailableCars", allCars);
		req.getRequestDispatcher("ride-confirmation.jsp").forward(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		RideBuilder rideBuilder = (RideBuilder)session.getAttribute("rideInfo");
		User user = (User)session.getAttribute("user");
		RideManager rm = new RideManager();
		
			if(session.getAttribute("car") != null && rideBuilder != null) {
			Ride ride = rideBuilder.buildRide((Car)session.getAttribute("car"), user);

			try {
				rm.createRide(ride);
				session.setAttribute(Profile.SUCCESS_MESSAGE_ATTRIBUTE, "The ride was successfully created");
				resp.sendRedirect("profile");
				return;
			} catch (DBException e) {
				e.printStackTrace();
			}
			session.removeAttribute("car");
			session.removeAttribute("rideInfo");
			return;
			} else if (req.getParameter("severalCars") != null){
				List<Car> pack = (List<Car>)session.getAttribute("pack");
				List<Ride> ridesToCreate = new ArrayList<>();
				for(Car car : pack) {
					Ride ride = rideBuilder.buildRide(car.getSeatsAvailable(), car, user);
					ridesToCreate.add(ride);
				}
				Ride[] rides = new Ride[ridesToCreate.size()];
				ridesToCreate.toArray(rides);
				try {
					rm.createRides(rides);
				} catch (DBException e) {
					e.printStackTrace();
					
					forwardToErrorPage(req, resp, "Failed to create a transaction");
				}
				session.setAttribute(Profile.SUCCESS_MESSAGE_ATTRIBUTE, "The rides were successfully created");
				resp.sendRedirect("profile");
				return;
			} else {
				forwardToErrorPage(req, resp, "Something went wrong");
			}
	}
	
	

	private void forwardToErrorPage(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("message", message);
		req.getRequestDispatcher("error.jsp").forward(req, resp);
	}
}
