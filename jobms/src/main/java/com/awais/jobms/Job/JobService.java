package com.awais.jobms.Job;


import com.awais.jobms.Job.dto.JobDTO;

import java.util.List;
public interface JobService {
    List<JobDTO> findAll();
    void createJob(Job job);
    JobDTO getJobByid(Long id);

    boolean deleteJobbyId(Long id);

    boolean updatedJob(Long id, Job updatedjob);
}
