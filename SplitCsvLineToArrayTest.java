import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class SplitCsvLineToArrayTest {

	@Test
	public void test() {
		String[] out = { "Pname", "CID12345", "5", "link.com", "512-444-4444", "123 Cove", "78704" };
		String one = Arrays.toString(out);
		String test = Arrays
				.toString(CombineCSV.splitCsvLineToArray("Pname,CID12345,5,link.com,512-444-4444,123 Cove,78704"));
		assertEquals(one, test);
	}

	// embedded quotes
	@Test
	public void test2() {
		String[] out = { "Pname", "CID12345", "\"5\"", "link.com", "512-444-4444", "123 Cove", "78704" };
		String one = Arrays.toString(out);
		String test = Arrays
				.toString(CombineCSV.splitCsvLineToArray("Pname,CID12345,\"5\",link.com,512-444-4444,123 Cove,78704"));
		assertEquals(one, test);
	}
}
