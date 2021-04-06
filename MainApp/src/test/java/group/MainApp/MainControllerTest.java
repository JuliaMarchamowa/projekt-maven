package group.MainApp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainControllerTest {

    private final MockCoachDao mockDao = new MockCoachDao();
    private final MainController controller = new MainController(mockDao, new MockTrackingService());


    @Test
    public void testInsert() {
        controller.insert("a", "b", "c", "d", new MockHttpRequest());
        assertEquals(1, mockDao.getAll().size());
    }

    @Test
    public void testUpdate() {
        Coach coach = new Coach(null, "a", "b", "c", "d");
        coach.id = mockDao.insert(coach);
        coach.email = "x";
        controller.update(coach, new MockHttpRequest());
        assertEquals("x", mockDao.get(coach.id).email);
    }

    @Test
    public void testDelete() {
        Coach coach = new Coach(null, "a", "b", "c", "d");
        coach.id = mockDao.insert(coach);
        controller.delete(coach.id, new MockHttpRequest());
        assertEquals(0, mockDao.getAll().size());
    }
}
