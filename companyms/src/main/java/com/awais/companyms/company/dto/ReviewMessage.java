package com.awais.companyms.company.dto;

import lombok.Data;

@Data
public class ReviewMessage {
    private Long id;
    private String tittle;
    private String description;
    private double rating;
    private Long companyId;
}
