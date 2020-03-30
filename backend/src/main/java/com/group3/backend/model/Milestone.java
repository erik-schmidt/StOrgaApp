package com.group3.backend.model;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Milestone {
    private Integer id;
    private String text;
    private LocalDate finishDate;
}
