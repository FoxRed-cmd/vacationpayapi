package com.corpdomain.vacationpayapi.services.implementations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.corpdomain.vacationpayapi.helpers.Holiday;
import com.corpdomain.vacationpayapi.models.VacationRequestParameters;
import com.corpdomain.vacationpayapi.services.IHolidayCalendarService;

@ExtendWith(MockitoExtension.class)
public class VacationPayControllerTest {
	private static final double AVERAGE_SALARY = 50000.0;
	private static final int VACATION_DAYS = 14;

	private IHolidayCalendarService holidayCalendarService;

	private VacationPayCalculatorService payCalculationService;

	@BeforeEach
	void setUp() {
		holidayCalendarService = new HolidayCalendarService();
		payCalculationService = new VacationPayCalculatorService(holidayCalendarService);
	}

	@Test
	void testCalculateVacationPay_WithoutStartDate() {
		VacationRequestParameters vacationParams = new VacationRequestParameters();
		vacationParams.setAverageSalary(AVERAGE_SALARY);
		vacationParams.setVacationDays(VACATION_DAYS);

		// 50000.00 / 29.3 * 14 = 23890.78
		assertEquals(23890.78, payCalculationService.calculateVacationPay(vacationParams));
	}

	@Test
	void testCalculateVacationPay_WithStartDate() {
		VacationRequestParameters vacationParams = new VacationRequestParameters();
		vacationParams.setAverageSalary(AVERAGE_SALARY);
		vacationParams.setVacationDays(VACATION_DAYS);
		vacationParams.setStartDate(LocalDate.of(2025, 5, 1));

		// 50000.00 / 29.3 * 14 - 2(Holiday) = 20477.82
		assertEquals(20477.82, payCalculationService.calculateVacationPay(vacationParams));
	}

	@Test
	void VacationPayControllerTest_WithHolidays() {
		VacationRequestParameters parameters = new VacationRequestParameters();
		parameters.setAverageSalary(AVERAGE_SALARY);
		parameters.setVacationDays(VACATION_DAYS);
		parameters.setStartDate(Holiday.NEW_YEAR_HOLIDAYS.getDates().findFirst().get());

		var result = payCalculationService.calculateVacationPay(parameters);

		// 50000.0 / 29.3 * (14 - 8(Holidays)) = 10238.91
		assertEquals(10238.91, result);
	}

	@Test
	void testCalculateVacationPay_WithoutHolidays() {
		VacationRequestParameters vacationParams = new VacationRequestParameters();
		vacationParams.setAverageSalary(AVERAGE_SALARY);
		vacationParams.setVacationDays(VACATION_DAYS);
		vacationParams.setStartDate(LocalDate.of(2025, 7, 20));

		// 50000.00 / 29.3 * 14 = 23890.78
		assertEquals(23890.78, payCalculationService.calculateVacationPay(vacationParams));
	}
}
