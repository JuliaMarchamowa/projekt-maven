package group.MainApp.dao;

import group.MainApp.Coach;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CoachDaoImpl implements CoachDao {

    private static final String select = "SELECT * FROM coaches";
    private static final String selectWhere = "SELECT * FROM coaches WHERE id = ?";
    private static final String insert = "INSERT INTO coaches(id, first_name, last_name, email, phone_number) VALUES(?, ?, ?, ?, ?)";
    private static final String delete = "DELETE FROM coaches WHERE id = ?";
    private static final String update = "UPDATE coaches SET first_name = ?, last_name = ?, email = ?, phone_number = ? WHERE id = ?";

    private final Connection c;
    private int currentId = 0;

    CoachDaoImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:coaches.db");
            Statement s = c.createStatement();

            s.executeUpdate("DROP TABLE IF EXISTS coaches");
            s.executeUpdate(
                    "CREATE TABLE coaches" +
                            "(id INTEGER PRIMARY KEY," +
                            "first_name TEXT NOT NULL," +
                            "last_name TEXT NOT NULL," +
                            "email TEXT NOT NULL," +
                            "phone_number TEXT NOT NULL)"
            );

            s.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Coach get(int id) {
        try (PreparedStatement ps = c.prepareStatement(selectWhere)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Coach(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone_number")
                );
            } else {
                return new Coach();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Coach> getAll() {
        try (Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(select)) {

            List<Coach> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Coach(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone_number")
                ));
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(Coach coach) {
        try (PreparedStatement ps = c.prepareStatement(insert)) {
            ps.setInt(1, ++currentId);
            ps.setString(2, coach.firstName);
            ps.setString(3, coach.lastName);
            ps.setString(4, coach.email);
            ps.setString(5, coach.phoneNumber);
            ps.executeUpdate();
            return currentId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = c.prepareStatement(delete)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Coach coach) {
        try (PreparedStatement ps = c.prepareStatement(update)) {
            ps.setString(1, coach.firstName);
            ps.setString(2, coach.lastName);
            ps.setString(3, coach.email);
            ps.setString(4, coach.phoneNumber);
            ps.setInt(5, coach.id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
