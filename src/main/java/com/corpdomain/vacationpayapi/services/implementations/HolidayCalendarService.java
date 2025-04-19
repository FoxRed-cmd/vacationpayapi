package com.corpdomain.vacationpayapi.services.implementations;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.stereotype.Service;

import com.corpdomain.vacationpayapi.helpers.Holiday;
import com.corpdomain.vacationpayapi.services.IHolidayCalendarService;

@Service
public class HolidayCalendarService implements IHolidayCalendarService {
	public static final ArrayList<Holiday> holidays = new ArrayList<>(Arrays.asList(Holiday.values()));

	@Override
	public boolean isHoliday(LocalDate date) {
		return holidays.stream().anyMatch(holiday -> {
			return holiday.getMonth() == date.getMonthValue()
					&& holiday.getDays().anyMatch(day -> day == date.getDayOfMonth());
		});
	}

	@Override
	public boolean isWeekend(DayOfWeek dayOfWeek) {
		return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
	}
}
