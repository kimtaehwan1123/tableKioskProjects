package org.example.tablekioskprojects.vo;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter

public class DetailVO {
    private Integer ono;
    private Integer mno;
    private int quantity;
    private BigDecimal total_price;
}