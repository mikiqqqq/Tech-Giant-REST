package com.spring_boot.webshop_app.repository;

import com.spring_boot.webshop_app.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Integer>, ItemCustomRepo {
    List<Item> findAllByTitleContainsIgnoreCase(String target);
}
