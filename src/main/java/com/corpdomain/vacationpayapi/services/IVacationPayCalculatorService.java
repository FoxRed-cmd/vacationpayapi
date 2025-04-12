package com.corpdomain.vacationpayapi.services;

import com.corpdomain.vacationpayapi.models.VacationParameters;

public interface IVacationPayCalculatorService {
	double calculateVacationPay(VacationParameters params);
}
