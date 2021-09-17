import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SanitizeNonNullableStringTest {
	// basic String
	@Test
	public void test() {
		String out = CombineCSV.sanitizeNonNullableString("hello");
		assertEquals("hello", out);
	}

	// double
	@Test
	public void test2() {
		String out = CombineCSV.sanitizeNonNullableString("123.0");
		assertEquals("123.0", out);
	}

	// embedded quotes
	@Test
	public void test3() {
		String out = CombineCSV.sanitizeNonNullableString("he\"ll\"o");
		assertEquals("he\"ll\"o", out);
	}

	// special chars
	@Test
	public void test4() {
		String out = CombineCSV.sanitizeNonNullableString("test$$$test!!!test***");
		assertEquals("test$$$test!!!test***", out);
	}

	// space
	@Test
	public void test5() {
		String out = CombineCSV.sanitizeNonNullableString("foo bar");
		assertEquals("foo bar", out);
	}

	// null
	@Test
	public void test6() throws Exception {
		Exception exception = assertThrows(NullPointerException.class, () -> {
			CombineCSV.sanitizeNonNullableString("");
		});

		String expectedMessage = "value cannot be null";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	// leading & trailing quotes
	@Test
	public void test7() {
		String out = CombineCSV.sanitizeNonNullableString("\"\"foo bar\"");
		assertEquals("foo bar", out);
	}
}
