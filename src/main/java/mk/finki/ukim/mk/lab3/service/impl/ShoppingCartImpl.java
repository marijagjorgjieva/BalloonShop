package mk.finki.ukim.mk.lab3.service.impl;

import mk.finki.ukim.mk.lab3.model.ShoppingCart;
import mk.finki.ukim.mk.lab3.repository.jpa.ShoppingCartRepository;
import mk.finki.ukim.mk.lab3.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }


    @Override
    public void save(ShoppingCart c) {
        shoppingCartRepository.save(c);
    }
}
