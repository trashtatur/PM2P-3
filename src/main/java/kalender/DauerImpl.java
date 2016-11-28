package kalender;

import kalender.interfaces.Datum;
import kalender.interfaces.Dauer;
import kalender.interfaces.Monat;
import kalender.interfaces.Tag;
import kalender.interfaces.Uhrzeit;
import kalender.interfaces.Woche;

public class DauerImpl implements Dauer {

    private int minuten;

    public DauerImpl(Datum d1, Datum d2) {
        this(d1.abstand(d2).inMinuten());
    }

    public DauerImpl(int minuten) {
        if (minuten<0) throw new IllegalArgumentException("Uebergebene Argumente duerfen nicht kleiner 0 sein");
        this.minuten = minuten;
    }

    public DauerImpl(int stunden, int minuten) {
        this((stunden * 60) + minuten);
    }

    public DauerImpl(int tage, int stunden, int minuten) {

        this((tage * 24 * 60) + (stunden * 60) + minuten);
    }

    @Override
    public int compareTo(Dauer o) {
        return this.inMinuten() - o.inMinuten();
    }

    @Override
    public int inMinuten() {

        return minuten;
    }

    @Override
    public int inStunden() {

        return this.inMinuten() / 60;
    }

    @Override
    public int inTagen() {

        return this.inStunden() / 24;
    }

    @Override
    public int inWochen() {

        return this.inTagen() / 7;
    }

    @Override
    public int anteilMinuten() {

        return (minuten - this.anteilWochen() - this.anteilTage() - this.anteilStunden());

    }

    @Override
    public int anteilStunden() {

        return (minuten - this.anteilWochen() - this.anteilTage()) -
                (minuten - this.anteilWochen() - this.anteilTage()) % 60;
    }

    @Override
    public int anteilTage() {

        return minuten - this.anteilWochen() - (minuten - this.anteilWochen()) % 1440;
    }

    @Override
    public int anteilWochen() {

        return minuten - (minuten % 10080);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DauerImpl dauer = (DauerImpl) o;

        return minuten == dauer.minuten;

    }

    @Override
    public int hashCode() {
        return minuten;
    }

    @Override
    public String toString() {
        return "DauerImpl{" +
                "minuten=" + minuten +
                '}';

    }
}
