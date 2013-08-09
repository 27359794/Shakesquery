package query.utils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import query.utils.FileReading;

public class FileReadingTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldReadFile() throws IOException {
		// Read this source file
		String src = FileReading.readTextFile(
				new File("src/test/query/utils/FileReadingTest.java"));
		// So meta
		assertTrue(src.startsWith("package query.utils;"));
		assertTrue(src.contains("Quine?"));
		assertTrue(src.endsWith("}"));
	}

}
