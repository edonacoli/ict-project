package com.example.test.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ToObjectable<T> {
    T toObject(ResultSet resultSet) throws SQLException;
}
