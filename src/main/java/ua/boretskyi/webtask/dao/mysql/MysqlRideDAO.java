package ua.boretskyi.webtask.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ua.boretskyi.webtask.dao.RideDAO;
import ua.boretskyi.webtask.dao.entity.Ride;
import ua.boretskyi.webtask.dao.util.ConnectionPool;
import ua.boretskyi.webtask.logic.DBException;

public class MysqlRideDAO implements RideDAO {

	private static final String PS_INSERT_RIDE_WITH_ALL_NEEDED_DATA = "INSERT INTO ride "
			+ "(place_from, place_to, people_in_ride, expected_start_time, expected_finish_time, car_id, user_id, driver_id, price) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String PS_FIND_RIDE_BY_ID = "SELECT * FROM ride WHERE id=?";
	private static final String PS_UPDATE_ALL_REQUIRED_FIELDS_BY_ID = "UPDATE ride"
			+ "SET place_from=?,place_to=?,people_in_ride=?, expected_finish_time=?, car_id=?, user_id=?,driver_id=?,price=?"
			+ "WHERE id=?";
	private static final String PS_DELETE_ROW_BY_ID = "DELETE FROM ride WHERE id=?";
	private static final String SELECT_ALL = "SELECT * FROM ride";

	private static ConnectionPool pool = ConnectionPool.getInstance();

	@Override
	public void createRide(Ride ride) throws DBException {
		Connection conn = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(PS_INSERT_RIDE_WITH_ALL_NEEDED_DATA, Statement.RETURN_GENERATED_KEYS);
			prepareStatementWithAllRequiredData(ride, ps);

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			rs.next();
			ride.setRideId(rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();

			throw new DBException("failed to insert a ride " + ride, e);
		} finally {
			close(rs);
			close(ps);
			close(conn);
		}
	}

	@Override
	public void createRides(Ride... rides) throws DBException {
		Connection conn = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(PS_INSERT_RIDE_WITH_ALL_NEEDED_DATA, Statement.RETURN_GENERATED_KEYS);
			for (Ride ride : rides) {
				prepareStatementWithAllRequiredData(ride, ps);

				ps.executeUpdate();

				rs = ps.getGeneratedKeys();
				rs.next();
				ride.setRideId(rs.getInt(1));
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new DBException("failed to insert an array of rides" + Arrays.toString(rides), e);
		} finally {
			close(rs);
			close(ps);
			close(conn);
		}
	}

	@Override
	public Ride findRide(int rideId) throws DBException {
		Connection conn = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(PS_FIND_RIDE_BY_ID);
			ps.setInt(1, rideId);
			rs = ps.executeQuery();
			rs.next();
			Ride ride = fillInAllRideDataFromResultSet(rs);
			ride.setRideId(rideId);
			return ride;
		} catch (SQLException e) {
			e.printStackTrace();

			throw new DBException("failed to find a ride with id " + rideId, e);
		} finally {
			close(rs);
			close(ps);
			close(conn);
		}
	}

	@Override
	public void updateRide(Ride ride) throws DBException {
		int id = ride.getRideId();
		updateRide(id, ride);
	}

	@Override
	public void updateRide(int rideId, Ride ride) throws DBException {
		Connection conn = pool.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(PS_UPDATE_ALL_REQUIRED_FIELDS_BY_ID);
			prepareStatementWithAllRequiredData(ride, ps);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

			throw new DBException("failed to update a ride " + ride + " with id " + rideId, e);
		} finally {
			close(ps);
			close(conn);
		}
	}

	@Override
	public void deleteRide(Ride ride) throws DBException {
		Connection conn = pool.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(PS_DELETE_ROW_BY_ID);
			ps.setInt(1, ride.getRideId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

			throw new DBException("failed to delete a ride " + ride, e);
		} finally {
			close(ps);
			close(conn);
		}

	}

	@Override
	public List<Ride> findAll() throws DBException {
		Connection conn = pool.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<Ride> result = new ArrayList<>();
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(SELECT_ALL);
			while (rs.next()) {
				Ride ride = fillInAllRideDataFromResultSet(rs);
				result.add(ride);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();

			throw new DBException("failed to find all records in ride table. Exception occured with list " + result, e);
		} finally {
			close(rs);
			close(statement);
			close(conn);
		}
	}

	private void close(Statement statement) {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void close(Connection connection) {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void close(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void prepareStatementWithAllRequiredData(Ride ride, PreparedStatement ps) throws SQLException {
		int k = 1;
		ps.setString(k++, ride.getPlaceFrom());
		ps.setString(k++, ride.getPlaceTo());
		ps.setInt(k++, ride.getPeopleInRide());
		ps.setTimestamp(k++, ride.getExpectedStartTime());
		ps.setTimestamp(k++, ride.getExpectedFinishTime());
		ps.setInt(k++, ride.getCarId());
		ps.setInt(k++, ride.getUserId());
		ps.setInt(k++, ride.getDriverId());
		ps.setDouble(k++, ride.getPrice());
	}

	private Ride fillInAllRideDataFromResultSet(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String placeFrom = rs.getString("place_from");
		String placeTo = rs.getString("place_to");
		int peopleInRide = rs.getInt("people_in_ride");
		Timestamp created = rs.getTimestamp("time_created");
		Timestamp expectedStartTime = rs.getTimestamp("expected_start_time");
		Timestamp finish = rs.getTimestamp("expected_finish_time");
		int carId = rs.getInt("car_id");
		int userId = rs.getInt("user_id");
		int driverId = rs.getInt("driver_id");
		String description = rs.getString("description");
		double price = rs.getDouble("price");

		Ride ride = new Ride(placeFrom, placeTo, peopleInRide, expectedStartTime, finish, carId, userId, driverId,
				price);
		if (description != null)
			ride.setDescription(description);

		ride.setRideId(id);
		ride.setTimeCreated(created);
		ride.setStatus(Ride.Status.valueOf(rs.getString("ride_status").toUpperCase()));
		return ride;
	}

}
