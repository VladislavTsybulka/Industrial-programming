package org.example;
abstract class AbstractFactory {
    abstract Product createProduct();
}

class ConcreteFactoryA extends AbstractFactory {
    @Override
    Product createProduct() {
        return new ProductA();
    }
}

class ConcreteFactoryB extends AbstractFactory {
    @Override
    Product createProduct() {
        return new ProductB();
    }
}

abstract class Product {
    abstract String getDescription();
}

class ProductA extends Product {
    @Override
    String getDescription() {
        return "Product A";
    }
}

class ProductB extends Product {
    @Override
    String getDescription() {
        return "Product B";
    }
}
