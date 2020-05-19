package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import java.sql.*;

public class SqlTracker implements Store {

    private Connection cn;

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        String query = "INSERT INTO Items(name) VALUES(?)";
        try (PreparedStatement st = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, item.getName());
            st.executeUpdate();
            ResultSet genKeys = st.getGeneratedKeys();
            if (genKeys.next()) {
                int newID = genKeys.getInt(1);
                item.setId(String.valueOf(newID));
                return item;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean replace(String id, Item item) {
        String query = "UPDATE Items SET name = ? WHERE id = ?";
        try (PreparedStatement st = cn.prepareStatement(query)) {
            st.setString(1, item.getName());
            st.setInt(2, Integer.parseInt(id));
            int rsl = st.executeUpdate();
            if (rsl > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        String query = "DELETE FROM Items WHERE id = ?";
        try (PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1, Integer.parseInt(id));
            int rsl = st.executeUpdate();
            if (rsl > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Item> findAll() {
        List<Item> rsl = new ArrayList<>();
        try (Statement st = cn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT id, name FROM Items");
            while (rs.next()) {
                Item it = new Item(rs.getString(2));
                it.setId(String.valueOf(rs.getInt(1)));
                rsl.add(it);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> rsl = new ArrayList<>();
        String query = "SELECT id, name FROM Items WHERE name = ?";
        try (PreparedStatement st = cn.prepareStatement(query)) {
            st.setString(1, key);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Item it = new Item(rs.getString(2));
                it.setId(String.valueOf(rs.getInt(1)));
                rsl.add(it);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public Item findById(String id) {
        String query = "SELECT id, name FROM Items WHERE id = ?";
        try (PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1, Integer.parseInt(id));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Item it = new Item(rs.getString(2));
                it.setId(String.valueOf(rs.getInt(1)));
                return it;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
