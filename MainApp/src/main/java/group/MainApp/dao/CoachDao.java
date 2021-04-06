package group.MainApp.dao;

import group.MainApp.Coach;

import java.util.Collection;

public interface CoachDao {

    Coach get(int id);

    Collection<Coach> getAll();

    int insert(Coach coach);

    void delete(int id);

    void update(Coach coach);
}
