package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.data.Book;
import ru.netology.data.Product;
import ru.netology.data.Smartphone;
import ru.netology.exceptions.AlreadyExistsException;
import ru.netology.exceptions.NotFoundException;

import java.rmi.AlreadyBoundException;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();
    Product book = new Book(1, "Call of Cthulhu", 800, "H.Lovecraft");
    Product book1 = new Book(11, "Книга 1", 800, "Автор 1");
    Product book2 = new Book(12, "Книга 2", 800, "Автор 2");
    Product book3 = new Book(13, "Книга 3", 800, "Автор 3");
    Product book4 = new Book(14, "Книга 4", 800, "Автор 4");
    Product book5 = new Book(15, "Книга 5", 800, "Автор 5");
    Product smartphone = new Smartphone(2, "iPhone", 90000, "Apple");
    Product product = new Product(3, "Bread", 40);

    @Test

    public void testShouldSave() {
        repository.save(book);
        Product[] expected = {book};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test

    public void testShouldThrowsAlreadyExistsException() {
        repository.save(book);
        Assertions.assertThrows(AlreadyExistsException.class,
                () -> repository.save(book));
    }


    @Test
    public void testShouldFindAll() {
        repository.save(book);
        repository.save(smartphone);
        repository.save(product);
        Product[] expected = {book, smartphone, product};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);

    }

    @Test
    public void testShouldRemoveByIdSuccessful() {
        repository.save(book);
        repository.save(smartphone);
        repository.save(product);
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(book4);
        repository.save(book5);
        repository.removeById(2);
        Product[] expected = {book, product, book1, book2, book3, book4, book5};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testShouldThrowsNotFoundExceptionIfRemoveID() {
        repository.save(book);
        repository.save(smartphone);
        repository.save(product);
        Assertions.assertThrows(NotFoundException.class,
                () -> repository.removeById(23));
    }

    @Test
    public void testShouldRemoveAllById() {
        repository.save(book);
        repository.save(smartphone);
        repository.save(product);
        repository.removeById(1);
        repository.removeById(2);
        repository.removeById(3);
        Product[] expected = {};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }


}