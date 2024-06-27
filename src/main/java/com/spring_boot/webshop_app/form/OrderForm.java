package com.spring_boot.webshop_app.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class OrderForm {

    private Integer id;

    @JsonFormat(pattern = "dd. MM. yyyy.")
    private Timestamp date;

    private BigDecimal priceWithNoPdvIncluded;
    private BigDecimal total;
    private Integer discountCodeId;
    private Integer paymentMethod;
    private String creditCardNumber;
    private String email;
    private String phoneNumber;
    private String deliveryAddress;
    private String note;
    private String status;
}
