package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.data.Book;
import ru.netology.data.Product;
import ru.netology.data.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Product book = new Book(1, "Call of Cthulhu", 800, "H.Lovecraft");
    Product book12 = new Book(12, "iPhone First", 600, "S.Jobs");
    Product smartphone = new Smartphone(2, "iPhone", 90000, "Apple");
    Product product = new Product(313, "Bread", 40);

    @Test
    void testShouldAdd() {
        manager.add(book);
        Product[] expected = {book};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void testShouldAddAll() {
        manager.add(book);
        manager.add(smartphone);
        manager.add(product);
        Product[] expected = {book, smartphone, product};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void testShouldSearchBy() {
        manager.add(book);
        manager.add(smartphone);
        manager.add(product);
        String name = "Call of Cthulhu";
        Product[] expected = {book};
        Product[] actual = manager.searchBy(name);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testShouldSearchWhenFewProducts() {
        manager.add(book12);
        manager.add(smartphone);
        manager.add(product);
        String name = "iPhone";
        Product[] expected = {book12, smartphone};
        Product[] actual = manager.searchBy(name);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testShouldSearchWhenProductsNotSuit() {
        manager.add(book);
        manager.add(smartphone);
        manager.add(product);
        String name = "Samsung";
        Product[] expected = {};
        Product[] actual = manager.searchBy(name);
        assertArrayEquals(expected, actual);
    }


}