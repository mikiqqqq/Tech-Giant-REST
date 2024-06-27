package com.spring_boot.webshop_app.repository.impl;

import com.spring_boot.webshop_app.model.Item;
import com.spring_boot.webshop_app.repository.ItemCustomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ItemCustomRepoImpl implements ItemCustomRepo {
    private final EntityManager entityManager;

    @Autowired
    public ItemCustomRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Item> findByItemId(Integer id) {

        return entityManager
                .createQuery("select i from Item i where i.id = :id", Item.class)
                .setParameter("id", id)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Item> findRandomItems(int limit) {
        String sql = "SELECT TOP " + limit + " * FROM PRODUCT ORDER BY NEWID()";
        Query query = entityManager.createNativeQuery(sql, Item.class);
        return query.getResultList();
    }

    @Override
    public List<Item> findAllInPriceRange(Long uprLmt, Long lwrLmt) {

        return entityManager
                .createQuery("select i from Item i where i.price < :uprLmt and i.price > :lwrLmt", Item.class)
                .setParameter("uprLmt", uprLmt)
                .setParameter("lwrLmt", lwrLmt)
                .getResultList();
    }

    @Override
    public List<Item> findByBrandId(Integer id) {

        return entityManager
                .createQuery("select i from Item i where i.brand.id = :brandId", Item.class)
                .setParameter("brandId", id)
                .getResultList();
    }
}
