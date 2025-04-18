package com.corpdomain.vacationpayapi.models;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class VacationRequestParameters {
	@NotNull
	@Positive(message = "averageSalary must be positive")
	private double averageSalary;

	@NotNull
	@Min(value = 1, message = "vacationDays must be at least 1")
	private int vacationDays;

	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private LocalDate startDate;
}
