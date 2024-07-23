package mk.finki.ukim.mk.lab3.web.servlets;

import mk.finki.ukim.mk.lab3.model.Order;
import mk.finki.ukim.mk.lab3.model.ShoppingCart;
import mk.finki.ukim.mk.lab3.service.BalloonService;
import mk.finki.ukim.mk.lab3.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "ConfirmationInfoServlet", urlPatterns = "/ConfirmationInfo")
public class ConfirmationInfoServlet extends HttpServlet {
    private final BalloonService balloonService;
    private final OrderService orderService;
    private final SpringTemplateEngine springTemplateEngine;

    public ConfirmationInfoServlet(BalloonService balloonService, OrderService orderService, SpringTemplateEngine springTemplateEngine) {
        this.balloonService = balloonService;
        this.orderService = orderService;
        this.springTemplateEngine = springTemplateEngine;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("colour", req.getSession().getAttribute("colour"));
        context.setVariable("size", req.getSession().getAttribute("size"));
        context.setVariable("name", req.getSession().getAttribute("name"));
        context.setVariable("delivery", req.getSession().getAttribute("delivery"));
        context.setVariable("ipaddr", req.getHeader("Origin"));
        context.setVariable("browser", req.getHeader("User-Agent"));

        resp.setHeader("Content-Type", "text/html");
        this.springTemplateEngine.process("selectBalloonSize.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String clientName = req.getParameter("clientName");
        String delivery = req.getParameter("delivery");
        LocalDateTime date= LocalDateTime.parse(req.getParameter("date"));;
        //session
        req.getSession().setAttribute("name", clientName);
        req.getSession().setAttribute("delivery", delivery);
        Order o = (Order) req.getSession().getAttribute("o");
        ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute("shopcart");
        o.setDateCreated(date);
        orderService.addOrder(o);
        shoppingCart.add(o);

        //context
        context.setVariable("colour", req.getSession().getAttribute("colour"));
        context.setVariable("size", req.getSession().getAttribute("size"));
        context.setVariable("name", req.getSession().getAttribute("name"));
        context.setVariable("delivery", req.getSession().getAttribute("delivery"));
        context.setVariable("ipaddr", req.getHeader("Origin"));
        context.setVariable("cart", o.getDateCreated());

        resp.setHeader("Content-Type", "text/html");
        this.springTemplateEngine.process("confirmationInfo.html", context, resp.getWriter());
    }
}

