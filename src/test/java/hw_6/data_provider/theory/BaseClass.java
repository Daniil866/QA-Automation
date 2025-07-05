package hw_6.theory;

import org.testng.annotations.*;

public class BaseClass {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("---From before suite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("---From after suite");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("---From before test");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("---From after test");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("---From before class");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("---From after class");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("---From before method");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("---From after method");
    }

}
