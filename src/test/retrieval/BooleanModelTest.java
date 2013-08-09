package retrieval;

import static org.junit.Assert.*;

import java.util.ArrayList;

import retrieval.BooleanModel;
import retrieval.Document;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BooleanModelTest {

	protected Document doc1, doc2, doc3;
	
	@Before
	public void setUp() throws Exception {
		doc1 = new Document("bla", "to be or not to be");
		doc2 = new Document("bla2", "i went to the market");
		doc3 = new Document("bla3", "i think therefore i be");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	protected BooleanModel getSampleModel() {
		ArrayList<Document> docs = new ArrayList<Document>();
		docs.add(doc1);
		docs.add(doc2);
		docs.add(doc3);
		
		BooleanModel model = new BooleanModel(docs);
		return model;
	}

	@Test
	public void shouldQueryIndividualWords() {
		BooleanModel model = getSampleModel();
		ArrayList<Document> results;
		
		results = model.wordQuery("to");
		assertEquals(2, results.size());
		assertTrue(results.contains(doc1));
		assertTrue(results.contains(doc2));
		
		results = model.wordQuery("i");
		assertEquals(2, results.size());
		assertTrue(results.contains(doc2));
		assertTrue(results.contains(doc3));
		
		results = model.wordQuery("be");
		assertEquals(2, results.size());
		assertTrue(results.contains(doc1));
		assertTrue(results.contains(doc3));
		
		results = model.wordQuery("therefore");
		assertEquals(1, results.size());
		assertTrue(results.contains(doc3));
		
		results = model.wordQuery("cat");
		assertEquals(0, results.size());	
	}
	
	@Test
	public void shouldPerformDisjunctiveQueries() {
		BooleanModel model = getSampleModel();
		ArrayList<Document> results;
		
		results = model.disjunctiveWordQuery(new String[] {"to"});
		assertEquals(2, results.size());
		assertTrue(results.contains(doc1));
		assertTrue(results.contains(doc2));
		
		results = model.disjunctiveWordQuery(new String[] {"i"});
		assertEquals(2, results.size());
		assertTrue(results.contains(doc2));
		assertTrue(results.contains(doc3));
		
		results = model.disjunctiveWordQuery(new String[] {"i", "to"});
		assertEquals(3, results.size());
		assertTrue(results.contains(doc1));
		assertTrue(results.contains(doc2));
		assertTrue(results.contains(doc3));
		
		results = model.disjunctiveWordQuery(new String[] {"went", "think"});
		assertEquals(2, results.size());
		assertTrue(results.contains(doc2));
		assertTrue(results.contains(doc3));
		
		results = model.disjunctiveWordQuery(new String[] {"therefore", "was"});
		assertEquals(1, results.size());
		assertTrue(results.contains(doc3));
		
		results = model.disjunctiveWordQuery(new String[] {"went", "market",
														   "therefore"});
		assertEquals(2, results.size());
		assertTrue(results.contains(doc2));
		assertTrue(results.contains(doc3));
		
		results = model.disjunctiveWordQuery(new String[] {"cat", "dog"});
		assertEquals(0,  results.size());
	}
	
	@Test
	public void shouldPerformConjunctiveQueries() {
		BooleanModel model = getSampleModel();
		ArrayList<Document> results;
		
		results = model.conjunctiveWordQuery(new String[] {"to"});
		assertEquals(2, results.size());
		assertTrue(results.contains(doc1));
		assertTrue(results.contains(doc2));
		
		results = model.conjunctiveWordQuery(new String[] {"i"});
		assertEquals(2, results.size());
		assertTrue(results.contains(doc2));
		assertTrue(results.contains(doc3));
		
		results = model.conjunctiveWordQuery(new String[] {"i", "to"});
		assertEquals(1, results.size());
		assertTrue(results.contains(doc2));
		
		results = model.conjunctiveWordQuery(new String[] {"went", "think"});
		assertEquals(0, results.size());
		
		results = model.conjunctiveWordQuery(new String[] {"therefore", "was"});
		assertEquals(0, results.size());
		
		results = model.conjunctiveWordQuery(new String[] {"went", "market",
														   "therefore"});
		assertEquals(0, results.size());
		
		results = model.conjunctiveWordQuery(new String[] {"cat", "dog"});
		assertEquals(0,  results.size());
	}

}
