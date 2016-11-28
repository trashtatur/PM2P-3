package kalender;

import java.util.Calendar;

import kalender.interfaces.Datum;
import kalender.interfaces.Tag;

public class TagImpl implements Tag {

	private Calendar intern; 
	
	public TagImpl(int jahr, int tagImJahr) {
		if (jahr<0||tagImJahr<0) throw new IllegalArgumentException("Uebergebe Argumente fuer Tag nicht korrekt");
		intern = Calendar.getInstance();
		intern.clear();
		intern.set(Calendar.YEAR,jahr);
		intern.set(Calendar.DAY_OF_YEAR,tagImJahr);
	}
	public TagImpl(int jahr, int monat, int tagImMonat) {
        if (jahr<0||tagImMonat<0) throw new IllegalArgumentException("Uebergebe Argumente fuer Tag nicht korrekt");
		intern = Calendar.getInstance();
		intern.clear();
		intern.set(Calendar.YEAR,jahr);
		intern.set(Calendar.MONTH,monat);
		intern.set(Calendar.DAY_OF_MONTH,tagImMonat);
	}
	
	public TagImpl(Tag tag) {
		intern = Calendar.getInstance();
		intern.set(tag.getJahr(),tag.getMonat(),tag.getTagImMonat());
	}

	@Override
	public Datum getStart() {
		Calendar copy = (Calendar) intern.clone();
		copy.set(Calendar.HOUR_OF_DAY, 0);
		return new DatumImpl(
				new TagImpl(copy.get(Calendar.YEAR), copy.get(Calendar.MONTH), copy.get(Calendar.DAY_OF_MONTH)));
	}

	@Override
	public Datum getEnde() {
		Calendar copy = (Calendar) intern.clone();
		copy.set(Calendar.HOUR_OF_DAY, copy.getActualMaximum(Calendar.HOUR_OF_DAY));
		copy.set(Calendar.MINUTE, copy.getActualMaximum(Calendar.MINUTE));
		return new DatumImpl(
				new TagImpl(copy.get(Calendar.YEAR), copy.get(Calendar.MONTH), copy.get(Calendar.DAY_OF_MONTH)),
				new UhrzeitImpl(23, 59));
	}

	@Override
	public int compareTo(Tag o) {
		return (int) this.differenzInTagen(o);
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
		return (long) ((intern.getTimeInMillis() - other.inBasis().getTimeInMillis()) * 0.001 / 60 / 60 / 24);
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
