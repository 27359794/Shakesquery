package retrieval;

import static org.junit.Assert.*;
import retrieval.Document;
import retrieval.InvertedIndex;
import retrieval.PostingsList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InvertedIndexTest {

	protected InvertedIndex index;
	protected Document doc1, doc2, doc3;
	
	@Before
	public void setUp() throws Exception {
		index = new InvertedIndex();
		
		doc1 = new Document("bla", "to be or not to be");
		doc2 = new Document("bla2", "i went to the market");
		doc3 = new Document("bla3", "i think therefore i be");
		
		index.indexDocument(doc1, 1337);
		index.indexDocument(doc2, 96);
		index.indexDocument(doc3, 42);
	}

	@After
	public void tearDown() throws Exception {
		index = null;
		doc1 = doc2 = doc3 = null;
	}

	@Test
	public void shouldGetPostingsListForTerm() {
		PostingsList results;
		results = index.getPostingsListForTerm("to");
		assertEquals(2, results.size());
		assertTrue(results.contains(1337));
		assertTrue(results.contains(96));
	}
	
	@Test
	public void shouldGetDocumentFrequencyOfTerm() {
		assertEquals(2, index.getFrequency("to"));
		assertEquals(2, index.getFrequency("i"));
		assertEquals(1, index.getFrequency("went"));
		assertEquals(0, index.getFrequency("cat"));
	}

}
