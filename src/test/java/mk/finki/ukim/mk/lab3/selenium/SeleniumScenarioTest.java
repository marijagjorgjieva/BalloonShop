package mk.finki.ukim.mk.lab3.selenium;

import mk.finki.ukim.mk.lab3.model.Manufacturer;
import mk.finki.ukim.mk.lab3.model.User;
import mk.finki.ukim.mk.lab3.service.BalloonService;
import mk.finki.ukim.mk.lab3.service.ManufacturerService;
import mk.finki.ukim.mk.lab3.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {
    @Autowired
    UserService userService;

    @Autowired
    ManufacturerService manufacturerService;


    @Autowired
    BalloonService BalloonService;

    private HtmlUnitDriver driver;

    private static Manufacturer m1;
    private static Manufacturer m2;
    private static User regularUser;
    private static User adminUser;

    private static String user = "user";
    private static String admin = "admin";

    private static boolean dataInitialized = false;

    @BeforeEach
    private void setup() {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }


    private void initData() {
        if (!dataInitialized) {

            m1 = manufacturerService.save("m1", "m1");
            m2 = manufacturerService.save("m2", "m2");


            regularUser = userService.addUser(new User(user, user, user, user, LocalDate.now()));
            adminUser = userService.addUser(new User(admin, admin, admin, admin,LocalDate.now()));
            dataInitialized = true;
        }
    }
    @Test
    public void testScenario() throws Exception {
        ProductsPage productsPage = ProductsPage.to(this.driver);
        productsPage.assertElemts(0, 0, 0,  0);
        LoginPage loginPage = LoginPage.openLogin(this.driver);

        productsPage = LoginPage.doLogin(this.driver, loginPage, adminUser.getUsername(), admin);
        productsPage.assertElemts(0, 0, 0,  1);

        productsPage = Add.addProduct(this.driver, "test", "desc", m1.getName());
        productsPage.assertElemts(1, 1, 1,  1);



    }



}
