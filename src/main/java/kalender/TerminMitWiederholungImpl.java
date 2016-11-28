package kalender;

import java.util.*;

import kalender.interfaces.*;

public class TerminMitWiederholungImpl extends TerminImpl implements TerminMitWiederholung {

	private Wiederholung wdh;

    private TerminKalender kalender;

 // TODO Konstruktorprobleme auflösen
	public TerminMitWiederholungImpl(String beschreibung, Datum start, Dauer dauer, WiederholungType type, int anzahl,
			int zyklus) {
        super(beschreibung,start,dauer);
		wdh=new WiederholungImpl(type,anzahl,zyklus);
	}

	public TerminMitWiederholungImpl(String beschreibung, Datum start, Dauer dauer, Wiederholung wdh) {
        super(beschreibung,start,dauer);
        this.wdh=wdh;
	}


	public Wiederholung getWdh() {

        return new WiederholungImpl(wdh);
	}


	@Override
	public Map<Datum, Termin> termineIn(Monat monat) {
        Map<Datum, Termin> ret = new HashMap<Datum, Termin>();
        /*Map<Datum, List<Termin>> temp = kalender.termineFuerMonat(monat);
        Set<Termin> liste = new HashSet<Termin>();
        for (Map.Entry<Datum, List<Termin>> map : temp.entrySet()) {
            for (Termin termine : map.getValue()) {
                if (liste.contains(termine)) {
                    ret.put(map.getKey(), termine);
                } else {
                    liste.add(termine);
                }
            }
        }*/
        return ret;
}

	@Override
	public Map<Datum, Termin> termineIn(Woche woche) {
        Map<Datum, Termin> ret = new HashMap<Datum, Termin>();
        /*Map<Datum, List<Termin>> temp = kalender.termineFuerWoche(woche);
        Set<Termin> liste = new HashSet<>();
        for (Map.Entry<Datum, List<Termin>> map : temp.entrySet()) {
            for (Termin termine : map.getValue()) {
                if (liste.contains(termine)) {
                    ret.put(map.getKey(), termine);
                } else {
                    liste.add(termine);
                }
            }
        }
        */return ret;
    }

	@Override
	public Map<Datum, Termin> termineAn(Tag tag) {
        Map<Datum, Termin> ret = new HashMap<Datum, Termin>();
        /*Map<Datum, List<Termin>> temp = kalender.termineFuerTag(tag);
        Set<Termin> liste = new HashSet<Termin>();
        for (Map.Entry<Datum, List<Termin>> map : temp.entrySet()) {
            for (Termin termine : map.getValue()) {
                if (liste.contains(termine)) {
                    ret.put(map.getKey(), termine);
                } else {
                    liste.add(termine);
                }
            }
        }
        */return ret;
	}

    @Override
    public Map<Datum, Termin> termineFuer(DatumsGroesse groesse) {
        // TODO Indizes fuer Start und End Intervall berechnen
		int startindex=this.getWdh().naechstesIntervall(groesse.getStart());
		int endindex=this.getWdh().naechstesIntervall(groesse.getEnde());
        // TODO Indizes auf Gültigkeit prüfen
        // wenn endIndex > maxIntervallIndex dann setze endIndex auf
        // maxIntervallIndex
        if (endindex>this.getWdh().maxIntervallIndex()) {
            endindex=this.getWdh().maxIntervallIndex();
        }
        // wenn endIndex < startIndex || endIndex < 0 || startIndex < 0 ||
        // endIndex > maxIntervallIndex
        // gib null zurück
        if (endindex<startindex||endindex<0||startindex<0||endindex>this.getWdh().maxIntervallIndex()) {
            return null;
        }
        // TODO hier den Intervalliterator nutzen
        // Map erzeugen und die Wiederholungen einsammeln
        Map <Datum,Termin> returnmap=new HashMap<>();
        IntervallIterator<Datum> iterator = intervallIterator(startindex,endindex);
        while(iterator.hasNext()) {
            Datum anotherOne=iterator.next();
            returnmap.put(anotherOne
                          ,new TerminMitWiederholungImpl(this.getBeschreibung()
                                                         ,anotherOne
                                                         ,this.getDauer()
                                                         ,this.getWdh().sub(1)));
        }

        return returnmap;
    }

	
	/**
	 * Beispiel für den naiven Iterator, der alle Wiederholungen explizit aufzaehlt
	 */
	@Override
	public Iterator<Termin> iterator() {
		return new Iterator<Termin>() {
			private TerminMitWiederholung current = null;
			private int howManySeen = 0;

			@Override
			public boolean hasNext() {
				return howManySeen <= wdh.anzahl();
			}

			@Override
			public Termin next() {
				if (current == null) {
					current = TerminMitWiederholungImpl.this;
				} else {
					current = new TerminMitWiederholungImpl(getBeschreibung(), current.getWdh().naechstesDatum(),
							getDauer(), current.getWdh().sub(1));
				}
				howManySeen += 1;
				return current;
			}
		};
	}


	@Override
	public IntervallIterator<Datum> intervallIterator(int von, int bis) {
		return new IntervallIterator<Datum>() {
			private int upperBound=bis;
			private int counter = von;

			@Override
			public boolean hasNext() {
                return counter <=upperBound && counter <= TerminMitWiederholungImpl.this.getWdh().maxIntervallIndex();
			}   //Checkt einmal ob es unter oder gleich der oberen Grenze ist
                //und zweitens ob es unter oder gleich dem maximalen Index des Intervalls ist
                //TerminMitWiederholungImpl ist hierbei die enclosing Class aus der die Wdh geprüft wird.

			@Override
			public Datum next() {

                    return TerminMitWiederholungImpl.this.getWdh().naechstesDatum(counter++);
				}   //Zugriff auf die Wiederholung der enclosing Class und per
                    //Methode der Subklasse Wiederholung wird das nächste Datum
                    //bezogen auf den Counter der das jetzige wiedergibt um eins hoch
                    //gesetzt. Also auf das Nächste Datum.

		};
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TerminMitWiederholungImpl other = (TerminMitWiederholungImpl) o;

        return wdh != null ? wdh.equals(other.wdh) : other.wdh == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (wdh != null ? wdh.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TerminMitWiederholung{"+super.toString()+"," +
                "wdh=" + wdh +
                '}';
    }


	public class WiederholungImpl implements Wiederholung {

		private WiederholungType wdhType;
		private int anzahl;
		private int cycle;

		public WiederholungImpl(WiederholungType wdhType, int anzahl, int cyclus) {
			this.wdhType = wdhType;
			this.anzahl = anzahl;
			this.cycle = cyclus;
		}

		public WiederholungImpl(Wiederholung wdh) {
			this(wdh.getType(), wdh.anzahl(), wdh.getZyklus());
		}
		@Override
		public WiederholungType getType() {
			return wdhType;
		}
		@Override
		public int getZyklus() {
			return cycle;
		}
		@Override
		public int anzahl() {
			return anzahl;
		}
		@Override
		public int maxIntervallIndex() {
			return anzahl;
		}
		@Override
		public int intervallLaenge() {
			return cycle * wdhType.inTagen();
		}
		/*
		 * @see kalender.interfaces.Wiederholung#naechstesIntervall(kalender.interfaces.Datum)
		 * 
		 * Methode liefert den Intervallindex für das einem Datum nachfolgendem
		 * Intervall. Es werden auch Intervalle berechnet, die außerhalb des
		 * gültigen Bereichs maxIntervallIndex liegen. Nutzer der Methode müssen
		 * sicher stellen, dass die Gültigkeit des Index geprüft wird.
		 */
		@Override
		public int naechstesIntervall(Datum dat) {
			long diff = dat.differenzInTagen(getDatum());
			long div = diff / intervallLaenge();
			long mod = diff % intervallLaenge();

			/*
			 * div <= 0 und mod < 0: tag liegt vor dem ersten Termin der
			 * Wiederholung (Intervall 0) div > 0 && mod > 0: tag liegt vor dem
			 * Termin im Intervall div+1 div >= 0 && mod == 0: tag ist ein
			 * Termin der Wiederholung im Intervall div
			 */
			int intervallIndex = -1;
			if (div <= 0 && mod < 0)
				intervallIndex = 0;
			if (diff > 0 && mod > 0)
				intervallIndex = (int) div + 1;
			if (diff >= 0 && mod == 0)
				intervallIndex = (int) div;
			return intervallIndex;
		}

		/*
		 * @see kalender.interfaces.Wiederholung#vorherigesIntervall(kalender.
		 * interfaces.Datum)
		 * 
		 * Methode liefert den Intervallindex für das einem Datum vorausgehenden
		 * Intervall. Es werden auch Intervalle berechnet, die außerhalb des
		 * gültigen Bereichs maxIntervallIndex liegen. Nutzer der Methode müssen
		 * sicher stellen, dass die Gültigkeit des Index geprüft wird.
		 */
		@Override
		public int vorherigesIntervall(Datum dat) {
			long diff = dat.differenzInTagen(getDatum());
			long div = diff / intervallLaenge();
			long mod = diff % intervallLaenge();

			/*
			 * diff < 0: dann liegt das Datum vor dem ersten Termin Fehler div
			 * >= 0 && mod = 0: dann interval = div sonst intervall =
			 * (naechstesIntervall(dat) -1)
			 */

			if (diff < 0)
				return -1;
			if (div >= 0 && mod == 0)
				return (int) div;
			return naechstesIntervall(dat) - 1;
		}

		/*
		 * @see kalender.interfaces.Wiederholung#naechstesDatum()
		 */
		@Override
		public Datum naechstesDatum() {
			return naechstesDatum(1);
		}
		/*
		 * @see kalender.interfaces.Wiederholung#naechstesDatum(int)
		 */
		@Override
		public Datum naechstesDatum(int faktor) {
			int anzahlTage = faktor * intervallLaenge();
			return new DatumImpl(getDatum()).add(new DauerImpl(anzahlTage, 0, 0));
		}
		/*
		 * @see kalender.interfaces.Wiederholung#sub(int)
		 */
		@Override
		public Wiederholung sub(int wdhCount) {

            return new WiederholungImpl(wdhType, anzahl - wdhCount, cycle);
		}
		/*
		 * @see kalender.interfaces.Wiederholung#add(int)
		 */
		@Override
		public Wiederholung add(int wdhCount) {

            return new WiederholungImpl(wdhType, anzahl + wdhCount, cycle);
		}

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            WiederholungImpl that = (WiederholungImpl) o;

            if (anzahl != that.anzahl) return false;
            if (cycle != that.cycle) return false;
            return wdhType == that.wdhType;

        }

        @Override
        public int hashCode() {
            int result = wdhType != null ? wdhType.hashCode() : 0;
            result = 31 * result + anzahl;
            result = 31 * result + cycle;
            return result;
        }

        @Override
        public String toString() {
            return "WiederholungImpl{" +
                    "wdhType=" + wdhType +
                    ", anzahl=" + anzahl +
                    ", cycle=" + cycle +
                    '}';
        }


	}

}
