package com.corpdomain.vacationpayapi.models;

import lombok.Data;

@Data
public class VacationPayResponse {
	private double vacationPay;

	public VacationPayResponse(double vacationPay) {
		this.vacationPay = vacationPay;
	}
}
