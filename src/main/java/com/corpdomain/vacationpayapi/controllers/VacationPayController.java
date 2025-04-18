package com.corpdomain.vacationpayapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.corpdomain.vacationpayapi.dto.VacationPayResponse;
import com.corpdomain.vacationpayapi.models.VacationParameters;
import com.corpdomain.vacationpayapi.services.IVacationPayCalculatorService;

import java.time.LocalDate;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@Validated
public class VacationPayController {
	private final IVacationPayCalculatorService vacationPayService;

	public VacationPayController(IVacationPayCalculatorService vacationPayService) {
		this.vacationPayService = vacationPayService;
	}

	@GetMapping("/calculate")
	public ResponseEntity<VacationPayResponse> calculateVacationPay(
			@RequestParam @NotNull(message = "averageSalary is required")
			@Positive(message = "averageSalary must be positive") double averageSalary,

			@RequestParam @NotNull(message = "vacationDays is required")
			@Min(value = 1, message = "vacationDays must be at least 1") int vacationDays,

			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {

		VacationParameters request = new VacationParameters();
		request.setAverageSalary(averageSalary);
		request.setStartDate(startDate);
		request.setVacationDays(vacationDays);

		double result = vacationPayService.calculateVacationPay(request);
		return ResponseEntity.ok(new VacationPayResponse(result));
	}
}
