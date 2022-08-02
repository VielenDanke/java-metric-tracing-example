package kz.vielendanke.javametrictracingexample.service.impl;

import io.opentracing.Span;
import io.opentracing.util.GlobalTracer;
import kz.vielendanke.javametrictracingexample.entity.Product;
import kz.vielendanke.javametrictracingexample.repository.ProductRepository;
import kz.vielendanke.javametrictracingexample.service.CRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements CRUD<Product, Long> {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll(Span span) {
        Span childSpan = GlobalTracer.get().buildSpan("service_find_all").asChildOf(span).start();

        List<Product> allProducts = productRepository.findAll();

        childSpan.finish();

        return allProducts;
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id, Span span) {
        Span childSpan = GlobalTracer.get().buildSpan("service_find_by_id").asChildOf(span).start();

        Product product = productRepository.findById(id).orElseThrow(RuntimeException::new);

        childSpan.finish();

        return product;
    }

    @Override
    @Transactional
    public void save(Product product, Span span) {
        Span childSpan = GlobalTracer.get().buildSpan("service_save").asChildOf(span).start();

        productRepository.save(product);

        childSpan.finish();
    }

    @Override
    @Transactional
    public void update(Product product, Span span) {
        Span childSpan = GlobalTracer.get().buildSpan("service_update").asChildOf(span).start();

        productRepository.update(product.getName(), product.getDescription(), product.getId());

        childSpan.finish();
    }
}
