package com.saneshka.pos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductReqDTO {
    private String productName;

    private Double price;

    private String description;

    private Integer qty;

    private Long categoryId;
}
