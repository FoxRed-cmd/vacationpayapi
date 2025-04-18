package com.corpdomain.vacationpayapi.services.implementations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.corpdomain.vacationpayapi.models.VacationRequestParameters;
import com.corpdomain.vacationpayapi.services.IHolidayCalendarService;
import com.corpdomain.vacationpayapi.services.IVacationPayCalculatorService;

@Service
public class VacationPayCalculatorService implements IVacationPayCalculatorService {

	private final IHolidayCalendarService holidayCalendar;

	public VacationPayCalculatorService(IHolidayCalendarService holidayCalendar) {
		this.holidayCalendar = holidayCalendar;
	}

	@Override
	public double calculateVacationPay(VacationRequestParameters request) {
		if (request.getStartDate() == null) {
			return calculate(request.getAverageSalary(), request.getVacationDays());
		}
		else {
			int countDays = request.getVacationDays();
			LocalDate startDate = request.getStartDate();

			for (int i = 0; i <= request.getVacationDays(); i++) {
				if (holidayCalendar.isHoliday(startDate.plusDays(i))) {
					countDays -= 1;
				}
			}
			return calculate(request.getAverageSalary(), countDays);
		}
	}

	private double calculate(double averageSalary, int vacationDays) {
		BigDecimal bd = new BigDecimal((averageSalary / 29.3) * vacationDays);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

}
