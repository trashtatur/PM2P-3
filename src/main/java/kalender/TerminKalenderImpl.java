package kalender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import kalender.interfaces.Datum;
import kalender.interfaces.Monat;
import kalender.interfaces.Tag;
import kalender.interfaces.Termin;
import kalender.interfaces.TerminKalender;
import kalender.interfaces.Woche;

public class TerminKalenderImpl implements TerminKalender {

    private  List<Termin> terminelist;

    public TerminKalenderImpl() {
        this.terminelist = new ArrayList<Termin>();
    }


	@Override
	public boolean eintragen(Termin termin) {
		if (this.enthaeltTermin(termin)) {
            return false;
        }
        terminelist.add(termin);
        return true;
	}

	@Override
	public void verschiebenAuf(Termin termin, Datum datum) {
        terminelist.get(terminelist.indexOf(termin)).verschiebeAuf(datum);
    }

	@Override
	public boolean terminLoeschen(Termin termin) {

        return terminelist.removeIf(termindelete -> termindelete.equals(termin));
	}

	@Override
	public boolean enthaeltTermin(Termin termin) {
		return terminelist.stream().anyMatch(terminsearched -> terminsearched.equals(termin));
	}

	@Override
	public Map<Datum, List<Termin>> termineFuerTag(Tag tag) {
        return terminelist
               .stream()
               .flatMap(termin -> termin.termineAn(tag).values().stream())
			   .collect(Collectors.groupingBy(Termin::getDatum));
    }

	@Override
	public Map<Datum, List<Termin>> termineFuerWoche(Woche woche) {
        return terminelist
                .stream()
                .flatMap(termin -> termin.termineIn(woche).values().stream())
                .collect(Collectors.groupingBy(Termin::getDatum));
	}

	@Override
	public Map<Datum, List<Termin>> termineFuerMonat(Monat monat) {
       /* return terminelist
                .stream()
                .collect(Collectors
                        .toMap(Termin::getDatum,   //NNNNGGHHHHH!!! >.<  oder: termin.termineIn(monat).keySet.iterator.next(),
                               termin ->(termin.termineIn(monat).values())
                                        .stream()
                                        .collect(Collectors.toList())
                       ));

                       Wüsste gerne warum diese Version nicht funktioniert :( ist mir nicht ganz klar.
      */
        return terminelist
                .stream()
                .flatMap(termin -> termin.termineIn(monat).values().stream())
                .collect(Collectors.groupingBy(Termin::getDatum));
	}

}
