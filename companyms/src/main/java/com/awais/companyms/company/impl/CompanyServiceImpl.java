package com.awais.companyms.company.impl;


import com.awais.companyms.company.Clients.ReviewClient;
import com.awais.companyms.company.Company;
import com.awais.companyms.company.CompanyRepository;
import com.awais.companyms.company.CompanyService;
import com.awais.companyms.company.dto.ReviewMessage;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    private ReviewMessage reviewMessage;
    @Autowired
    private ReviewClient reviewClient;

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company companyToupdate = companyOptional.get();
            companyToupdate.setName(company.getName());
            companyToupdate.setDescription(company.getDescription());
            companyRepository.save(companyToupdate);
            return true;
        }
        return false;
    }

    @Override
    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Company getCompanybyid(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        try {
            System.out.println("➡️ Updating rating for company ID: " + reviewMessage.getCompanyId());
            Company company = companyRepository.findById(reviewMessage.getCompanyId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Company not found with ID: " + reviewMessage.getCompanyId()
                    ));

            double averageRating = reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());
            company.setRating(averageRating);
            companyRepository.save(company);

            System.out.println("✅ Company rating updated successfully.");

        } catch (ResponseStatusException e) {
            System.err.println("⚠️ " + e.getReason());
        } catch (Exception e) {
            System.err.println("❌ Unexpected error: " + e.getMessage());
        }
    }

}