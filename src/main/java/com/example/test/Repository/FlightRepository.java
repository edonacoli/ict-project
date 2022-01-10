package com.example.test.Repository;

import com.example.test.Models.Flight;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightRepository extends DataConnection implements CrudRepository<Flight, String>, ToObjectable<Flight> {

    private static FlightRepository instance = new FlightRepository();

    public static FlightRepository getInstance() {
        return instance;
    }

    private FlightRepository() {

    }


    @Override
    public boolean add(Flight item) {
        String query = "INSERT INTO dbo.Flights (flight_number, flight_date, departure_time, arrival_time, departure_city, arrival_city, available_seats) values (?, ?, ?, ?, ?, ?, ?)";
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, item.getFlightNumber());
            statement.setString(2,  item.getFlightDate());
            statement.setString(3, item.getDepartureTime());
            statement.setString(4, item.getArrivalTime());
            statement.setString(5, item.getDepartureCity());
            statement.setString(6, item.getArrivalCity());
            statement.setInt(7, item.getAvailableSeats());
            return statement.executeUpdate() >= 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Flight item) {
        return false;
    }

    @Override
    public boolean deleteById(String key) {
        return false;
    }

    @Override
    public Flight getById(String key) {
        String query = "SELECT * FROM dbo.Flights WHERE flight_number = ?";

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, key);
            Flight item = null;
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                item = new Flight(resultSet.getString("flight_number"),resultSet.getString("flight_date"),resultSet.getString("departure_time"), resultSet.getString("arrival_time"), resultSet.getString("departure_city"), resultSet.getString("arrival_city"), resultSet.getInt("available_seats"));
            }
            statement.close();
            connection.close();

            return item;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Flight> getAll() {
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM dbo.flights")
        ) {
            List<Flight> items = null;
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
    public Flight toObject(ResultSet resultSet) throws SQLException {
        String flight_no = resultSet.getString("flight_number");
        String flight_date = resultSet.getString("flight_date");
        String departure_time = resultSet.getString("departure_time");
        String arrival_time = resultSet.getString("arrival_time");
        String  departure_city = resultSet.getString("departure_city");
        String arrival_city = resultSet.getString("arrival_city");
        int  available_seats = resultSet.getInt("available_seats");
        return new Flight(flight_no, flight_date, departure_time, arrival_time, departure_city, arrival_city, available_seats);
    }
}
