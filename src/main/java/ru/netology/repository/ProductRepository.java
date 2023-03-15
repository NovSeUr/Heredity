package ru.netology.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.data.Product;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRepository {

    private  Product [] goods = new Product[0];

    public void save(Product item) {
        int length = goods.length + 1;
        Product[] tmp = new Product[length];
        System.arraycopy(goods, 0, tmp, 0, goods.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        goods = tmp;
    }
    public Product [] findAll(){
        return goods;
    }

    public void removeById (int id) {
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
}
