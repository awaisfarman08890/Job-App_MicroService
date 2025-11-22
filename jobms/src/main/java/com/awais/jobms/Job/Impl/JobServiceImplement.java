package com.awais.jobms.Job.Impl;

import com.awais.jobms.Job.Job;
import com.awais.jobms.Job.JobRepository;
import com.awais.jobms.Job.JobService;
import com.awais.jobms.Job.clients.CompanyClient;
import com.awais.jobms.Job.clients.ReviewClient;
import com.awais.jobms.Job.dto.JobDTO;
import com.awais.jobms.Job.external.Company;
import com.awais.jobms.Job.external.Review;
import com.awais.jobms.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class JobServiceImplement implements JobService {
//    private List<Job> jobs = new ArrayList<>();
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CompanyClient companyClient;
    @Autowired
    private ReviewClient reviewClient;

    int attempt = 0;

    @Override

//    @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
//    @Retry(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name = "companyBreaker")
    public List<JobDTO> findAll() {
//        System.out.println("Attempt " + ++attempt);
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobWithCompanyDTOs = new ArrayList<>();
        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public List<String> companyBreakerFallback(Exception e) {
        List<String> list = new ArrayList<>();
        list.add("Dummy");
        return list;
    }

    private JobDTO convertToDto(Job job) {
            Company company = companyClient.getCompany(job.getCompanyId());
            List<Review> reviews = reviewClient.getCompanyReview(job.getCompanyId());
            return JobMapper.mapJobToJobWithCompanyDTO(job, company,reviews);
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobByid(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        return convertToDto(job);

    }

    @Override
    public boolean deleteJobbyId(Long id) {
       try {
           jobRepository.deleteById(id);
           return true;
       } catch (Exception e){
           return false;
       }
    }

    @Override
    public boolean updatedJob(Long id, Job updatedjob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
            if(jobOptional.isPresent()) {
                Job job = jobOptional.get();
                job.setTittle(updatedjob.getTittle());
                job.setDescription(updatedjob.getDescription());
                job.setMinSalary(updatedjob.getMinSalary());
                job.setMaxSalary(updatedjob.getMaxSalary());
                job.setLocation(updatedjob.getLocation());
                jobRepository.save(job);
                return true;
            }
        return false;
    }
}
