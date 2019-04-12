import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author michaelsafonov
 *
 */
public class sequenceDataIFTest {

	sequenceDataIF a1;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		String[][] x = new String[][]{{ "A" , "C" }, { "C", "D" }, { "B", "C" }};
		a1 = new daten(x);
		
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
		assertEquals(false, a1.isWellSorted(new String[]{"A,B,C"}));
	}

}
