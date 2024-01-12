package com.sist.vo;

import java.sql.Date;

public class ReservationVO {
	private int rno;
	private String cardno;
	private Date checkin_date;
	private Date checkout_date;
	private int guest_cnt;
	private int saleprice;
	private int uno;
	private int pno;
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public Date getCheckin_date() {
		return checkin_date;
	}
	public void setCheckin_date(Date checkin_date) {
		this.checkin_date = checkin_date;
	}
	public Date getCheckout_date() {
		return checkout_date;
	}
	public void setCheckout_date(Date checkout_date) {
		this.checkout_date = checkout_date;
	}
	public int getGuest_cnt() {
		return guest_cnt;
	}
	public void setGuest_cnt(int guest_cnt) {
		this.guest_cnt = guest_cnt;
	}
	public int getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(int saleprice) {
		this.saleprice = saleprice;
	}
	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public ReservationVO(int rno, String cardno, Date checkin_date, Date checkout_date, int guest_cnt, int saleprice,
			int uno, int pno) {
		super();
		this.rno = rno;
		this.cardno = cardno;
		this.checkin_date = checkin_date;
		this.checkout_date = checkout_date;
		this.guest_cnt = guest_cnt;
		this.saleprice = saleprice;
		this.uno = uno;
		this.pno = pno;
	}
	public ReservationVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
