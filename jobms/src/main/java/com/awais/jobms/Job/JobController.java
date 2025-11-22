package com.awais.jobms.Job;

import com.awais.jobms.Job.dto.JobDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@Data
@RestController
@RequestMapping("/jobs")
public class JobController {

 private JobService jobService;

    @GetMapping
    public ResponseEntity<List<JobDTO>> findAll(){
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job created", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> findById(@PathVariable Long id){
        JobDTO jobDTO = jobService.getJobByid(id);
        if(jobDTO != null)
             return new ResponseEntity<>(jobDTO, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deleteJobbyId(id);
        if(deleted)
               return new ResponseEntity<>("Job deleted", HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id,
                                            @RequestBody Job updatedjob){
        boolean updated = jobService.updatedJob(id, updatedjob);
        if(updated)
            return ResponseEntity.ok("job updated");
        return ResponseEntity.notFound().build();

    }
}
