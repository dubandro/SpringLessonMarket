package ru.geebrains.market.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geebrains.market.model.Product;
import ru.geebrains.market.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> productAll() {
        return repository.productAll();
    }

    public Product productById(Long id) {
        return repository.productById(id);
    }

    public boolean idIsPresent(Long id) {
        return repository.idIsPresent(id);
    }

    public Long nextID() {
        return repository.nextID();
    }

    public void addProduct(Product product) {
        repository.addProduct(product);
    }

    public void changeCount(Long productID, Integer delta) {
        int count = repository.productById(productID).getCount() + delta;
        if (count >= 0) repository.productById(productID).setCount(count);
    }

    public void deleteProduct(Long productID) {
        repository.deleteProduct(productID);
    }
}
