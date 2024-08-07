package org.example.tablekioskprojects.vo;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderVO {
    private Integer ono;
    private int table_number;
    private Integer o_sequence;
    private String o_status;
    private LocalDate o_date;
    private LocalDateTime o_time;

}