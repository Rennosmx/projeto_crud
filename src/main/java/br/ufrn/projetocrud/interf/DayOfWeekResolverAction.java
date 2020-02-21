package br.ufrn.projetocrud.interf;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class DayOfWeekResolverAction implements Serializable {

	private final DayOfWeekResolverForm dayOfWeekResolverForm;
	private final DayOfWeekResolverService dayOfWeekResolverService;

	public DayOfWeekResolverAction(@Autowired DayOfWeekResolverForm dayOfWeekResolverForm, @Autowired DayOfWeekResolverService dayOfWeekResolverService) {
		this.dayOfWeekResolverForm = dayOfWeekResolverForm;
		this.dayOfWeekResolverService = dayOfWeekResolverService;
	}

	public void determineDayOfWeek() {

		int year = dayOfWeekResolverForm.getYear();
		int month = dayOfWeekResolverForm.getMonth();
		int dayOfMonth = dayOfWeekResolverForm.getDayOfMonth();

		String dayOfWeekName = dayOfWeekResolverService.determineDayOfWeek(year, month, dayOfMonth);
		dayOfWeekResolverForm.setDayOfWeek(dayOfWeekName);
	}
}