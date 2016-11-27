package kalender;

import java.util.Calendar;

import kalender.interfaces.Datum;
import kalender.interfaces.Tag;

public class TagImpl implements Tag {

	private Calendar intern; 
	
	public TagImpl(int jahr, int tagImJahr) {
		intern = Calendar.getInstance();
		intern.set(jahr,tagImJahr);
	}
	public TagImpl(int jahr, int monat, int tagImMonat) {
		intern = Calendar.getInstance();
		intern.set(jahr,monat,tagImMonat);
	}
	
	public TagImpl(Tag tag) {
		intern = Calendar.getInstance();
		intern.set(tag.getJahr(),tag.getMonat(),tag.getTagImMonat());
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
		copy.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new DatumImpl(
				new TagImpl(copy.get(Calendar.YEAR), copy.get(Calendar.MONTH), copy.get(Calendar.DAY_OF_MONTH)),
				new UhrzeitImpl(23, 59));
	}

	@Override
	public int compareTo(Tag o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getJahr() {
		return intern.get(Calendar.YEAR);
	}

	@Override
	public int getMonat() {
		return intern.get(Calendar.MONTH);
	}

	@Override
	public int getTagImJahr() {
		return intern.get(Calendar.DAY_OF_YEAR);
	}

	@Override
	public int getTagImMonat() {
		return intern.get(Calendar.DAY_OF_MONTH);
	}

	@Override
	public long differenzInTagen(Tag other) {
		int dayOther = other.getTagImJahr() + other.getJahr() * 365;		//Get day of year to substract from
		return Math.abs(dayOther - (getTagImJahr()+ (getJahr() * 365)));
	}

	@Override
	public Calendar inBasis() {
		Calendar copy = (Calendar) intern.clone();
		return copy;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagImpl tag = (TagImpl) o;

        return intern != null ? intern.equals(tag.intern) : tag.intern == null;

    }

    @Override
    public int hashCode() {
        return intern != null ? intern.hashCode() : 0;
    }
}
