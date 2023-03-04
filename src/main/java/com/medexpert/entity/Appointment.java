package com.medexpert.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "client_id")
	private int clientId;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "time")
	private String time;
	
	@Column(name = "tests")
	private String tests;

	
	/**
	 * @param date
	 * @param time
	 * @param tests
	 */
	public Appointment(Date date, String time, String tests) {
		this.date = date;
		this.time = time;
		this.tests = tests;
	}


	@Override
	public String toString() {
		return "Appointment [date=" + date + ", time=" + time + ", tests=" + tests + "]";
	}
}
