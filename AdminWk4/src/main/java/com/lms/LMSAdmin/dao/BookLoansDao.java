package com.lms.LMSAdmin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.LMSAdmin.pojo.BookLoans;

@Repository
public interface BookLoansDao extends JpaRepository<BookLoans, Long>{

}
