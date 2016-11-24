package kalender;

import java.util.Calendar;
import java.util.Map;

import kalender.interfaces.Datum;
import kalender.interfaces.Dauer;
import kalender.interfaces.Monat;
import kalender.interfaces.Tag;
import kalender.interfaces.Termin;
import kalender.interfaces.Woche;

public class TerminImpl implements Termin {

	private Datum datum;
    private Dauer dauer;
    private String beschreibung;

	public TerminImpl(String beschreibung, Datum datum, Dauer dauer) {
        this.datum=datum;
        this.beschreibung=beschreibung;
        this.dauer=dauer;
	}


	@Override
	public int compareTo(Termin o) {
		return this.getDatum().compareTo(o.getDatum());
	}

	@Override
	public String getBeschreibung() {
		return this.beschreibung;
	}

	@Override
	public Datum getDatum() {
		return this.datum;
	}

	@Override
	public Dauer getDauer() {
		return this.dauer;
	}

	@Override
	public Termin verschiebeAuf(Datum datum) {
		this.datum=datum;
        return this;
	}

	@Override
	public Map<Datum, Termin> termineIn(Monat monat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Datum, Termin> termineIn(Woche woche) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Datum, Termin> termineAn(Tag tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TerminImpl termin = (TerminImpl) o;

		if (!getDatum().equals(termin.getDatum())) return false;
		if (!getDauer().equals(termin.getDauer())) return false;
		return getBeschreibung() != null ? getBeschreibung().equals(termin.getBeschreibung()) : termin.getBeschreibung() == null;

	}

	@Override
	public int hashCode() {
		int result = getDatum().hashCode();
		result = 31 * result + getDauer().hashCode();
		result = 31 * result + (getBeschreibung() != null ? getBeschreibung().hashCode() : 0);
		return result;
	}

}
