package com.corpdomain.vacationpayapi.services;

import java.time.DayOfWeek;
import java.time.LocalDate;

public interface IHolidayCalendarService {
	boolean isHoliday(LocalDate date);

	boolean isWeekend(DayOfWeek date);
}