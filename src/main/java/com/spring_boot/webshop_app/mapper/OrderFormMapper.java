package com.spring_boot.webshop_app.mapper;

import com.spring_boot.webshop_app.form.OrderForm;
import com.spring_boot.webshop_app.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderFormMapper {

    public Order map(OrderForm orderForm){

        if(orderForm == null){
            return null;
        }

        return Order.builder()
                .id(orderForm.getId())
                .date(orderForm.getDate())
                .priceWithNoPdvIncluded(orderForm.getPriceWithNoPdvIncluded())
                .total(orderForm.getTotal())
                .discountCodeId(orderForm.getDiscountCodeId())
                .paymentMethod(orderForm.getPaymentMethod())
                .creditCardNumber(orderForm.getCreditCardNumber())
                .email(orderForm.getEmail())
                .phoneNumber(orderForm.getPhoneNumber())
                .deliveryAddress(orderForm.getDeliveryAddress())
                .note(orderForm.getNote())
                .status(orderForm.getStatus())
                .build();
    }
}
