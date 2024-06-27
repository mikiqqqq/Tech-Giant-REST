package com.spring_boot.webshop_app.service.impl;

import com.spring_boot.webshop_app.dto.ItemDto;
import com.spring_boot.webshop_app.mapper.ItemDtoMapper;
import com.spring_boot.webshop_app.model.Item;
import com.spring_boot.webshop_app.model.OrderItem;
import com.spring_boot.webshop_app.repository.ItemRepo;
import com.spring_boot.webshop_app.repository.OrderItemRepo;
import com.spring_boot.webshop_app.service.ItemService;
import com.spring_boot.webshop_app.sort.ItemDtoSorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepo itemRepo;

    private final OrderItemRepo orderItemRepo;

    private final ItemDtoMapper itemDtoMapper;

    @Autowired
    public ItemServiceImpl(ItemRepo itemRepo, ItemDtoMapper itemDtoMapper, OrderItemRepo orderItemRepo) {
        this.itemRepo = itemRepo;
        this.orderItemRepo = orderItemRepo;
        this.itemDtoMapper = itemDtoMapper;
    }

    @Override
    public void saveItem(Item item) {
        itemRepo.save(item);
    }

    @Override
    public void deleteItem(int itemId) {
        Optional<Item> item = itemRepo.findById(itemId);
        if (item.isPresent()) {
            itemRepo.deleteById(itemId);
        }
    }

    @Override
    public List<ItemDto> fetchAll(){
        return itemRepo.findAll()
                .stream()
                .map(itemDtoMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDto fetchById(int id) {
        Optional<Item> item = itemRepo.findById(id);
        return item.map(itemDtoMapper::map).orElse(null);
    }

    @Override
    public List<ItemDto> fetchAllByOrderId(Integer orderId) {
        List<OrderItem> orderItems = orderItemRepo.findAllByOrderId(orderId);
        Integer[] itemIds = orderItems.stream().map(orderItem -> orderItem.getItem().getId()).toList().toArray(new Integer[0]);
        return fetchByItemIds(itemIds);
    }

    @Override
    public List<ItemDto> fetchByItemIds(Integer[] ids) {
        return Arrays.stream(ids)
                .flatMap(id -> itemRepo.findByItemId(id).stream())
                .map(itemDtoMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> fetchRandomItems(int limit) {
        return itemRepo.findRandomItems(limit)
                .stream()
                .map(itemDtoMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> fetchByBrandIds(Integer[] ids){
        return Arrays.stream(ids)
                .flatMap(id -> itemRepo.findByBrandId(id).stream())
                .map(itemDtoMapper::map)
                .collect(Collectors.toList());
    }

    List<ItemDto> filteredItems;

    @Override
    public List<ItemDto> filter(Optional<Integer[]> brandIds, Optional<BigDecimal> uprLmt, Optional<BigDecimal> lwrLmt,
                                Optional<Integer> productTypeId, Optional<Integer> productionYear,
                                Optional<String> sortBy, Optional<String> sortOrder)
    {
        //Default
        filteredItems = fetchAll();

        //Filtering
        brandIds.ifPresent(integers -> filteredItems = Arrays.stream(integers)
                .flatMap(id ->
                        filteredItems.stream().filter(itemDto -> itemDto.getBrand().getId().equals(id)))
                .collect(Collectors.toList()));
        if(uprLmt.isPresent() && lwrLmt.isPresent()) {
            filteredItems = filteredItems.stream()
                    .filter(itemDto -> itemDto.getPrice().compareTo(uprLmt.get()) < 0  && itemDto.getPrice().compareTo(lwrLmt.get()) > 0)
                    .collect(Collectors.toList());
        }
        if(productTypeId.isPresent() && productTypeId.get().compareTo(0) > 0){
            filteredItems = filteredItems.stream()
                    .filter(itemDto -> itemDto.getProductType().getId().equals(productTypeId.get()))
                    .collect(Collectors.toList());
        }
        if(productionYear.isPresent() && productionYear.get().compareTo(0) > 0){
            filteredItems = filteredItems.stream()
                    .filter(itemDto -> itemDto.getProductionYear().equals(productionYear.get()))
                    .collect(Collectors.toList());
        }

        //Sorting
        if(sortBy.isPresent() && sortOrder.isPresent()){
            filteredItems = sort(filteredItems, sortBy.get(), sortOrder.get());
        }

        return filteredItems;
    }

    public List<ItemDto> sort(List<ItemDto> filteredItems, String sortBy, String sortOrder){
        String price = "PRICE";
        String name = "NAME";

        if (sortBy.equalsIgnoreCase(price)) {
            if (sortOrder.equalsIgnoreCase("ASC")) {
                filteredItems.sort(new ItemDtoSorter(price, "ASC"));
            }
            if (sortOrder.equalsIgnoreCase("DESC")) {
                filteredItems.sort(new ItemDtoSorter(price, "DESC"));
            }
        }
        if (sortBy.equalsIgnoreCase(name)) {
            if (sortOrder.equalsIgnoreCase("ASC")) {
                filteredItems.sort(new ItemDtoSorter(name, "ASC"));
            }
            if (sortOrder.equalsIgnoreCase("DESC")) {
                filteredItems.sort(new ItemDtoSorter(name, "DESC"));
            }
        }

        return filteredItems;
    }

    @Override
    public List<ItemDto> fetchAllInPriceRange(Long uprLmt, Long lwrLimit){
        return itemRepo.findAllInPriceRange(uprLmt, lwrLimit)
                .stream()
                .map(itemDtoMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> fetchAllByTitleContainsIgnoreCase(String target){
        return itemRepo.findAllByTitleContainsIgnoreCase(target)
                .stream()
                .map(itemDtoMapper::map)
                .collect(Collectors.toList());
    }
}
