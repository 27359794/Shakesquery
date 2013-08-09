package query.retrieval;

import static org.junit.Assert.*;
import query.retrieval.Document;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DocumentTest {
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	protected Document getSampleDoc() {
		return new Document("My Name!",
				"The quick brown Fox! 'Jumped' over O'Reilly... ...");
	}
	
	protected void checkDocumentTerms(String[] terms, Document doc) {
		String[] docTerms = doc.terms.toArray(new String[0]);
		assertArrayEquals(terms, docTerms);
	}
	
	@Test
	public void shouldKnowName() {
		Document doc = getSampleDoc();
		assertEquals("My Name!", doc.name);
	}

	@Test
	public void shouldContainTerm() {
		Document doc = getSampleDoc();
		String terms[] = {"the", "quick", "brown", "fox",
						  "jumped", "over", "oreilly"};
	
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
	
	@Test
	public void shouldSplitTokensOnAllWhitespace() {
		Document doc = new Document("Hamlet", 
				"The king\nHamlet	Sat upon  his \n	throne");
		String terms[] = {"the", "king", "hamlet", "sat",
						  "upon", "his", "throne"};
		checkDocumentTerms(terms, doc);
		
	}

}
