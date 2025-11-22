package com.awais.jobms.Job.external;

import lombok.Data;

@Data
public class Review {
    private Long id;
    private String tittle;
    private String description;
    private double rating;
}
