package com.awais.companyms.company;

import com.awais.companyms.company.dto.ReviewMessage;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompany();
    boolean updateCompany(Company company, Long id);
    void addCompany(Company company);
    boolean deleteCompany(Long id);
    Company getCompanybyid(Long id);
    void updateCompanyRating(ReviewMessage reviewMessage);
}
