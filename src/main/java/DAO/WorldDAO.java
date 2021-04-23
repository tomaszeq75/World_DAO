package DAO;

import models.City;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WorldDAO {

    private Connection connection;

    public List<City> getAll() {
        List<City> items = new ArrayList<>();
        String query = "select * from " + "city;";

        openConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                items.add(getCityFromResultset(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        closeConnection();

        return items;
    }

    public List<City> getByName(String name) {
        String query = "select * from city where `name` like ?";
        return getFromQuery(query, name);
    }

    public City getById(int id) {
        String query = "select * from city where id = ?";
        return getFromQuery(query, String.valueOf(id)).get(0);
    }

    public List<City> getByCountryCode(String code) {
        String query = "select * from city where CountryCode = ?";
        return getFromQuery(query, code);
    }

    public List<City> getByPopulationBetween(int from,  int to) {
        String query = "select * from city where population between ? and ?";
        return getFromQuery(query, String.valueOf(from), String.valueOf(to));
    }

    private List<City> getFromQuery(String query, String... param) {
        List<City> items = new ArrayList<>();

        openConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < param.length; i++) {
                statement.setString(i + 1, param[i]);
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                items.add(getCityFromResultset(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        closeConnection();
        return items;
    }

    public City getCityFromResultset(ResultSet resultSet) throws SQLException {
        City city = new City();
        city.setId(resultSet.getLong("ID"));
        city.setName(resultSet.getString("Name"));
        city.setCountryCode(resultSet.getString("CountryCode"));
        city.setDistrict(resultSet.getString("District"));
        city.setPopulation(resultSet.getInt("Population"));
        return city;
    }

    private void openConnection() {
        String url = "jdbc:mysql://localhost:3306/world?useTimezone=true&serverTimezone=UTC";
        try {
            connection = DriverManager.getConnection(url, "root", "root");
            System.out.println("connected");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("disconnected");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
