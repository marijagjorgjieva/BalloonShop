package mk.finki.ukim.mk.lab3;

import mk.finki.ukim.mk.lab3.model.exceptions.WrongUsernameOrPasswordException;
import org.junit.Assert;
import mk.finki.ukim.mk.lab3.model.User;
import mk.finki.ukim.mk.lab3.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab3.service.AuthService;
import mk.finki.ukim.mk.lab3.service.impl.AuthServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest {
    @Mock
    private UserRepository userRepository;

    private AuthServiceImpl authService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        User user = new User("username","test","user","password", LocalDate.now());

        Mockito.when(this.userRepository.findByUsernameAndPassword(Mockito.anyString(), Mockito.anyString()))
                .thenAnswer(invocation -> {
                    String username = (String) invocation.getArgument(0);
                    String password = (String) invocation.getArgument(1);
                    if (username.equals("username") && password.equals("password")) {
                        return user;
                    } else {
                        return null;
                    }
                });
        authService = Mockito.spy(new AuthServiceImpl(this.userRepository));
    }


    @Test
    public void testSuccessLogin() {
        User user = this.authService.login("username","password");

        Mockito.verify(this.authService).login("username","password");
        Assert.assertNotNull("User is null",user);
        Assert.assertEquals("username does not match","username",user.getUsername());
        Assert.assertEquals("name does not match","test",user.getName());
        Assert.assertEquals("surname does not match","user",user.getSurname());
        Assert.assertEquals("password does not match","password",user.getPassword());
        Assert.assertEquals("birth date does not match",LocalDate.now(),user.getDateOfBirth());
    }

    @Test
    public void testPasswordEmpty()  {
        Assert.assertThrows("IllegalArgumentsException expected",
                WrongUsernameOrPasswordException.class,
                () ->
                        this.authService.login("username",""));
        Mockito.verify(this.authService).login("username","");
    }
    @Test
    public void testUsernameEmpty() {
        Assert.assertThrows("IllegalArgumentsException expected",
                WrongUsernameOrPasswordException.class,
                () ->
                        this.authService.login("","password"));
        Mockito.verify(this.authService).login("","password");
    }

    @Test
    public void testPasswordNull()  {
        Assert.assertThrows("IllegalArgumentsException expected",
                WrongUsernameOrPasswordException.class,
                () ->
                        this.authService.login("username",""));
        Mockito.verify(this.authService).login("username","");
    }
    @Test
    public void testUsernameNull() {
        Assert.assertThrows("IllegalArgumentsException expected",
                WrongUsernameOrPasswordException.class,
                () ->
                        this.authService.login(null,"password"));
        Mockito.verify(this.authService).login(null,"password");
    }
}

