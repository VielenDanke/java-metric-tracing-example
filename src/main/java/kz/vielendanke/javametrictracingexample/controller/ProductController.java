package kz.vielendanke.javametrictracingexample.controller;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;
import kz.vielendanke.javametrictracingexample.entity.Product;
import kz.vielendanke.javametrictracingexample.service.impl.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        Span span = GlobalTracer.get()
                .buildSpan("controller_find_all")
                .withTag(Tags.SPAN_KIND.getKey(), Tags.SPAN_KIND_SERVER)
                .withTag("entity", "product")
                .start();

        ResponseEntity<List<Product>> result = ResponseEntity.ok(productService.findAll(span));

        span.finish();

        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable(name = "id") Long id) {
        Span span = GlobalTracer.get()
                .buildSpan("controller_find_by_id")
                .withTag(Tags.SPAN_KIND.getKey(), Tags.SPAN_KIND_SERVER)
                .withTag("entity", "product")
                .start();

        ResponseEntity<Product> result = ResponseEntity.ok(productService.findById(id, span));

        span.finish();

        return result;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Product product) {
        Span span = GlobalTracer.get()
                .buildSpan("controller_save")
                .withTag(Tags.SPAN_KIND.getKey(), Tags.SPAN_KIND_SERVER)
                .withTag("entity", "product")
                .start();

        productService.save(product, span);

        span.finish();

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Product product) {
        Span span = GlobalTracer.get()
                .buildSpan("controller_save")
                .withTag(Tags.SPAN_KIND.getKey(), Tags.SPAN_KIND_SERVER)
                .withTag("entity", "product")
                .start();

        productService.update(product, span);

        span.finish();

        return ResponseEntity.ok().build();
    }
}
