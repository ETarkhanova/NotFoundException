package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Book first = new Book(1, "One", 100, "Author1");
    private Book second = new Book(2, "Two", 200, "Author2");
    private Book third = new Book(3, "Three", 300, "Author3");

    @BeforeEach
    public void save() {
        repository.saveProduct(first);
        repository.saveProduct(second);
        repository.saveProduct(third);
    }

    @Test
    void shouldRemoveByExistingId() {
        repository.removeById(1);

        Product[] expected = new Product[]{second, third};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveByNotExistingId() {

        assertThrows(NotFoundException.class, () -> {
            repository.removeById(4);
        });
    }
}