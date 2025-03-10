package mk.finki.ukim.mk.lab3.web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/logout")
public class LogoutController {
    @GetMapping
    public String logout(HttpServletRequest req)
    {
        req.getSession().invalidate();
        return "login";
    }

}
