/**
 * @author michaelsafonov
 *
 */
public class daten implements sequenceDataIF {
	String[][] x;

	public daten(String[][] x) {
		// TODO Auto-generated constructor stub
		this.x = x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sequenceDataIF#xData()
	 */
	@Override
	public String xData() {
		// TODO Auto-generated method stub
		return x.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sequenceDataIF#isWellSorted()
	 */
	@Override
	public boolean isWellSorted(String[] x) {
		// TODO Auto-generated method stub
		
		/*
		 * 
		 * DIESE SCHEIßE FERTIG STELLEN
		 * 
		 */
		
		return false;
	}
	
	@Override
	public String toString() {
		String erg = "";
		
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[0].length; j++) {
				erg += x[i][j];
			}
		}
		
		return erg;
	}

}
