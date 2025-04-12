package com.corpdomain.vacationpayapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.corpdomain.vacationpayapi.dto.VacationPayResponse;
import com.corpdomain.vacationpayapi.models.VacationParameters;
import com.corpdomain.vacationpayapi.services.IVacationPayCalculatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

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
@Tag(name = "Vacation calculator", description = "API for calculating vacation pay")
public class VacationPayController {
	private final IVacationPayCalculatorService vacationPayService;

	public VacationPayController(IVacationPayCalculatorService vacationPayService) {
		this.vacationPayService = vacationPayService;
	}

	@Operation(summary = "Calculate vacation pay",
			description = "Returns the amount of vacation pay based on average salary and vacation days")
	@GetMapping("/calculate")
	public ResponseEntity<VacationPayResponse> calculateVacationPay(
			@Parameter(description = "Average salary for 12 months", example = "24544.98")
			@RequestParam @NotNull(message = "averageSalary is required")
			@Positive(message = "averageSalary must be positive")
			double averageSalary,
			@Parameter(description = "Number of vacation days", example = "14")
			@RequestParam @NotNull(message = "vacationDays is required")
			@Min(value = 1, message = "vacationDays must be at least 1")
			int vacationDays,
			@Parameter(description = "The date from which the vacation begins in the format: YYYY-MM-DD",
					example = "2025-04-01") @RequestParam(required = false)
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
		VacationParameters request = new VacationParameters();
		request.setAverageSalary(averageSalary);
		request.setStartDate(startDate);
		request.setVacationDays(vacationDays);

		double result = vacationPayService.calculateVacationPay(request);
		return ResponseEntity.ok(new VacationPayResponse(result));
	}
}
