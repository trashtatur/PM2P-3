package kalender;

import kalender.interfaces.Datum;
import kalender.interfaces.Dauer;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by andi on 25.11.16.
 */
public class DatumImplTest {
    private Datum startdatum=new DatumImpl(new TagImpl(1969,12,1),new UhrzeitImpl(1,0));
    private Datum datum=new DatumImpl(new TagImpl(2016,9,15),new UhrzeitImpl(13,37));
    private Datum abstand= new DatumImpl(new TagImpl(2016,10,15),
                                         new UhrzeitImpl(13,37));
    private Dauer dauer=new DauerImpl(1337);
    private Dauer zerodauer=new DauerImpl(0);



    @Test
    public void add() throws Exception {

    }

    @Test
    public void sub() throws Exception {

    }

    @Test
    public void abstand() throws Exception {

    }

    @Test
    public void differenzInTagen() throws Exception {

    }

    @Test
    public void inMinuten() throws Exception {
        assertEquals(startdatum.inMinuten(),0);
        assertEquals(datum.inMinuten(),24608857); //Zahl ist Jahre in Minuten. Rechnet anscheined nicht
                                                  //ganz exakt. (Das ist nicht der exakte Wert der Minuten der Jahre)
                                                  //TODO Muss nochmal drüberschauen warum das so rechnet
    }

}