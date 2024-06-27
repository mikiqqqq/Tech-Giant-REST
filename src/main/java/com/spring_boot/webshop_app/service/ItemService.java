package com.spring_boot.webshop_app.service;

import com.spring_boot.webshop_app.dto.ItemDto;
import com.spring_boot.webshop_app.model.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<ItemDto> fetchAllByTitleContainsIgnoreCase(String target);
    List<ItemDto> fetchByItemIds(Integer[] ids);
    ItemDto fetchById(int id);
    List<ItemDto> fetchByBrandIds(Integer[] ids);
    List<ItemDto> fetchAllByOrderId(Integer orderId);
    List<ItemDto> fetchAllInPriceRange(Long uprLmt, Long lwrLimit);
    List<ItemDto> fetchAll();
    void saveItem(Item item);
    void deleteItem(int itemId);
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    List<ItemDto> filter(Optional<Integer[]> ids, Optional<BigDecimal> uprLmt, Optional<BigDecimal> lwrLmt,
                         Optional<Integer> productTypeId, Optional<Integer> productionYear,
                         Optional<String> sortBy, Optional<String> sortOrder);
    List<ItemDto> fetchRandomItems(int limit);
}
