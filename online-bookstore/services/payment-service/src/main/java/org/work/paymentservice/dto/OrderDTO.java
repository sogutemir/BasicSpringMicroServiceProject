package org.work.paymentservice.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Long id;
    private String productName;
    private Double price;
    private Integer quantity;
}
