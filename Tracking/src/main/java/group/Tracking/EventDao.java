package group.Tracking;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class EventDao {

    private final Connection c;

    EventDao() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:events.db");
            Statement s = c.createStatement();

            s.executeUpdate("DROP TABLE IF EXISTS events");
            s.executeUpdate(
                    "CREATE TABLE events" +
                            "(event TEXT NOT NULL," +
                            "timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)"
            );

            s.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getLast20() {
        try (Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery("SELECT event FROM events ORDER BY timestamp DESC LIMIT 20");

            List<String> l = new ArrayList<>();
            while (rs.next()) {
                l.add(rs.getString(1));
            }
            return l;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(String event) {

        try (PreparedStatement s = c.prepareStatement("INSERT INTO events (event) VALUES (?)")) {
            s.setString(1, event);
            s.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
