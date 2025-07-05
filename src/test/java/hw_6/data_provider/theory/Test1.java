package hw_6.theory;

import hw_6.theory.listeners.Listener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Listener.class)
public class Test1 extends BaseClass {

    @Test
    public void firstTest() {
        System.out.println("---First test from Test1 class");
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void secondTest() {
        System.out.println(1/0);
    }

}
