package com.leysoft.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DateBetweenRequest {
	
	@JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd")
	private Date lte;
	
	@JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd")
	private Date gte;

	public Date getLte() {
		return lte;
	}

	public void setLte(Date lte) {
		this.lte = lte;
	}

	public Date getGte() {
		return gte;
	}

	public void setGte(Date gte) {
		this.gte = gte;
	}
}
