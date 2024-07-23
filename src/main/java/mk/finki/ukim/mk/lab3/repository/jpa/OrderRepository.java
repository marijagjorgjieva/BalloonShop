package mk.finki.ukim.mk.lab3.repository.jpa;

import mk.finki.ukim.mk.lab3.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByDateCreatedBetween(LocalDateTime endDate, LocalDateTime startDate);
    List<Order> findAllBySizeContaining(String text);
}
