package org.example;

import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractFactoryTest {

    @Test
    public void testProductADescription() {
        AbstractFactory factoryA = new ConcreteFactoryA();
        Product productA = factoryA.createProduct();
        assertEquals("Product A", productA.getDescription());
    }

    @Test
    public void testProductBDescription() {
        AbstractFactory factoryB = new ConcreteFactoryB();
        Product productB = factoryB.createProduct();
        assertEquals("Product B", productB.getDescription());
    }
}
