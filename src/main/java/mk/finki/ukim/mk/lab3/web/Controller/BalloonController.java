package mk.finki.ukim.mk.lab3.web.Controller;

import mk.finki.ukim.mk.lab3.model.Balloon;
import mk.finki.ukim.mk.lab3.model.Order;
import mk.finki.ukim.mk.lab3.model.User;
import mk.finki.ukim.mk.lab3.service.BalloonService;
import mk.finki.ukim.mk.lab3.service.ManufacturerService;
import mk.finki.ukim.mk.lab3.service.OrderService;
import mk.finki.ukim.mk.lab3.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/balloons")
public class BalloonController {

    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;
    private final OrderService orderService;
    private final UserService userService;


    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService, OrderService orderService, UserService userService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
        this.orderService = orderService;

        this.userService = userService;
    }

    @GetMapping()
    public String getBalloonsPage( String error, Model model) {

        model.addAttribute("balloons", balloonService.listAll());
        model.addAttribute("man", manufacturerService.listAll());
        return "listBalloons";
    }

    @PostMapping("/choose/{id}")
    public String chooseBalloon(HttpServletRequest req, Model model, @PathVariable Long id) {
        model.addAttribute("balloons", balloonService.listAll());
        model.addAttribute("man", manufacturerService.listAll());
        req.getSession().setAttribute("Order", 0);
        //session
        Balloon chosen = balloonService.findByID(id);
        String colour = chosen.getDescription();
        req.getSession().setAttribute("colour", colour);
        req.getSession().setAttribute("idBalloon", id);
        //context
        model.addAttribute("colour", req.getSession().getAttribute("colour"));
        return "selectBalloonSize";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveBalloon(
            @RequestParam String name,
            @RequestParam String desc,
            @RequestParam(required = false) Long id,
            @RequestParam Long manufacturer,
            @RequestParam Integer edit
    ) {

        if (edit == 0 && balloonService.searchByNameOrDescription(name, desc).size() == 0) {
            balloonService.addBalloon(new Balloon(name, desc, manufacturerService.find(manufacturer)));
        } else if (edit == 1) {
            balloonService.editBalloon(id, name, desc, manufacturer);
        }
        return "redirect:/balloons";

    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteBalloon(@PathVariable Long id) {
        this.balloonService.deleteBalloon(id);
        return "redirect:/balloons";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getEditBalloonPage(@PathVariable Long id, Model model) {
        model.addAttribute("man", manufacturerService.listAll());
        if (balloonService.containsID(id)) {
            Balloon toBeEdited = balloonService.findByID(id);
            model.addAttribute("BalloonID", toBeEdited.getId());
            model.addAttribute("BalloonName", toBeEdited.getName());
            model.addAttribute("BalloonDesc", toBeEdited.getDescription());
            model.addAttribute("BalloonMan", toBeEdited.getManufacturer());
            return "add-balloon";
        } else {
            return "redirect:/balloons?error=1";
        }


    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddBalloonPage(Model model) {
        model.addAttribute("man", manufacturerService.listAll());
        return "add-balloon";
    }

    @GetMapping("/orders")
    public String ShowOrders(Model model, HttpServletRequest request) {
        User user = (User) userService.findByName(request.getRemoteUser());
        List<Order> ordlersList = orderService.findAll().stream().filter(x -> x.getUsername().compareTo(user.getUsername()) == 0).collect(Collectors.toList());
        model.addAttribute("orders", ordlersList);
        return "userOrders";
    }

    @PostMapping("/orders/withdate")
    public String ShowOrdersWithDate(@RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                     @RequestParam("to")
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
                                     Model model, HttpServletRequest request) {
        User user = (User) userService.findByName(request.getRemoteUser());
        List<Order> ordlersList = orderService.getBetween(from, to);
        model.addAttribute("orders", ordlersList);

        return "userOrders";
    }

    @PostMapping("/orders/namecontains")
    public String ShowOrdersContainingTextInName(@RequestParam String textInName,Model model, HttpServletRequest request) {

        List<Order> ordlersList = orderService.findBySize(textInName);
        model.addAttribute("orders", ordlersList);

        return "userOrders";
    }
    @PostMapping("/orders/descriptioncontains")
    public String ShowOrdersContainingTextInDescription(@RequestParam String textInDescription,Model model, HttpServletRequest request) {

        List<Order> ordlersList = orderService.findByAllByBalloonName(textInDescription);
        model.addAttribute("orders", ordlersList);
        return "userOrders";
    }

}
