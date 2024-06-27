package com.spring_boot.webshop_app.sort;

import com.spring_boot.webshop_app.dto.ItemDto;

import java.util.Comparator;

public class ItemDtoSorter implements Comparator<ItemDto> {
    private final String sortBy;
    private final String sortOrder;

    public ItemDtoSorter(String sortBy, String sortOrder){
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;
    }

    public int compare(ItemDto a, ItemDto b){
        if(sortBy.equalsIgnoreCase("PRICE")){
            if(sortOrder.equalsIgnoreCase("ASC"))
                return a.getPrice().compareTo(b.getPrice());
            if(sortOrder.equalsIgnoreCase("DESC"))
                return b.getPrice().compareTo(a.getPrice());
        }
        if (sortBy.equalsIgnoreCase("NAME")){
            if(sortOrder.equalsIgnoreCase("ASC"))
                return a.getTitle().compareTo(b.getTitle());
            if(sortOrder.equalsIgnoreCase("DESC"))
                return b.getTitle().compareTo(a.getTitle());
        }

        return 0;
    }
}
