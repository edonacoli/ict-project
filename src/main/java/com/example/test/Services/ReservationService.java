package com.example.test.Services;

import com.example.test.Models.Reservation;
import com.example.test.Repository.ReservationRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ReservationService {

    private static ReservationService instance = new ReservationService();
    private static ObservableList<Reservation> items;

    public ReservationService() {
        items = FXCollections.observableArrayList();
        initData();
    }

    private void initData() {
        List<Reservation> dbItems = ReservationRepository.getInstance().getAll();
        items.addAll(dbItems);
    }

    public static ReservationService getInstance() {
        return instance;
    }

    public ObservableList<Reservation> getItems() {

        return items;
    }

    public void add(Reservation item) {
        if (ReservationRepository.getInstance().add(item)) {
            items.add(item);
        }
    }
    public List<Reservation> getByFlightId(String key) {
        List<Reservation> result = ReservationRepository.getInstance().getByFlightId(key);
       return result;
    }
}
