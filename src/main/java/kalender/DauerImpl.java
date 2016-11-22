package kalender;

import java.util.Calendar;

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
		this.minuten=minuten;
	}
	
	public DauerImpl(int stunden, int minuten) {
		this((stunden*60)+minuten);
	}

	public DauerImpl(int tage, int stunden, int minuten) {
		this((tage*24*60)+(stunden*60)+minuten);
	}

	@Override
	public int compareTo(Dauer o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int inMinuten() {
		return minuten;
	}

	@Override
	public int inStunden() {
		return this.inMinuten()/60;
	}

	@Override
	public int inTagen() {
		return this.inStunden()/24;
	}

	@Override
	public int inWochen() {
		return this.inTagen()/7;
	}

	@Override
	public int anteilMinuten() {
		return minuten-(this.inStunden()*60);
	}

	@Override
	public int anteilStunden() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int anteilTage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int anteilWochen() {

	}

}
