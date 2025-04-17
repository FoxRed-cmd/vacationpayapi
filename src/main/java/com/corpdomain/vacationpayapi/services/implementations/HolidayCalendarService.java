package com.corpdomain.vacationpayapi.services.implementations;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.corpdomain.vacationpayapi.services.IHolidayCalendarService;

@Service
public class HolidayCalendarService implements IHolidayCalendarService {
	public static final Set<LocalDate> HOLIDAYS = Set.of(
			LocalDate.of(2025, 2, 23),
			LocalDate.of(2025, 3, 8),
			LocalDate.of(2025, 5, 1),
			LocalDate.of(2025, 5, 9),
			LocalDate.of(2025, 6, 12),
			LocalDate.of(2025, 11, 4),
			LocalDate.of(2025, 1, 1),
			LocalDate.of(2025, 1, 2),
			LocalDate.of(2025, 1, 3),
			LocalDate.of(2025, 1, 4),
			LocalDate.of(2025, 1, 5),
			LocalDate.of(2025, 1, 6),
			LocalDate.of(2025, 1, 8));

	@Override
	public boolean isHoliday(LocalDate date) {
		return HOLIDAYS.stream().anyMatch(holiday -> {
			return holiday.getDayOfMonth() == date.getDayOfMonth()
					&& holiday.getMonthValue() == date.getMonthValue();
		});
	}

}
