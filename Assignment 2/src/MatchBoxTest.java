import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import junit.framework.TestCase;


public class MatchBoxTest extends TestCase { // kill all the mutants

	MatchBox box = new MatchBox(100,200,50,10);
	
	public void testInheritance() {
		assertTrue(box instanceof Box1);
		
		// verify that MatchBox does not override values from Box1
		boolean exists = false;
		for (Field field: Box1.class.getDeclaredFields()){
			exists = false;
			try {
				MatchBox.class.getDeclaredField(field.getName());
			} catch (SecurityException e) {
				fail(e.getMessage());
			} catch (NoSuchFieldException e) {
				exists = true;
			}
			assertTrue("Inheritance test for field: "+field.getName(), exists);
		}
	}

	public void testMatchBoxDoubleDoubleDoubleDouble() {
		assertEquals(100, box.width, 0.001);
		assertEquals(200, box.height, 0.001);
		assertEquals(50, box.depth, 0.001);
		assertEquals(10, box.weight, 0.001);
		assertEquals(0D, MatchBox.result, 0.001);

		box.setVolume();
		assertEquals(100*200*50, box.getVolume(), 0.001);
		
		box = new MatchBox(300, 500, 10, 42);
		
		assertEquals(300, box.width, 0.001);
		assertEquals(500, box.height, 0.001);
		assertEquals(10, box.depth, 0.001);
		assertEquals(42, box.weight, 0.001);
		box.setVolume();
		assertEquals(300*500*10, box.getVolume(), 0.001);
		
	}
	

	public void testStaticFields(){
		// Matchbox should only have static result, nothing more.
		verifyStaticResultOnly(Box1.class);
		verifyStaticResultOnly(MatchBox.class);
	}
	
	private void verifyStaticResultOnly(Class<?> clazz){
		for(Field field : clazz.getDeclaredFields()){
			boolean isStatic = Modifier.isStatic(field.getModifiers());
			
			// only 'result' should be static
			if(field.getName().equalsIgnoreCase("result")){
				assertTrue(isStatic);		
			}
			else {
				assertFalse(isStatic);
			}
		}
	}
}
