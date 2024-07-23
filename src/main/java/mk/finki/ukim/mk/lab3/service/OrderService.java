package mk.finki.ukim.mk.lab3.service;

import mk.finki.ukim.mk.lab3.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    void addOrder(Order o);
    List<Order> findAll();
    void update(Order o);
    List<Order>getBetween(LocalDateTime first,LocalDateTime second);
    List<Order>findBySize(String size);
    List<Order>findByAllByBalloonName(String name);
}
