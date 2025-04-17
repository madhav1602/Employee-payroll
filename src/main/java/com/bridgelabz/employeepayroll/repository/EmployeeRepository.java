package com.bridgelabz.employeepayroll.repository;

import com.bridgelabz.employeepayroll.model.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeInfo, Long> {
}
