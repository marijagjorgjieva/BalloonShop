package mk.finki.ukim.mk.lab3.web.Controller;

import mk.finki.ukim.mk.lab3.model.User;
import mk.finki.ukim.mk.lab3.model.exceptions.WrongUsernameOrPasswordException;
import mk.finki.ukim.mk.lab3.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage(@RequestParam(required = false) String error, Model model) {

        return "login";
    }


    @PostMapping
    public String login(HttpServletRequest request, Model model) {
        User user = null;
        try {
            user = this.authService.login(request.getParameter("username"), request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/balloons";
        } catch (WrongUsernameOrPasswordException c) {
            return "redirect:/login?error=1";
        }


    }
}

