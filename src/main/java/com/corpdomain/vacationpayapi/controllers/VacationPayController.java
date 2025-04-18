package com.corpdomain.vacationpayapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.corpdomain.vacationpayapi.models.VacationPayResponse;
import com.corpdomain.vacationpayapi.models.VacationRequestParameters;
import com.corpdomain.vacationpayapi.services.IVacationPayCalculatorService;

import javax.validation.Valid;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@RestController
@Validated
public class VacationPayController {
	private final IVacationPayCalculatorService vacationPayService;

	public VacationPayController(IVacationPayCalculatorService vacationPayService) {
		this.vacationPayService = vacationPayService;
	}

	@GetMapping("/calculate")
	public ResponseEntity<VacationPayResponse> calculateVacationPay(
			@Valid @ParameterObject @ModelAttribute VacationRequestParameters requestParameters,
			BindingResult bindingResult) {
		double result = vacationPayService.calculateVacationPay(requestParameters);
		return ResponseEntity.ok(new VacationPayResponse(result));
	}
}
