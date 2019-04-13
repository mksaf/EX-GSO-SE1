import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author michaelsafonov
 *
 */
public class sequenceDataIFTest {

	sequenceDataIF a1;
	sequenceDataIF a2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		String[][] x = new String[][]{{ "A" , "C" }, { "C", "D" }, { "B", "C" }};
		a1 = new daten(x);
		String[][] y = new String[][]{{ "A" , "C" }, { "C", "D" }, { "B", "C" },{ "D", "E" },{ "E", "F" },{ "F", "G" }};
		a2 = new daten(y);
	}

	/**
	 * Test method for {@link sequenceDataIF#xData()}.
	 */
	@Test
	public void testXDataNotEmpty() throws Exception{
		a1.toString();
		assertNotNull(a1.toString());
	}

	/**
	 * Test method for {@link sequenceDataIF#isWellSorted()}.
	 */
	@Test
	public void testIsWellSorted() throws Exception{
		assertEquals(true, a1.isWellSorted(new String[]{"A","B","C","D"}));
	}
	
	@Test
	public void testWrongCountDetection() throws Exception{
		assertEquals(false, a1.isWellSorted(new String[]{"C","A","B"}));
	}
	
	@Test
	public void testWrongInput() throws Exception{
		assertEquals(false, a1.isWellSorted(new String[]{"4","A","B"}));
		assertEquals(false, a1.isWellSorted(new String[]{"C","A","B","S","T"}));
	}
	
	@Test
	public void testToString() throws Exception{
		assertEquals("|A-C|C-D|B-C||D-E|E-F|F-G", a2.toString());
	}

}
