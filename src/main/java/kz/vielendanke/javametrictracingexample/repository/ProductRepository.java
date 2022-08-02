package kz.vielendanke.javametrictracingexample.repository;

import kz.vielendanke.javametrictracingexample.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = "update products p set p.name=?1, p.description=?2 where p.id=?3")
    @Modifying
    void update(String name, String description, Long id);
}
