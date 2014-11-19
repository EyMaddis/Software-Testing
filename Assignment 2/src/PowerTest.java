import junit.framework.TestCase;

public class PowerTest extends TestCase {

	public void testPower() {
		
		// 20^10 > Integer.MAX_VALUE therefore integer overflow
		int[] testLeft  = new int[]{1, 0, 2, Integer.MAX_VALUE, 1,  2,  10, 20};
		int[] testRight = new int[]{0, 0, 0, 0, 				99, 16, 4,  10};
		
		for (int i=0; i < testLeft.length; i++){
			int left = testLeft[i];
			int right = testRight[i];
			
			// can't use Math.pow because of integer overflow.
			int expected = left;
			
			for (int y = 2; y <= right; y++)
				expected = expected * left;
			if(right == 0) expected = 1;
			
			assertEquals("Iteration "+i+" ("+left+","+right+")",
					expected, Power.power(left, right));
		}
	}
}
