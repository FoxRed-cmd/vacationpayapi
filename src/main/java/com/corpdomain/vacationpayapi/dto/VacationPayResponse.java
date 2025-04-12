package com.corpdomain.vacationpayapi.dto;

import lombok.Data;

@Data
public class VacationPayResponse {
	private double vacationPay;

	public VacationPayResponse(double vacationPay) {
		this.vacationPay = vacationPay;
	}
}
