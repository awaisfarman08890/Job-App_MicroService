package com.awais.Reviewms.Reviews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewReponsitory extends JpaRepository<Review, Long> {
    List<Review> findByCompanyId(Long companyId);
}
