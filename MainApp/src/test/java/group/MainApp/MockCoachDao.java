package group.MainApp;

import group.MainApp.dao.CoachDao;

import java.util.*;

public class MockCoachDao implements CoachDao {

    private int id = 0;
    private final Map<Integer, Coach> map = new HashMap<>();

    @Override

    public Coach get(int id) {
        return map.get(id);
    }

    @Override
    public Collection<Coach> getAll() {
        return map.values();
    }

    @Override
    public int insert(Coach coach) {
        map.put(id, coach);
        return id++;
    }

    @Override
    public void delete(int id) {
        map.remove(id);
    }

    @Override
    public void update(Coach coach) {
        map.put(coach.id, coach);
    }
}
