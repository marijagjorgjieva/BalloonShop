package mk.finki.ukim.mk.lab3.service.impl;

import mk.finki.ukim.mk.lab3.model.User;
import mk.finki.ukim.mk.lab3.model.exceptions.WrongUsernameOrPasswordException;
import mk.finki.ukim.mk.lab3.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab3.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   @Override
   public User login(String username, String password) throws WrongUsernameOrPasswordException{
       if(username==null || username.isEmpty()  || password==null || password.isEmpty())
           throw new WrongUsernameOrPasswordException();
       User user = userRepository.findByUsernameAndPassword(username, password);
       if (user == null) throw new WrongUsernameOrPasswordException();
       return user;
   }

}
