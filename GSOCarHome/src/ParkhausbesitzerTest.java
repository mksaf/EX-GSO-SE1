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
		
		// kunde output
		System.out.println(k);
		
		// kunde parkhaus verlassen
		parkhaus.kundeVerlaesstParkhaus(k, new Date(), 100L, 5.0f);
	}

}
