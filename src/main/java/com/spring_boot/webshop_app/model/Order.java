package com.spring_boot.webshop_app.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DATE")
    private Timestamp date;

    @Column(name = "TOTAL_PRICE_EXCL_VAT")
    private BigDecimal priceWithNoPdvIncluded;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal total;

    @Column(name = "DISCOUNT_CODE_ID")
    private Integer discountCodeId;

    @Column(name = "PAYMENT_METHOD_ID")
    private Integer paymentMethod;

    @Column(name = "CARD_NUMBER")
    private String creditCardNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "DELIVERY_ADDRESS")
    private String deliveryAddress;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "STATUS")
    private String status;
}
