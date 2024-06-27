package com.spring_boot.webshop_app.controller;

import com.spring_boot.webshop_app.dto.ItemDto;
import com.spring_boot.webshop_app.model.Item;
import com.spring_boot.webshop_app.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping (value = "/admin/add")
    ResponseEntity<Item> save(@Valid @RequestBody Item item) {
        itemService.saveItem(item);

        return new ResponseEntity<>(
                HttpStatus.CREATED
        );
    }

    @DeleteMapping (value = "/admin/remove/{itemId}")
    ResponseEntity<Item> delete(@PathVariable int itemId) {
        itemService.deleteItem(itemId);

        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/by-id/{id}")
    ResponseEntity<ItemDto> fetchById(@PathVariable int id) {

        return ResponseEntity
                .ok()
                .body(itemService.fetchById(id));
    }

    @GetMapping(value = "/all")
    ResponseEntity<List<ItemDto>> fetchAll() {

        return ResponseEntity
                .ok()
                .body(itemService.fetchAll());
    }

    @GetMapping(value = "/by-orderId/{id}")
    ResponseEntity<List<ItemDto>> fetchByItemIds(@PathVariable Integer id) {

        return ResponseEntity
                .ok()
                .body(itemService.fetchAllByOrderId(id));
    }

    @GetMapping(value = "/by-itemIds/{ids}")
    ResponseEntity<List<ItemDto>> fetchByItemIds(@PathVariable Integer[] ids) {

        return ResponseEntity
                .ok()
                .body(itemService.fetchByItemIds(ids));
    }

    @GetMapping(value = "/by-brandIds/{ids}")
    ResponseEntity<List<ItemDto>> fetchByBrandIds(@PathVariable Integer[] ids) {

        return ResponseEntity
                .ok()
                .body(itemService.fetchByBrandIds(ids));
    }

    @GetMapping("/random-items/{limit}")
    ResponseEntity<List<ItemDto>> getRandomItems(@PathVariable int limit) {

        return ResponseEntity
                .ok()
                .body(itemService.fetchRandomItems(limit));
    }

    @GetMapping(value = "/filter-items-by")
    @ResponseBody
    ResponseEntity<List<ItemDto>> filterItemsBy(
            @RequestParam(value = "brandIds") Optional<Integer[]> brandIds,
            @RequestParam(value = "uprLmt") Optional<BigDecimal> uprLmt,
            @RequestParam(value = "lwrLmt") Optional<BigDecimal> lwrLmt,
            @RequestParam(value = "productTypeId") Optional<Integer> productTypeId,
            @RequestParam(value = "productionYear") Optional<Integer> productionYear,
            @RequestParam(value = "sortBy") Optional<String> sortBy,
            @RequestParam(value = "sortOrder") Optional<String> sortOrder) {

        return ResponseEntity.ok().body(itemService.
                filter(brandIds, uprLmt, lwrLmt, productTypeId, productionYear, sortBy, sortOrder));
    }

    @GetMapping(value = "/in-price-range/{uprLmt}-{lwrLmt}")
    ResponseEntity<List<ItemDto>> fetchAllInPriceRange(@PathVariable Long uprLmt, @PathVariable Long lwrLmt){

        return ResponseEntity
                .ok()
                .body(itemService.fetchAllInPriceRange(uprLmt, lwrLmt));
    }

    @GetMapping(value = "contain/{target}")
    ResponseEntity<List<ItemDto>> fetchAllByNameContainsIgnoreCase(@PathVariable String target) {

        return ResponseEntity
                .ok()
                .body(itemService.fetchAllByTitleContainsIgnoreCase(target));
    }
}
