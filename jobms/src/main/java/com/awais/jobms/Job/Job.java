package com.awais.jobms.Job;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "job_table")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tittle;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Long companyId;
}
