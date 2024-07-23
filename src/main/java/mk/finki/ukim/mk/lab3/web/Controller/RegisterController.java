package mk.finki.ukim.mk.lab3.web.Controller;

import mk.finki.ukim.mk.lab3.model.User;
import mk.finki.ukim.mk.lab3.service.AuthService;
import mk.finki.ukim.mk.lab3.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage( Model model) {
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam("localDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                       LocalDate localDate
                             ) {
        this.userService.addUser(new User(username,name,surname,password, localDate));
        return "redirect:/login";

    }
}


