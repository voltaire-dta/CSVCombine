import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IsValidLineTest {

	// basic
	@Test
	public void test() {
		CSVLine test = new CSVLine("Pname", "CID12345", Float.parseFloat("5"), "link.com", "512-444-4444", "123 Cove",
				"78704");
		assertEquals(true, test.isValidLine());
	}

	// invalid Provider
	@Test
	public void test2() {
		CSVLine test = new CSVLine("", "CID12345", Float.parseFloat("5"), "link.com", "512-444-4444", "123 Cove",
				"78704");
		assertEquals(false, test.isValidLine());
	}

	// invalid cmpnId
	@Test
	public void test3() {
		CSVLine test = new CSVLine("Pname", "", Float.parseFloat("5"), "link.com", "512-444-4444", "123 Cove", "78704");
		assertEquals(false, test.isValidLine());
	}

	// invalid redirect URL
	@Test
	public void test4() {
		CSVLine test = new CSVLine("Pname", "CID12345", Float.parseFloat("5"), "", "512-444-4444", "123 Cove", "78704");
		assertEquals(false, test.isValidLine());
	}

	// missing phone number
	@Test
	public void test5() {
		CSVLine test = new CSVLine("Pname", "CID12345", Float.parseFloat("5"), "link.com", "", "123 Cove", "78704");
		assertEquals(true, test.isValidLine());
	}

	// invalid address
	@Test
	public void test6() {
		CSVLine test = new CSVLine("Pname", "CID12345", Float.parseFloat("5"), "link.com", "512-444-4444", "", "78704");
		assertEquals(false, test.isValidLine());
	}

	// invalid zip
	@Test
	public void test7() {
		CSVLine test = new CSVLine("Pname", "CID12345", Float.parseFloat("5"), "link.com", "512-444-4444", "123 Cove",
				"");
		assertEquals(false, test.isValidLine());
	}
}
