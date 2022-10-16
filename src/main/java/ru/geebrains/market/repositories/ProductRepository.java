package ru.geebrains.market.repositories;

import org.springframework.stereotype.Repository;
import ru.geebrains.market.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> productList;

    @PostConstruct
    public void init(){
        productList = new ArrayList<>(Arrays.asList(
                new Product(1L, "Bread", 50L, 0),
                new Product(2L, "Milk", 80L, 0),
                new Product(3L, "Orange", 100L, 0)
        ));
    }

    public Product productById(Long id) {
        return productList.stream().filter(p -> p.getId().equals(id)).findFirst()
                .orElseThrow(() -> new RuntimeException("Товар не неайден"));
    }

    public boolean idIsPresent(Long id) {
        return productList.stream().anyMatch(p -> p.getId().equals(id));
    }

    public Long nextID() {
        Long max = 0L;
        for (Product p : productList) {
            if (p.getId() > max) max = p.getId();
        }
        return max + 1;
    }

    public List<Product> productAll() {
        return Collections.unmodifiableList(productList);
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void deleteProduct(Long productID) {
        productList.removeIf(product -> product.getId().equals(productID));
    }
}
