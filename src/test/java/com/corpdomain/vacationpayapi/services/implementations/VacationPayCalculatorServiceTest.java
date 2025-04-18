package com.corpdomain.vacationpayapi.services.implementations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.corpdomain.vacationpayapi.models.VacationRequestParameters;
import com.corpdomain.vacationpayapi.services.IHolidayCalendarService;

@ExtendWith(MockitoExtension.class)
public class VacationPayCalculatorServiceTest {
	private static final double AVERAGE_SALARY = 50000.0;
	private static final int VACATION_DAYS = 14;
	private static final LocalDate START_DATE = LocalDate.of(2025, 4, 1);
	private static final LocalDate START_DATE_WITH_HOLIDAY = LocalDate.of(2025, 6, 1);
	private static final LocalDate HOLIDAY_DATE = LocalDate.of(2025, 6, 12);

	IHolidayCalendarService holidayCalendarService;

	VacationPayCalculatorService payCalculationService;

	@BeforeEach
	void setUp() {
		holidayCalendarService = mock(IHolidayCalendarService.class, withSettings().lenient());
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
		vacationParams.setStartDate(START_DATE_WITH_HOLIDAY);

		when(holidayCalendarService.isHoliday(START_DATE)).thenReturn(true);
		when(holidayCalendarService.isHoliday(HOLIDAY_DATE)).thenReturn(true);

		// 50000.00 / 29.3 * 14 - 1(Holiday) = 22184.30
		assertEquals(22184.30, payCalculationService.calculateVacationPay(vacationParams));
	}

	@Test
	void testCalculateVacationPay_WithoutHolidays() {
		VacationRequestParameters vacationParams = new VacationRequestParameters();
		vacationParams.setAverageSalary(AVERAGE_SALARY);
		vacationParams.setVacationDays(VACATION_DAYS);
		vacationParams.setStartDate(START_DATE);

		when(holidayCalendarService.isHoliday(HOLIDAY_DATE)).thenReturn(true);

		// 50000.00 / 29.3 * 14 = 23890.78
		assertEquals(23890.78, payCalculationService.calculateVacationPay(vacationParams));
	}
}
