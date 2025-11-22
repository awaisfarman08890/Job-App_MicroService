package com.awais.companyms.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@Data
@AllArgsConstructor

public class CompanyController {
    private CompanyService companyService;
    @GetMapping
    public ResponseEntity<List<Company>> getcompany(){
        return new ResponseEntity<>(companyService.getAllCompany(), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updatecompany(@PathVariable Long id, @RequestBody Company company){
        boolean updated = companyService.updateCompany(company,id);
        if(updated){
            return new ResponseEntity<>("updated companies", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<String> addcompany(@RequestBody Company company){
        companyService.addCompany(company);
        return new ResponseEntity<>("added company", HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletecompany(@PathVariable Long id){
        boolean deleted = companyService.deleteCompany(id);
        if (deleted){
            return new ResponseEntity<>("deleted company", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Company> getcompanyById(@PathVariable Long id){
        Company company =companyService.getCompanybyid(id);
        if(company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
