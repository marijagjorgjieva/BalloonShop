package mk.finki.ukim.mk.lab3.service.impl;

import mk.finki.ukim.mk.lab3.model.Order;
import mk.finki.ukim.mk.lab3.repository.jpa.OrderRepository;
import mk.finki.ukim.mk.lab3.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImp( OrderRepository orderRepository1) {
        this.orderRepository = orderRepository1;

    }
    @Override
    public void addOrder(Order o) {
        orderRepository.save(o);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public void update(Order o) {
        orderRepository.save(o);
    }

    @Override
    public List<Order> getBetween(LocalDateTime first, LocalDateTime second) {
        return orderRepository.findAllByDateCreatedBetween(first,second);
    }

    @Override
    public List<Order> findBySize(String size) {
        return orderRepository.findAllBySizeContaining(size);
    }

    @Override
    public List<Order> findByAllByBalloonName(String name) {
        return orderRepository.findAll().stream().filter(x->x.getBalloonColor().compareTo(name)==0).collect(Collectors.toList());
    }


}
