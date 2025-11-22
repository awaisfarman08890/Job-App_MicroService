package com.awais.jobms.mapper;

import com.awais.jobms.Job.Job;
import com.awais.jobms.Job.dto.JobDTO;
import com.awais.jobms.Job.external.Company;
import com.awais.jobms.Job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO mapJobToJobWithCompanyDTO(Job job, Company company, List<Review> review) {
        JobDTO jobWithCompanyDTO = new JobDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setTittle(job.getTittle());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setCompany(company);
        jobWithCompanyDTO.setReview(review);
        return jobWithCompanyDTO;
    }
}
