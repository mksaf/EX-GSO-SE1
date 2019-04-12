import java.util.Arrays;
import java.util.HashSet;

/**
 * @author michaelsafonov
 *
 */
public class daten implements sequenceDataIF {
	private String[][] myData;

	public daten(String[][] x) {
		// TODO Auto-generated constructor stub
		this.myData = x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sequenceDataIF#xData()
	 */
	@Override
	public String xData() {
		// TODO Auto-generated method stub
		return myData.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sequenceDataIF#isWellSorted()
	 */
	@Override
	public boolean isWellSorted(String[] x) throws Exception {
		// TODO Auto-generated method stub

		if (!uniqueData(x) || !sameLengthUniqueData(x)) {
			return false;
		}

		boolean erg = true;

		for (int i = 0; i < myData.length; i++) {
			for (int j = 0; j < x.length - 1; j++) {
				int t1 = -1;
				int t2 = -1;

				for (int k = j + 1; k < x.length; k++) {
					try {
						t1 = find(myData[i], x[j]);
						t2 = find(myData[i], x[k]);
					} catch (Exception e) {
						// TODO: handle exception
						throw new Exception("Array mit nur einem Element vorhanden!");
					}
				}

				if (t1 > t2 && t1 != -1 && t2 != -1) {
					return false;
				}
			}
		}

		return erg;
	}

	@Override
	public String toString() {
		String erg = "";

		for (int i = 0; i < myData.length; i++) {
			erg += "|";
			for (int j = 0; j < myData[0].length; j++) {
				erg += myData[i][j] + ((j < myData[0].length - 1) ? "-" : "");
			}
			erg += (i == myData[i].length) ? "|" : "";
		}

		return erg;
	}

	private int find(String[] x, String target) {
		for (int i = 0; i < x.length; i++) {
			if (x[i] == target) {
				return i;
			}
		}

		return -1;
	}

	private boolean uniqueData(String[] x) {
		String[] unique = new HashSet<String>(Arrays.asList(x)).toArray(new String[0]);

		return (unique.length == x.length) ? true : false;
	}

	private boolean sameLengthUniqueData(String[] x) {
		String[] first = null;
		String[] second;

		if (myData.length > 1) {

			for (int i = 0; i < myData.length-1; i++) {
				if (i == 0) {
					first = myData[i];
				}
				second = myData[i + 1];
				first = combine(first, second);
			}
		}
		int l1 = new HashSet<String>(Arrays.asList(x)).toArray(new String[0]).length;
		int l2 = new HashSet<String>(Arrays.asList(first)).toArray(new String[0]).length;


		return (l1 == l2) ? true : false;
	}

	private String[] combine(String[] a, String[] b) {
		int length = a.length + b.length;
		String[] result = new String[length];
		System.arraycopy(a, 0, result, 0, a.length);
		System.arraycopy(b, 0, result, a.length, b.length);
		return result;
	}

}
