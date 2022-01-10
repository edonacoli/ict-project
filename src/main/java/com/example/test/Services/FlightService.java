package com.example.test.Services;

import com.example.test.Models.Flight;
import com.example.test.Repository.FlightRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class FlightService {
    private static FlightService instance = new FlightService();
    private static ObservableList<Flight> items;

    public FlightService() {
        items = FXCollections.observableArrayList();
        initData();
    }

    private void initData() {
        List<Flight> dbItems = FlightRepository.getInstance().getAll();
        items.addAll(dbItems);
    }

    public static FlightService getInstance() {
        return instance;
    }

    public ObservableList<Flight> getItems() {

        return items;
    }

    public Flight getById(String key) {
        return FlightRepository.getInstance().getById(key);
    }

    public void add(Flight item) {
        if (FlightRepository.getInstance().add(item)) {
            items.add(item);
        }
    }




}
