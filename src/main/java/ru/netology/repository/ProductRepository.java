package ru.netology.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.data.Product;
import ru.netology.exceptions.AlreadyExistsException;
import ru.netology.exceptions.NotFoundException;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRepository {

    private Product[] goods = new Product[0];


    public void save(Product item) {
        if (findById(item.getId()) != null) {
            throw new AlreadyExistsException("Товар с ID: " + item.getId() + " уже добавлен в репозиторий!");
        }
        int length = goods.length + 1;
        Product[] tmp = new Product[length];
        System.arraycopy(goods, 0, tmp, 0, goods.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        goods = tmp;
    }

    public Product[] findAll() {
        return goods;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Товар с ID: " + id + " не существует!");
        }
        int length = goods.length - 1;
        Product[] tmp = new Product[length];
        int index = 0;
        for (Product item : goods) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        goods = tmp;
    }

    public Product findById(int id) {
        for (Product item : goods) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}

