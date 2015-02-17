package com.thoughtworks.gauge.example;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.example.pages.CustomerPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CustomerSpec {
    private final WebDriver driver;

    public CustomerSpec() {
        this.driver = DriverFactory.getDriver();
    }

    @Step("On the customer page")
    public void navigateToCustomersPage() {
        driver.get(CustomerPage.CustomerUrl);
    }

    @Step("Search for customer <name>")
    public void searchUser(String username) {
        CustomerPage customerPage = PageFactory.initElements(driver, CustomerPage.class);
        customerPage.searchUser(username);
    }

    @Step("The customer <name> is listed")
    public void verifyUserIsListed(String username) {
        CustomerPage customerPage = PageFactory.initElements(driver, CustomerPage.class);
        customerPage.verifyUserListed(username);
    }

    @Step("Search for customers <table>")
    public void verifyCustomers(Table table) {
        List<List<String>> rows = table.getRows();
        System.out.println(rows.size());
        for (List<String> row : rows) {
            searchUser(row.get(0));
            System.out.println(row.get(0));
            verifyUserIsListed(row.get(0));
        }
    }
}