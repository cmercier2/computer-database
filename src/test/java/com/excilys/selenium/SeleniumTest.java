package com.excilys.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {
	private String baseUrl; 
	private WebDriver driver;
	
    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/computer-database";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    
    
    @Test
    public void testSelenium() throws Exception {
      driver.get(baseUrl + "/dashboard");
    }
    
}
