package test.retrieval;

import static org.junit.Assert.*;
import main.retrieval.Document;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DocumentTest {

	protected Document doc;
	
	@Before
	public void setUp() throws Exception {
		doc = new Document("The quick brown Fox! 'Jumped' over O'Reilly... ...");
	}

	@After
	public void tearDown() throws Exception {
		doc = null;
	}

	@Test
	public void shouldGetDocumentContainsTerm() {
		String terms[] = {"the", "quick", "brown", "fox", "jumped", "over", "oreilly"};
		for (String term : terms) {
			assertTrue(doc.containsTerm(term));
		}
		
		assertFalse(doc.containsTerm("cat"));
		assertFalse(doc.containsTerm(""));
		assertFalse(doc.containsTerm("..."));
		assertFalse(doc.containsTerm("The"));
		assertFalse(doc.containsTerm("Fox!"));
		assertFalse(doc.containsTerm("'Jumped'"));
		assertFalse(doc.containsTerm("Jumped"));
		assertFalse(doc.containsTerm("o'reilly"));
	}

}
