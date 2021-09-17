
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SanitizeFloatTest {
	// Decimal
	@Test
	public void test() throws Exception {
		Float out = CombineCSV.sanitizeFloat("2.0");
		assertEquals(2, out);
	}

	// Zero Test
	@Test
	public void test2() throws Exception {
		Float out = CombineCSV.sanitizeFloat("0");
		assertEquals(0, out);
	}

	// Quoted Vals
	@Test
	public void test3() throws Exception {
		Float out = CombineCSV.sanitizeFloat("\"15.00\"");
		assertEquals(15, out);
	}

	// Sub-Zero Vals
	@Test
	public void test4() throws Exception {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			CombineCSV.sanitizeFloat("-1");
		});

		String expectedMessage = "No Negative Numbers!";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	// Non-Number Vals
	@Test
	public void test5() throws Exception {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			CombineCSV.sanitizeFloat("-1");
		});

		String expectedMessage = "No Negative Numbers!";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}
}
