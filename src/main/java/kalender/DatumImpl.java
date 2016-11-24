package kalender;

import java.util.Calendar;

import kalender.interfaces.Datum;
import kalender.interfaces.Dauer;
import kalender.interfaces.Monat;
import kalender.interfaces.Tag;
import kalender.interfaces.Uhrzeit;
import kalender.interfaces.Woche;

public class DatumImpl implements Datum {


	private Calendar intern;
	
	public DatumImpl(Tag tag){
		intern=Calendar.getInstance();
		intern.clear();
		intern.set(Calendar.YEAR,tag.getJahr());
		intern.set(Calendar.DAY_OF_YEAR,tag.getTagImJahr());
		intern.set(Calendar.MONTH,tag.getTagImMonat());
	}
	public DatumImpl(Tag tag, Uhrzeit uhrzeit ) {
		this(tag);
		intern.set(Calendar.HOUR,uhrzeit.getStunde());
		intern.set(Calendar.MINUTE,uhrzeit.getMinuten());
	}

	public DatumImpl(Datum d) {
		this(d.getTag(),d.getUhrzeit());
	}

	private DatumImpl(Calendar intern) {
		this.intern=intern;
	}
	
	
	@Override
	public int compareTo(Datum o) {

		return this.inMinuten()-o.inMinuten();
	}

	@Override
	public Tag getTag() {
		return new TagImpl(intern.get(Calendar.YEAR),
				           intern.get(Calendar.MONTH),
				           intern.get(Calendar.DAY_OF_MONTH));
	}

	@Override
	public Woche getWoche() {
		return new WocheImpl(intern.get(Calendar.YEAR),
							 intern.get(Calendar.MONTH),
				             intern.get(Calendar.WEEK_OF_MONTH));
	}

	@Override
	public Monat getMonat() {
		return new MonatImpl(intern.get(Calendar.YEAR),
				             intern.get(Calendar.MONTH));

	}

	@Override
	public Uhrzeit getUhrzeit() {
		return new UhrzeitImpl(intern.get(Calendar.HOUR),
				               intern.get(Calendar.MINUTE));
	}

	@Override
	public int getJahr() {

		return intern.get(Calendar.YEAR);
	}

	@Override
	public int getTagImMonat() {

		return intern.get(Calendar.DAY_OF_MONTH);
	}

	@Override
	public int getTagImJahr() {

		return intern.get(Calendar.DAY_OF_YEAR);
	}

	@Override
	public int getWocheImMonat() {

		return intern.get(Calendar.WEEK_OF_MONTH);
	}

	@Override
	public int getWocheImJahr() {

		return intern.get(Calendar.WEEK_OF_YEAR);
	}

	@Override
	public int getMonatImJahr() {

		return intern.get(Calendar.MONTH);
	}

	@Override
	public Datum add(Dauer dauer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Datum sub(Dauer dauer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dauer abstand(Datum d) {

		return new DauerImpl(this.compareTo(d));

	}

	@Override
	public long differenzInTagen(Datum d) {

        return this.abstand(d).inTagen();
	}

	@Override
	public int inMinuten() {

        return (int) (intern.getTimeInMillis() / 100.0 / 60);
	}

	@Override
	public Calendar inBasis() {
		return this.intern= (Calendar) intern.clone();
	}

}
