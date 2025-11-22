package com.awais.jobms.Job.dto;

import com.awais.jobms.Job.external.Company;
import com.awais.jobms.Job.external.Review;
import lombok.Data;

import java.util.List;

@Data
public class JobDTO {
    private Long id;
    private String tittle;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Company company;
    private List<Review> review;
}
