package com.example.test.Repository;
import com.example.test.Models.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository extends DataConnection implements CrudRepository<Reservation, String>, ToObjectable<Reservation> {

    private static ReservationRepository instance = new ReservationRepository();

    public static ReservationRepository getInstance() {
        return instance;
    }

    private ReservationRepository() {
    }

    @Override
    public boolean add(Reservation item) {
        String query = "INSERT INTO dbo.Reservations (flight_number, passenger_id, passenger_name, seat_number ) values (?, ?, ?, ?)";
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, item.getFlightNumber());
            statement.setString(2, item.getPassengerId());
            statement.setString(3, item.getPassengerName());
            statement.setString(4, item.getSeatNumber());
            return statement.executeUpdate() >= 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Reservation item) {
        return false;
    }

    @Override
    public boolean deleteById(String key) {
        return false;
    }

    @Override
    public Reservation getById(String key) {

        return null;
    }

    public List<Reservation> getByFlightId(String key) {
        String query = "SELECT * FROM dbo.Reservations WHERE flight_number = ?";

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            System.out.println("key " + key);
            statement.setString(1, key);
            List<Reservation> items = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                items.add(toObject(resultSet));
            }
            statement.close();
            connection.close();

            return items;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reservation> getAll() {
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM dbo.reservations")
        ) {
            List<Reservation> items = null;
            ResultSet resultSet = statement.executeQuery();
            items = new ArrayList<>();
            while (resultSet.next()) {
                items.add(toObject(resultSet));
            }

            return items;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public Reservation toObject(ResultSet resultSet) throws SQLException {
        String flight_no = resultSet.getString("flight_number");
        String passenger_id = resultSet.getString("passenger_id");
        String passenger_name = resultSet.getString("passenger_name");
        String seat_number = resultSet.getString("seat_number") ;

        return new Reservation(flight_no, passenger_id, passenger_name, seat_number);
    }
}
