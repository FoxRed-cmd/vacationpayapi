package com.corpdomain.vacationpayapi.services;

import java.time.LocalDate;

public interface IHolidayCalendarService {
	boolean isHoliday(LocalDate date);
}