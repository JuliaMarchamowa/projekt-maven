package group.MainApp;

import group.MainApp.tracking.Event;
import group.MainApp.tracking.TrackingService;

import java.util.Collections;
import java.util.List;

public class MockTrackingService implements TrackingService {

    @Override
    public void save(Event event) {
    }

    @Override
    public List<Event> get() {
        return Collections.emptyList();
    }
}
