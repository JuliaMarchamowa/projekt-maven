package group.Tracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class Controller {

    @Autowired
    private EventDao dao;

    @PostMapping("/tracking/event")
    public void save(@RequestBody String event) {
        dao.save(event);
    }

    @GetMapping("/tracking/logs")
    public String get() {
        return dao.getLast20().stream().collect(Collectors.joining(",", "[", "]"));
    }
}
