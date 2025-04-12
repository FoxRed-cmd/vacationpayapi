package com.corpdomain.vacationpayapi.models;

import java.time.LocalDate;

import lombok.Data;

@Data
public class VacationParameters {
	private double averageSalary;
	private int vacationDays;
	private LocalDate startDate;
}
