package test.retrieval;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.TestCase;
import main.retrieval.BooleanModel;
import main.retrieval.Document;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BooleanModelTest {

	protected Document doc1, doc2, doc3;
	
	protected BooleanModel model;
	
	@Before
	public void setUp() throws Exception {
		model = new BooleanModel();
		
		doc1 = new Document("to be or not to be");
		doc2 = new Document("i went to the market");
		doc3 = new Document("i think therefore i be");
		
		model.addDocument(doc1);
		model.addDocument(doc2);
		model.addDocument(doc3);
	}

	@After
	public void tearDown() throws Exception {
		model = null;
		doc1 = doc2 = doc3 = null;
	}

	@Test
	public void testWordQuery() {
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
	public void testDisjunctiveWordQuery() {
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
		
		results = model.disjunctiveWordQuery(new String[] {"cat", "dog"});
		assertEquals(0,  results.size());

	}

}
