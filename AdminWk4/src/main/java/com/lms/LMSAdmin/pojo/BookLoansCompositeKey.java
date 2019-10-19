package com.lms.LMSAdmin.pojo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class BookLoansCompositeKey implements Serializable {
	
	private static final long serialVersionUID = -3048883034853116437L;
	
	private Integer cardNo;
	private Integer branchId;
	private Integer bookId;
	
	public BookLoansCompositeKey() {}
	
	public BookLoansCompositeKey(Integer cardNo, Integer branchId, Integer bookId) {
		super();
		this.cardNo = cardNo;
		this.branchId = branchId;
		this.bookId = bookId;
	}

	public Integer getCardNo() {
		return cardNo;
	}

	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
		result = prime * result + ((cardNo == null) ? 0 : cardNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookLoansCompositeKey other = (BookLoansCompositeKey) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (branchId == null) {
			if (other.branchId != null)
				return false;
		} else if (!branchId.equals(other.branchId))
			return false;
		if (cardNo == null) {
			if (other.cardNo != null)
				return false;
		} else if (!cardNo.equals(other.cardNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookLoansCompositeKey [cardNo=" + cardNo + ", branchId=" + branchId + ", bookId=" + bookId + "]";
	}
}
