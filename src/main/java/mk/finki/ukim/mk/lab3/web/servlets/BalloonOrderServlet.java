package mk.finki.ukim.mk.lab3.web.servlets;

import mk.finki.ukim.mk.lab3.model.Balloon;
import mk.finki.ukim.mk.lab3.model.Order;
import mk.finki.ukim.mk.lab3.model.ShoppingCart;
import mk.finki.ukim.mk.lab3.model.User;
import mk.finki.ukim.mk.lab3.service.BalloonService;
import mk.finki.ukim.mk.lab3.service.OrderService;
import mk.finki.ukim.mk.lab3.service.ShoppingCartService;
import mk.finki.ukim.mk.lab3.service.UserService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "BalloonOrderServlet", urlPatterns = "/BalloonOrder.do")
public class BalloonOrderServlet extends HttpServlet {
    private final BalloonService balloonService;
    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;
    private final SpringTemplateEngine springTemplateEngine;
    private final UserService userService;

    public BalloonOrderServlet(BalloonService balloonService, ShoppingCartService shoppingCartService, OrderService orderService, SpringTemplateEngine springTemplateEngine, UserService userService) {
        this.balloonService = balloonService;
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
        this.springTemplateEngine = springTemplateEngine;
        this.userService = userService;
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("size",req.getSession().getAttribute("size"));
        context.setVariable("colour",req.getSession().getAttribute("colour"));
        resp.setHeader("Content-Type", "text/html");
        this.springTemplateEngine.process("selectBalloonSize.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String balloonSize = req.getParameter("size");
        //session
        req.getSession().setAttribute("size", balloonSize);
        //context
        //context
        context.setVariable("colour",req.getSession().getAttribute("colour"));
        context.setVariable("size",req.getSession().getAttribute("size"));
        Long id = (Long) req.getSession().getAttribute("idBalloon");
        String user = (String) req.getRemoteUser();
        //make order
        User s = userService.findByName(user);
        Balloon toBeAdded= balloonService.findByID(id);
        ShoppingCart shoppingCart= new ShoppingCart(s);
        shoppingCartService.save(shoppingCart);
        Order order = new Order(toBeAdded,balloonSize,user,shoppingCart);
        req.getSession().setAttribute("o", order);
        req.getSession().setAttribute("shopcart",shoppingCart);
        resp.setHeader("Content-Type", "text/html");
        this.springTemplateEngine.process("deliveryInfo.html", context, resp.getWriter());
    }

}
