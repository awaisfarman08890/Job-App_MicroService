package com.awais.Reviewms.Reviews.dto;

import lombok.Data;

@Data
public class ReviewMessage {
    private Long id;
    private String tittle;
    private String description;
    private double rating;
    private Long companyId;
}
