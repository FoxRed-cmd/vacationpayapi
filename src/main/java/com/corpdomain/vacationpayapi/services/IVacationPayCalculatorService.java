package com.corpdomain.vacationpayapi.services;

import com.corpdomain.vacationpayapi.models.VacationRequestParameters;

public interface IVacationPayCalculatorService {
	double calculateVacationPay(VacationRequestParameters params);
}
