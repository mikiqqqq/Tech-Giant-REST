package com.spring_boot.webshop_app.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "PRODUCT")
public class Item {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "BRAND_ID")
    private Integer brandId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BRAND_ID", insertable = false, updatable = false)
    private Brand brand;

    @Column(name = "TYPE_ID")
    private Integer typeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TYPE_ID", insertable = false, updatable = false)
    private ProductType productType;

    @Column(name = "PRODUCTION_YEAR")
    private Integer productionYear;
}