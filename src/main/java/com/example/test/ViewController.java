package com.example.test;

import com.example.test.Models.Flight;
import com.example.test.Models.Reservation;
import com.example.test.Services.FlightService;
import com.example.test.Services.ReservationService;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;



import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ViewController implements Initializable {
    ReservationService reservationService = new ReservationService();
    FlightService flightService = new FlightService();


    @FXML
    private TextField flightNumField, flightDateField, departureTimeField,
            arrivalTimeField, departureCityField, arrivalCityField,
            availableSeatsField, flightNumberRes,  PassengerIDRes,
            passengerNameRes, seatNumberRes, flightNumberOnMapField,
            specificFlightTextField;

    @FXML
    private Pane newFlightPane, newReservationPane, seatMapPane,
            listAllFlightsPane, listAllReservationsPane,
            listAllFlightReservationsPane, exitPane;

    @FXML
    private TextArea seatMapArea, listAllFlightsArea, listOfAllPassengerReservations,
            listOfSpecificPassengerReservations;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void updateSeatMap() {

        String[] seats = {"A", "B", "C", "D", "E", "F", "G"};
        List<String> takenSeat = new ArrayList<>();
        int availableSeat;
        String flightNumberOnMap = flightNumberOnMapField.getText();
        List<Reservation> getRes = ReservationService.getInstance().getByFlightId(flightNumberOnMap);
        System.out.println(getRes);
        getRes.forEach(f -> takenSeat.add(f.getSeatNumber()));

        Flight searchedFlight = FlightService.getInstance().getById(flightNumberOnMap);
        availableSeat = searchedFlight.getAvailableSeats();
        int rowSeats    = availableSeat / 7;
        int lastRowSeat = availableSeat % 7;

        seatMapArea.setText("");


                    for(int i = 1; i <= rowSeats; i++) {
                        for(int j = 1; j <= 7; j++) {
                            System.out.println(i + "" + seats[j - 1] + ": " + takenSeat.contains(i + "" + seats[j - 1]));
                            if(!takenSeat.contains(i + "" + seats[j - 1])) {
                                seatMapArea.appendText(i + seats[j - 1] + "\t");
                            } else {
                                seatMapArea.appendText(  "  \t");
                            }
                        }
                        seatMapArea.appendText("\n");
                    }

                    if(lastRowSeat != 0) {
                        for (int i = 1; i <= lastRowSeat; i++) {
                            if (!takenSeat.contains((rowSeats + 1) + "" + seats[i - 1])) {
                                seatMapArea.appendText((rowSeats + 1) + seats[i - 1] + "\t");
                            } else {
                                seatMapArea.appendText(  "  \t");
                            }
                        }
                    }

    }


    @FXML
    void addFlight() {
        Flight newFlightItem = new Flight(flightNumField.getText(), flightDateField.getText(), departureTimeField.getText(), arrivalTimeField.getText(), departureCityField.getText(), arrivalCityField.getText(), Integer.parseInt(availableSeatsField.getText()));
        FlightService.getInstance().add(newFlightItem);
        flightNumField.clear();
        flightDateField.clear();
        departureTimeField.clear();
        arrivalTimeField.clear();
        departureCityField.clear();
        arrivalCityField.clear();
        availableSeatsField.clear();
    }

    @FXML
    void addNewReservation() {
        Reservation newReservationItem = new Reservation(flightNumberRes.getText(), PassengerIDRes.getText(), passengerNameRes.getText(),  seatNumberRes.getText() );
        ReservationService.getInstance().add(newReservationItem);
        flightNumberRes.clear();
        PassengerIDRes.clear();
        passengerNameRes.clear();
        seatNumberRes.clear();
    }

    @FXML
    void displaySeatMapButtonAction() {
        seatMapPane.toFront();  // brings seatMapPane to front at button press
    }

    @FXML
    void exitButtonAction() {
        exitPane.toFront();     // brings exitPane to front at button press
    }

    @FXML
    void listAllFlightReservationsAction() {
        listAllFlightReservationsPane.toFront(); // brings this pane to front at button press
    }

    @FXML
    void listAllReservationsButtonAction() {
        listAllReservationsPane.toFront();


        ObservableList<Reservation> res = reservationService.getItems();

        listOfAllPassengerReservations.setText("");
        listOfAllPassengerReservations.appendText("Flight Number\tPassenger ID\tPassenger Name\t" +
                "Seat Number"+"\n");
        res.forEach(reservation -> {
            String str = "\t" + reservation.getFlightNumber() + "\t\t\t" + reservation.getPassengerId() + "\t\t\t"
                    + reservation.getPassengerName() + "\t\t\t" + reservation.getSeatNumber();
            listOfAllPassengerReservations.appendText(str+"\n");
        });


    }

    @FXML
    void listAllFlightsButtonAction() {

        listAllFlightsPane.toFront();  // brings this pane to front at button press

        ObservableList<Flight> res = flightService.getItems();

        listAllFlightsArea.setText("");
        listAllFlightsArea.appendText("Flight Number\t  Flight Date\tFlight Departure Time\t" +
                "Flight Arrival Time\tFlight Departure City\tFlight Arrival City\tFlight Available Seats" + "\n");
        res.forEach(flight -> {
            String str = "\t" + flight.getFlightNumber() + "\t\t" +  flight.getFlightDate() + " \t\t\t" + flight.getDepartureTime() +
                     "\t\t\t" +  flight.getArrivalTime() + "\t\t\t" + flight.getDepartureCity() + "\t\t\t" + flight.getArrivalCity()
                    + "\t\t\t" +  flight.getAvailableSeats();
            listAllFlightsArea.appendText(str+"\n");
        });



    }

    @FXML
    void newFlightButtonAction() {
        newFlightPane.toFront();        // brings newFlightPane to front at button press
    }

    @FXML
    void newReservationButtonAction() {
        newReservationPane.toFront();   // brings this pane to front at button press
    }

    @FXML
    void exitApplication() {
        Platform.exit();    // when exitApplication button is pressed, the application exits
    }


    @FXML
    void showSpecificFlightReservations() {
        listOfSpecificPassengerReservations.toFront();

        String specificFlight = specificFlightTextField.getText();
        List<Reservation> res = ReservationService.getInstance().getByFlightId(specificFlight);

        listOfSpecificPassengerReservations.setText("");
        listOfSpecificPassengerReservations.appendText("Flight Number\t\tPassenger ID\t\tPassenger Name\t\t" +
                "Seat Number \n" );
        res.forEach(reservation -> {
            System.out.println(reservation);
            String str = reservation.getFlightNumber() + "\t\t\t" + reservation.getPassengerId() + "\t\t\t"
                    + reservation.getPassengerName() + "\t\t\t" + reservation.getSeatNumber();
            listOfSpecificPassengerReservations.appendText(str+"\n");
        });







    }
}
