package mk.finki.ukim.mk.lab3.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class ProductsPage extends AbstractPage {

    @FindBy(css = "tr[class=product]")
    private List<WebElement> productRows;


    @FindBy(className = "delete-product")
    private List<WebElement> deleteButtons;


    @FindBy(className = "edit-product")
    private List<WebElement> editButtons;

    @FindBy(css = ".add-product")
    private List<WebElement> addProductButton;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public static ProductsPage to(WebDriver driver) {
        get(driver, "/balloons");
        return PageFactory.initElements(driver, ProductsPage.class);
    }

    public void assertElemts(int productsNumber, int deleteButtons, int editButtons,  int addButtons) {
        Assert.assertEquals("rows do not match", productsNumber, this.getProductRows().size());
        Assert.assertEquals("delete do not match", deleteButtons, this.getDeleteButtons().size());
        Assert.assertEquals("edit do not match", editButtons, this.getEditButtons().size());
        Assert.assertEquals("add is visible", addButtons, this.getAddProductButton().size());
    }
}

