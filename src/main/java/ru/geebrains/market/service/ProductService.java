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

    public void addProduct(Product product) {
        repository.addProduct(product);
    }
}
