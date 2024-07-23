package mk.finki.ukim.mk.lab3.repository.jpa;

import mk.finki.ukim.mk.lab3.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
}
