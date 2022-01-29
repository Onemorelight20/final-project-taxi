package ua.boretskyi.webtask.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.boretskyi.webtask.dao.CarDAO;
import ua.boretskyi.webtask.dao.entity.Car;
import ua.boretskyi.webtask.dao.entity.Car.Status;
import ua.boretskyi.webtask.dao.util.ConnectionPool;
import ua.boretskyi.webtask.logic.DBException;

public class MysqlCarDAO implements CarDAO {

	private static final String PS_INSERT_CAR_BY_MODEL_STATUS_DRIVER_ID_SEATS_TYPE ="INSERT INTO car VALUES (DEFAULT, ?, ?, ?, ?, ?)";
	private static final String PS_UPDATE_CAR_BY_ID = "UPDATE car SET car_model=?,car_status=?,driver_id=?,seats_available=?,car_type=? WHERE id=?";
	private static final String PS_UPDATE_STATUS_BY_ID = "UPDATE car set car_status=? where id=?"; 
	private static final String PS_FIND_CAR_BY_ID = "SELECT * FROM car WHERE id=?";
	private static final String PS_FIND_ALL_CARS = "SELECT * FROM car";
	private static final String PS_DELETE_CAR_BY_ID = "DELETE FROM car WHERE id=?"; 
	private static ConnectionPool pool = ConnectionPool.getInstance();
	
	@Override
	public void createCar(Car car) throws DBException {
		Connection conn = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(PS_INSERT_CAR_BY_MODEL_STATUS_DRIVER_ID_SEATS_TYPE, PreparedStatement.RETURN_GENERATED_KEYS);
			fillPreparedStatementWithRequiredCarInfo(car, ps);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();
			car.setId(rs.getInt(1));
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new DBException("failed to prepare a statement", e);
		} finally {
			close(rs);
			close(ps);
			close(conn);
		}
	}

	

	@Override
	public Car findCar(int carId) throws DBException {
		Connection conn = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(PS_FIND_CAR_BY_ID);
			ps.setInt(1, carId);
			rs = ps.executeQuery();
			rs.next();
			Car car = fillInAllCarInfoFromResultSet(rs);
			return car;
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new DBException("failed to find a car with id " + carId, e);
		} finally {
			close(rs);
			close(ps);
			close(conn);
		}
	}

	
	@Override
	public void setCarStatus(int id, Status status) throws DBException {
		Connection conn = pool.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(PS_UPDATE_STATUS_BY_ID);
			ps.setString(1, status.toString().toLowerCase());
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new DBException("failed to update car status to " + status.toString() + " with id " + id , e);
		}
	}
	
	@Override
	public void updateCar(Car car) throws DBException {
		updateCar(car.getId(), car);
	}

	@Override
	public void updateCar(int id, Car car) throws DBException {
		Connection conn = pool.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(PS_UPDATE_CAR_BY_ID);
			fillPreparedStatementWithRequiredCarInfo(car, ps);
			ps.executeUpdate();
			car.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new DBException("failed to update car " + car + " with id " + id, e);
		} finally {
			close(ps);
			close(conn);
		}
	}

	@Override
	public void deleteCar(int carId) throws DBException {
		Connection conn = pool.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(PS_DELETE_CAR_BY_ID);
			ps.setInt(1, carId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new DBException("failed to delete a car with id " + carId, e);
		} finally {
			close(ps);
			close(conn);
		}	
	}

	@Override
	public Car findCar(Car car) throws DBException {
		return findCar(car.getId());
	}

	@Override
	public List<Car> findAll() throws DBException {
		Connection conn = pool.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<Car> result = new ArrayList<>();
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(PS_FIND_ALL_CARS);
			while(rs.next()) {
				Car car = fillInAllCarInfoFromResultSet(rs);
				result.add(car);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new DBException("failed to find all cars. list before exception: " + result, e);
		} finally {
			close(rs);
			close(statement);
			close(conn);
		}
	}
	
	private void fillPreparedStatementWithRequiredCarInfo(Car car, PreparedStatement ps) throws SQLException {
		ps.setString(1, car.getModel());
		ps.setString(2, car.getStatus().toString());
		ps.setInt(3, car.getDriverId());
		ps.setInt(4, car.getSeatsAvailable());
		ps.setString(5, car.getType().toString());
	}

	private Car fillInAllCarInfoFromResultSet(ResultSet rs) throws SQLException {
		Car car = new Car();
		car.setId(rs.getInt("id"));
		car.setModel(rs.getString("car_model"));
		car.setStatus(Car.Status.valueOf(rs.getString("car_status").toUpperCase()));
		car.setDriverId(rs.getInt("driver_id"));
		car.setSeatsAvailable(rs.getInt("seats_available"));
		car.setType(Car.Type.valueOf(rs.getString("car_type").toUpperCase()));
		return car;
	}

	private void close(Statement statement) {
		try {
			if(statement != null)
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void close(Connection connection) {
		try {
			if(connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void close(ResultSet rs) {
		try {
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
