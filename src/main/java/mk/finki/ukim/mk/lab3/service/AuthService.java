package mk.finki.ukim.mk.lab3.service;

import mk.finki.ukim.mk.lab3.model.User;

public interface AuthService {
    User login(String username, String password);
}
