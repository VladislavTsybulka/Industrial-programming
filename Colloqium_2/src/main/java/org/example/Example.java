package org.example;

public class Example {
    public static void main(String[] args) {
        AbstractFactory factoryA = new ConcreteFactoryA();
        Product productA = factoryA.createProduct();
        System.out.println(productA.getDescription());

        AbstractFactory factoryB = new ConcreteFactoryB();
        Product productB = factoryB.createProduct();
        System.out.println(productB.getDescription());
    }
}
