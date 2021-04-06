package group.MainApp;

import group.MainApp.dao.CoachDao;
import group.MainApp.tracking.Event;
import group.MainApp.tracking.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
public class MainController {

    private final CoachDao dao;
    private final TrackingService trackingService;

    @Autowired
    public MainController(CoachDao dao, TrackingService trackingService) {
        this.dao = dao;
        this.trackingService = trackingService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/coaches")
    public String list(Model model, HttpServletRequest request) {
        model.addAttribute("coaches", dao.getAll());
        trackingService.save(new Event(
                request.getRemoteAddr(),
                "list",
                null,
                LocalDateTime.now()
        ));
        return "list";
    }

    @PostMapping("/coaches/{id}/delete")
    public String delete(@PathVariable("id") int id, HttpServletRequest request) {
        dao.delete(id);
        trackingService.save(new Event(
                request.getRemoteAddr(),
                "delete " + id,
                null,
                LocalDateTime.now()
        ));
        return "redirect:/coaches";
    }

    @GetMapping("/coaches/{id}")
    public String edit(Model model, @PathVariable String id, HttpServletRequest request) {
        try {
            model.addAttribute("coach", dao.get(Integer.parseInt(id)));
            trackingService.save(new Event(
                    request.getRemoteAddr(),
                    "view " + id,
                    "/coaches/" + id,
                    LocalDateTime.now()
            ));
        } catch (NumberFormatException ignore) {
        }
        return "edit";
    }

    @PostMapping("/coaches/new")
    public String insert(@RequestParam String firstName,
                         @RequestParam String lastName,
                         @RequestParam String email,
                         @RequestParam String phoneNumber,
                         HttpServletRequest request) {
        int id = dao.insert(new Coach(null, firstName, lastName, email, phoneNumber));
        trackingService.save(new Event(
                request.getRemoteAddr(),
                "new " + id,
                "/coaches/" + id,
                LocalDateTime.now()
        ));
        return "redirect:/coaches";
    }

    @PostMapping("/coaches/{id}")
    public String update(@ModelAttribute("coach") Coach coach, HttpServletRequest request) {
        dao.update(coach);
        trackingService.save(new Event(
                request.getRemoteAddr(),
                "edit " + coach.id,
                "/coaches/" + coach.id,
                LocalDateTime.now()
        ));
        return "redirect:/coaches";
    }

    @GetMapping("/logs")
    public String logs(Model model) {
        model.addAttribute("events", trackingService.get());
        return "logs";
    }
}
