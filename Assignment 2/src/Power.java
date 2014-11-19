public class Power {
	public static int power(int left, int right) {
		// **************************************
		// Raises Left to the power of Right
		// precondition : Right >= 0
		// postcondition: Returns Left**Right
		// **************************************
		int rslt;
		rslt = left;
		if (right == 0) {
			rslt = 1;
		} else {
			for (int i = 2; i <= right; i++)
				rslt = rslt * left;
		}
		return (rslt);
	}
}
