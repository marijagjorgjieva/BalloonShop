package mk.finki.ukim.mk.lab3.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class Add extends AbstractPage {

    private WebElement name;
    private WebElement desc;
    private WebElement man;
    private WebElement submit;


    public Add(WebDriver driver) {
        super(driver);
    }

    public static ProductsPage addProduct(WebDriver driver, String name, String desc, String manufacturer) {
        get(driver, "/balloons/add-form");
        Add add = PageFactory.initElements(driver, Add.class);
        add.name.sendKeys(name);
        add.desc.sendKeys(desc);
        add.man.click();
        add.man.findElement(By.xpath("//option[. = '" + manufacturer + "']")).click();
        add.submit.click();
        return PageFactory.initElements(driver, ProductsPage.class);
    }


}


