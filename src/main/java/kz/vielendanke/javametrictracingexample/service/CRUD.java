package kz.vielendanke.javametrictracingexample.service;

import io.opentracing.Span;
import kz.vielendanke.javametrictracingexample.entity.Product;

import java.util.List;

public interface CRUD<ENTITY, ID> {

    List<ENTITY> findAll(Span span);

    ENTITY findById(Long id, Span span);

    void save(Product product, Span span);

    void update(Product product, Span span);
}
