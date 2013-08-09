package query.retrieval;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
			list.add(num);
		}
		return list;
	}
	
	protected PostingsList getSampleList() {
		return constructList(new Integer[] {100, 3, 15, 1, 200, 100});
	}
	
	protected void checkListValues(Integer[] expected, PostingsList pList) {
		Integer[] actual = new Integer[expected.length];
		pList.asArrayList().toArray(actual);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void shouldGetSize() {
		PostingsList list = getSampleList();
		
		list.add(100);
		assertEquals(5, list.size());
		list.add(1000);
		assertEquals(6, list.size());
	}
	
	@Test
	public void shouldGetContainsStatus() {
		PostingsList list = getSampleList();
		
		assertTrue(list.contains(1));
		assertTrue(list.contains(3));
		assertTrue(list.contains(15));
		assertTrue(list.contains(100));
		assertTrue(list.contains(200));
		
		assertFalse(list.contains(2));
		assertFalse(list.contains(20));
		assertFalse(list.contains(300));
	}
	
	@Test
	public void shouldGetPostingsListRepresentation() {
		PostingsList list = getSampleList();
		
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(1);
		expected.add(3);
		expected.add(15);
		expected.add(100);
		expected.add(200);
		
		assertTrue(list.asArrayList().equals(expected));		
	}

	@Test
	public void shouldUnionPostingsLists() {
		PostingsList list = getSampleList();
		
		PostingsList other = constructList(new Integer[]{20, 3, 100, 18,
														 300, 3000});
		list.unionIn(other);
		
		Integer[] expected = {1, 3, 15, 18, 20, 100, 200, 300, 3000}; 
		checkListValues(expected, list);

		// Make sure other wasn't changed in the union		
		Integer[] otherExpected = {3, 18, 20, 100, 300, 3000};
		checkListValues(otherExpected, other);
		
		// Test some more complex cases
		list = getSampleList();
		
		PostingsList other1 = constructList(new Integer[]{3, 0, 12, 200, 100});
		list.unionIn(other1);
		checkListValues(new Integer[]{0, 1, 3, 12, 15, 100, 200}, list);
		
		PostingsList other2 = constructList(new Integer[]{-1, 12, 15, 17});
		list.unionIn(other2);
		checkListValues(new Integer[]{-1, 0, 1, 3, 12, 15, 17, 100, 200}, list);
	}
	
	@Test
	public void shouldIntersectPostingsLists() {
		PostingsList list = getSampleList();
		
		PostingsList other = constructList(new Integer[]{20, 3, 100, 18,
														 300, 3000});
		list.intersectIn(other);
		
		checkListValues(new Integer[]{3, 100}, list);
		
		// Make sure other wasn't changed in the intersection
		Integer[] otherExpected = {3,18, 20, 100, 300, 3000};
		checkListValues(otherExpected, other);
		
		// Test some more complex cases
		list = getSampleList();
		
		PostingsList other1 = constructList(new Integer[]{3, 0, 12, 200, 100});
		list.intersectIn(other1);
		checkListValues(new Integer[]{3, 100, 200}, list);
		
		PostingsList other2 = constructList(new Integer[]{3, 200});
		list.intersectIn(other2);
		checkListValues(new Integer[]{3, 200}, list);
		
		PostingsList other3 = constructList(new Integer[]{200});
		list.intersectIn(other3);
		checkListValues(new Integer[]{200}, list);
		
		PostingsList other4 = constructList(new Integer[]{});
		list.intersectIn(other4);
		checkListValues(new Integer[]{}, list);
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
		checkListValues(expected, list);
		assertTrue(other.size() == 0);
	}
	
	@Test
	public void shouldConvertToArrayList() {
		PostingsList list = getSampleList();
		
		list.add(100);
		list.add(15);
		list.add(200);
		list.add(1);
		list.add(3);
		list.add(100);
		
		Integer[] expected = {1, 3, 15, 100, 200};
		checkListValues(expected, list);
	}

}
