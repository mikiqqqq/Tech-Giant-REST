package com.spring_boot.webshop_app.repository;

import com.spring_boot.webshop_app.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Integer>, ItemCustomRepo {
    List<Item> findAllByTitleContainsIgnoreCase(String target);
    @Query(value = "SELECT * FROM PRODUCT ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Item> findRandomItems(@Param("limit") int limit);

}
