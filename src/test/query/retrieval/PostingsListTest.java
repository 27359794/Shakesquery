package query.retrieval;

import static org.junit.Assert.*;

import java.util.ArrayList;

import query.datastructures.Posting;
import query.retrieval.PostingsList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PostingsListTest {
	
	protected PostingsList list;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	protected PostingsList constructList(Integer[] array) {
		list = new PostingsList("my list");
		for (Integer num : array) {
			list.add(num, 0);
		}
		return list;
	}
	
	protected PostingsList getSampleList() {
		return constructList(new Integer[] {100, 3, 15, 1, 200, 100});
	}
	
	protected void checkListDocIDs(Integer[] expected, PostingsList pList) {
		assertEquals(expected.length, pList.size());
		for (Integer n : expected) {
			assertTrue(pList.containsDoc(n));
		}
	}
	
	@Test
	public void shouldGetSize() {
		PostingsList list = getSampleList();
		
		list.add(100, 0);
		assertEquals(5, list.size());
		list.add(1000, 0);
		assertEquals(6, list.size());
	}
	
	@Test
	public void shouldGetContainsStatus() {
		PostingsList list = getSampleList();
		
		assertTrue(list.containsDoc(1));
		assertTrue(list.containsDoc(3));
		assertTrue(list.containsDoc(15));
		assertTrue(list.containsDoc(100));
		assertTrue(list.containsDoc(200));
		
		assertFalse(list.containsDoc(2));
		assertFalse(list.containsDoc(20));
		assertFalse(list.containsDoc(300));
	}
	
	@Test
	public void shouldGetPostingsListRepresentation() {
		PostingsList list = getSampleList();
		ArrayList<Posting> actual = list.asArrayList();
		
		ArrayList<Posting> expected = new ArrayList<Posting>();
		expected.add(new Posting(1, 0));
		expected.add(new Posting(3, 0));
		expected.add(new Posting(15, 0));
		expected.add(new Posting(100, 0));
		expected.add(new Posting(200, 0));
		
		assertEquals(expected.size(), actual.size());
		for (int i=0 ; i<expected.size() ; i++) {
			assertEquals(expected.get(i).docID, actual.get(i).docID);
		}
	}

	@Test
	public void shouldUnionPostingsLists() {
		PostingsList list = getSampleList();
		
		PostingsList other = constructList(new Integer[]{20, 3, 100, 18,
														 300, 3000});
		list.unionIn(other);
		
		Integer[] expected = {1, 3, 15, 18, 20, 100, 200, 300, 3000}; 
		checkListDocIDs(expected, list);

		// Make sure other wasn't changed in the union		
		Integer[] otherExpected = {3, 18, 20, 100, 300, 3000};
		checkListDocIDs(otherExpected, other);
		
		// Test some more complex cases
		list = getSampleList();
		
		PostingsList other1 = constructList(new Integer[]{3, 0, 12, 200, 100});
		list.unionIn(other1);
		checkListDocIDs(new Integer[]{0, 1, 3, 12, 15, 100, 200}, list);
		
		PostingsList other2 = constructList(new Integer[]{-1, 12, 15, 17});
		list.unionIn(other2);
		checkListDocIDs(new Integer[]{-1, 0, 1, 3, 12, 15, 17, 100, 200}, list);
	}
	
	@Test
	public void shouldIntersectPostingsLists() {
		PostingsList list = getSampleList();
		
		PostingsList other = constructList(new Integer[]{20, 3, 100, 18,
														 300, 3000});
		list.intersectIn(other);
		
		checkListDocIDs(new Integer[]{3, 100}, list);
		
		// Make sure other wasn't changed in the intersection
		Integer[] otherExpected = {3,18, 20, 100, 300, 3000};
		checkListDocIDs(otherExpected, other);
		
		// Test some more complex cases
		list = getSampleList();
		
		PostingsList other1 = constructList(new Integer[]{3, 0, 12, 200, 100});
		list.intersectIn(other1);
		checkListDocIDs(new Integer[]{3, 100, 200}, list);
		
		PostingsList other2 = constructList(new Integer[]{3, 200});
		list.intersectIn(other2);
		checkListDocIDs(new Integer[]{3, 200}, list);
		
		PostingsList other3 = constructList(new Integer[]{200});
		list.intersectIn(other3);
		checkListDocIDs(new Integer[]{200}, list);
		
		PostingsList other4 = constructList(new Integer[]{});
		list.intersectIn(other4);
		checkListDocIDs(new Integer[]{}, list);
	}
		
	@Test
	public void shouldIntersectWithEmptyList() {
		PostingsList list = getSampleList();
		
		PostingsList other = new PostingsList("i am empty");
		list.intersectIn(other);
		assertEquals(0, list.size());
		assertEquals(0, other.size());
	}
	
	@Test
	public void shouldUnionWithEmptyList() {
		PostingsList list = getSampleList();
		
		PostingsList other = new PostingsList("i am empty");
		list.unionIn(other);
		Integer[] expected = {1, 3, 15, 100, 200};
		checkListDocIDs(expected, list);
		assertTrue(other.size() == 0);
	}
	
	@Test
	public void shouldConvertToArrayList() {
		PostingsList list = getSampleList();
		
		list.add(100, 0);
		list.add(15, 0);
		list.add(200, 0);
		list.add(1, 0);
		list.add(3, 0);
		list.add(100, 0);
		
		Integer[] expected = {1, 3, 15, 100, 200};
		checkListDocIDs(expected, list);
	}

}
