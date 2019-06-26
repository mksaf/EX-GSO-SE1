import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParkhausbesitzerTest {
	
	Parkhausbesitzer parkhaus;
	
	@BeforeEach
	void setUp() throws Exception {
		parkhaus = new Parkhausbesitzer();
	}

	@Test
	void test() {
		// parkhaus erfolgreich erstellt
		assertNotEquals(parkhaus, null);
		
		// kunde erstellen
		Kunde k = new Kunde(0, new Date(), "Ticket 1");
		assertNotEquals(k, null);
		
		// kunde dem parkhaus hinzufügen
		parkhaus.kundeHinzufuegen(k);
		
		// kunde output
		System.out.println(k);
		
		// kunde zustand parkend=true?
		assertEquals(true, k.parkend);
		
		// kunde parkhaus verlassen
		parkhaus.kundeVerlaesstParkhaus(k, new Date(), 100L, 5.0f);
		
		// kunde zustand parkend=false?
		assertEquals(false, k.parkend);
		
		// Summe der Einnahmen überprüfen
		Kunde k2 = new Kunde(1, new Date(), "Ticket 2");
		parkhaus.kundeHinzufuegen(k2);
		parkhaus.kundeVerlaesstParkhaus(k2, new Date(), 100L, 5.0f);
		assertEquals(10.0f, parkhaus.getEinnahmen());
		
		// Avg der Einnahmen überprüfen
		assertEquals(5.0f, parkhaus.getEinnahmenAvg());
		
		// Max der Einnahmen überprüfen
		Kunde k3 = new Kunde(2, new Date(), "Ticket 3");
		parkhaus.kundeHinzufuegen(k3);
		parkhaus.kundeVerlaesstParkhaus(k3, new Date(), 10000L, 13.0f);
		assertEquals(13.0f, parkhaus.getMaxEinnahme());
		
	}

}
