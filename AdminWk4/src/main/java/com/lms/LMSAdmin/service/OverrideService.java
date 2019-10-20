package com.lms.LMSAdmin.service;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.dao.BookLoansDao;
import com.lms.LMSAdmin.pojo.BookLoans;
import com.lms.LMSAdmin.pojo.BookLoansCompositeKey;

@Component
public class OverrideService {
	
	@Autowired
	BookLoansDao loansDao;

	//Override due date
	public void overDueDate(BookLoans loans) {
		loansDao.save(loans);
	}
	
	//Get all records
	public List<BookLoans> getBookLoans() {
		return loansDao.findAll();
	}
	
	//Get one loans record
	public Optional<BookLoans> getLoanById(BookLoansCompositeKey compKey){
		return loansDao.findById(compKey);
	}
	
	//Add one day to dueDate
	public Date dueDate(Date dueDate) {
		
		LocalDateTime ldt = dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();		
		ldt = ldt.plusDays(1);
		
		dueDate = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		
		return dueDate;
	}
	
	//Validate Id's
	public boolean ifExists(int cardNo, int branchId, int bookId) {
		List<BookLoans> list = loansDao.findAll();
		boolean exists = false;
		
		//Check if the cardNo exists
		exists = list.stream()
				.anyMatch(id -> id.getBlCompKey().getBorrower().getCardNo().equals(cardNo) &&
						id.getBlCompKey().getBranch().getBranchId().equals(branchId) &&
						id.getBlCompKey().getBook().getBookId().equals(bookId));
	
		return exists;
	}
}
