package kalender;

import java.util.Calendar;

import kalender.interfaces.Datum;
import kalender.interfaces.Dauer;
import kalender.interfaces.Monat;
import kalender.interfaces.Tag;
import kalender.interfaces.Uhrzeit;
import kalender.interfaces.Woche;

public class MonatImpl implements Monat {

	private Calendar intern;

	public MonatImpl(int jahr, int monat) {
		intern=Calendar.getInstance();
        intern.clear();
        intern.set(Calendar.YEAR, jahr);
        intern.set(Calendar.MONTH, monat);
	}

	@Override
	public Datum getStart() {
		Calendar copy = (Calendar) intern.clone();
		copy.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return new DatumImpl(
				new TagImpl(copy.get(Calendar.YEAR), copy.get(Calendar.MONTH), copy.get(Calendar.DAY_OF_MONTH)));
	}

	@Override
	public Datum getEnde() {
		Calendar copy = (Calendar) intern.clone();
		copy.set(Calendar.DAY_OF_MONTH, Calendar.SUNDAY);
		return new DatumImpl(
				new TagImpl(copy.get(Calendar.YEAR), copy.get(Calendar.MONTH), copy.get(Calendar.DAY_OF_MONTH)),
				new UhrzeitImpl(23, 59));
	}

	@Override
	public int getMonat() {
		Calendar copy = (Calendar) intern.clone();
		return copy.get(Calendar.MONTH);
	}

	@Override
	public int getJahr() {
		Calendar copy = (Calendar) intern.clone();
		return copy.get(Calendar.YEAR);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MonatImpl monat = (MonatImpl) o;

		return intern != null ? intern.equals(monat.intern) : monat.intern == null;

	}

	@Override
	public int hashCode() {
		return intern != null ? intern.hashCode() : 0;
	}

}
