package kalender;

import kalender.interfaces.Dauer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by andi on 24.11.16.
 */
public class DauerImplTest {


    private int zeromin=0;
    private int minuten=22300;
    private Dauer zerodauer= new DauerImpl(0);
    private Dauer dauer=new DauerImpl(22300);


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void isSame() throws Exception {
        assertEquals(zerodauer.anteilWochen()+
                     zerodauer.anteilTage()+
                     zerodauer.anteilStunden()+
                     zerodauer.anteilMinuten(),
                     zeromin);

        assertEquals(dauer.anteilWochen()+
                     dauer.anteilTage()+
                     dauer.anteilStunden()+
                     dauer.anteilMinuten(),
                     minuten);
    }

    @Test
    public void inStunden() throws Exception {
        assertEquals(zerodauer.inStunden(),zeromin/60);
        assertEquals(dauer.inStunden(), this.minuten/60);
    }

    @Test
    public void inTagen() throws Exception {
        assertEquals(zerodauer.inTagen(),zeromin/60/24);
        assertEquals(dauer.inTagen(), (minuten/60)/24);
    }

    @Test
    public void inWochen() throws Exception {
        assertEquals(zerodauer.inWochen(),zeromin/60/24/7);
        assertEquals(dauer.inWochen(), (minuten/60)/24/7);
    }

    @Test
    public void anteilMinuten() throws Exception {
        assertEquals(dauer.anteilMinuten(),40);
    }

    @Test
    public void anteilStunden() throws Exception {
        assertEquals(dauer.anteilStunden(),660);
    }

    @Test
    public void anteilTage() throws Exception {
        assertEquals(dauer.anteilTage(),1440);
    }

    @Test
    public void anteilWochen() throws Exception {
        assertEquals(dauer.anteilWochen(),20160);
    }

}