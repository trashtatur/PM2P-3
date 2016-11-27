package kalender;

import java.util.HashMap;
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
		Map<Datum,Termin> termineInMap=new HashMap<Datum,Termin>();
		if (datum.getMonat().equals(monat)) {
            termineInMap.put(this.datum,this);
        }
        return termineInMap;
	}

	@Override
	public Map<Datum, Termin> termineIn(Woche woche) {
        Map<Datum,Termin> termineInMap=new HashMap<Datum,Termin>();
        if (datum.getWoche().equals(woche)) {
            termineInMap.put(this.datum,this);
        }
        return termineInMap;
	}

	@Override
	public Map<Datum, Termin> termineAn(Tag tag) {
        Map<Datum,Termin> termineInMap=new HashMap<Datum,Termin>();
        if (datum.getTag().equals(tag)) {
            termineInMap.put(this.datum,this);
        }
        return termineInMap;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TerminImpl termin = (TerminImpl) o;

		if (beschreibung != null ? !beschreibung.equals(termin.beschreibung) : termin.beschreibung != null)
			return false;
		if (datum != null ? !datum.equals(termin.datum) : termin.datum != null) return false;
		return dauer != null ? dauer.equals(termin.dauer) : termin.dauer == null;

	}

	@Override
	public int hashCode() {
		int result = beschreibung != null ? beschreibung.hashCode() : 0;
		result = 31 * result + (datum != null ? datum.hashCode() : 0);
		result = 31 * result + (dauer != null ? dauer.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "TerminImpl{" +
				"beschreibung='" + beschreibung + '\'' +
				", datum=" + datum +
				", dauer=" + dauer +
				'}';
	}

}
