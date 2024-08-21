package com.zuhriddin.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zuhriddin.model.Product;
import com.zuhriddin.service.util.UtilityJson;

import java.util.List;
import java.util.UUID;

public class ProductService implements BaseService<Product, UUID>{
    private final String PATH = "src/file/products.json";

    @Override
    public Product add(Product product) {
        List<Product> products = read();
        products.add(product);
        write(products);
        return product;
    }

    @Override
    public Product get(UUID id) {
        List<Product> products = read();
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public void delete(UUID id) {
        List<Product> products = read();
        products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .ifPresent(products::remove);
        write(products);
    }

    @Override
    public List<Product> list() {
        return read();
    }

    private List<Product> read() {
        return UtilityJson.read(PATH, new TypeReference<>() {});
    }

    private void write(List<Product> products) {
        UtilityJson.write(PATH, products);
    }
}
