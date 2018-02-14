import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class InitialTestSuite {
	@Test
	public void testStringAssignment() {
		String str1 = "This is string to test with";
		String str2 = str1;
		assertEquals(str1, str2);
	}

	@Test
	public void getConfigurationTag() {
		String actualTag = enchatter.Configuration.TAG;
		String expectedTag = "Configuration";
		assertEquals(expectedTag, actualTag);
	}
}
