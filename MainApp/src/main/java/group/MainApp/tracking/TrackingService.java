package group.MainApp.tracking;

import java.util.List;

public interface TrackingService {
    void save(Event event);

    List<Event> get();
}
