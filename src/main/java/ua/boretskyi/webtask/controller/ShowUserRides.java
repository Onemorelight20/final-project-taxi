package ua.boretskyi.webtask.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.boretskyi.webtask.dao.entity.Ride;
import ua.boretskyi.webtask.dao.entity.User;
import ua.boretskyi.webtask.logic.DBException;
import ua.boretskyi.webtask.logic.RideManager;

@WebServlet("/rides")
public class ShowUserRides extends HttpServlet {
	private static final int shift = 0;
	private static final Logger log = Logger.getLogger(ShowUserRides.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		String paramPage = req.getParameter("page");
		String paramPageSize = req.getParameter("pageSize");
		
		Integer page = null;
		Integer pageSize = null;
		try {
		page = Integer.parseInt(paramPage);
		pageSize = Integer.parseInt(paramPageSize);
		} catch (Exception e) {
			log.info("Exception was thrown while parsing page params");
			e.printStackTrace();
			req.setAttribute("message", "Illegal params were passed in URL");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
			return;
		}
		
		List<Ride> allRidesOfUser = showUserRides(user);

		int size = allRidesOfUser.size();
		int pageCount = (int) Math.ceil((double)size / pageSize);

		if(page > pageCount) {
			page = pageCount;
		}
		int fromIndex = pageSize * (page - 1) > 0 ? pageSize * (page - 1) : 0;
		int toIndex = pageSize * (page - 1) + pageSize;
		List<Ride> userRidesToShow = allRidesOfUser.subList(fromIndex,
				(toIndex > allRidesOfUser.size() ? allRidesOfUser.size() : toIndex));

		int minPagePossible = page - shift < 1 ? 1 : page - shift;

		int maxPagePossible = page + shift > pageCount ? pageCount : page + shift;

		req.setAttribute("userRides", userRidesToShow);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("page", page);
		req.setAttribute("pageSize", pageSize);
		req.setAttribute("generalSize", size);
		req.setAttribute("minPossiblePage", minPagePossible);
		req.setAttribute("maxPossiblePage", maxPagePossible);
		req.setAttribute("servletUrl", "rides");
		req.getRequestDispatcher("user-rides.jsp").forward(req, resp);
	}

	private List<Ride> showUserRides(User user) throws ServletException, IOException {
		
		RideManager rm = new RideManager();
		List<Ride> allUserRides = new ArrayList<>();
		try {
			allUserRides = rm.findAll().stream().filter(ride -> ride.getUserId() == user.getId())
					.collect(Collectors.toList());
		} catch (DBException e) {
			e.printStackTrace();
		}

		return allUserRides;
	}
}
