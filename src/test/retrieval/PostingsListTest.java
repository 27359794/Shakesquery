package test.retrieval;

import static org.junit.Assert.*;

import java.util.ArrayList;

import main.retrieval.PostingsList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PostingsListTest {
	
	protected PostingsList list;

	@Before
	public void setUp() throws Exception {
		list = new PostingsList("foo");
		
		list.add(100);
		list.add(3);
		list.add(15);
		list.add(1);
		list.add(200);
		list.add(100);
	}

	@After
	public void tearDown() throws Exception {
		list = null;
	}
	
	@Test
	public void shouldGetSize() {
		list.add(100);
		assertEquals(5, list.size());
		list.add(1000);
		assertEquals(6, list.size());
	}
	
	@Test
	public void shouldGetContainsStatus() {
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
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(1);
		expected.add(3);
		expected.add(15);
		expected.add(100);
		expected.add(200);
		
		assertTrue(list.asArrayList().equals(expected));		
	}

	@Test
	public void shouldMergePostingsLists() {
		PostingsList other = new PostingsList("bar");
		other.add(20);
		other.add(3);
		other.add(100);
		other.add(18);
		other.add(300);
		other.add(3000);
		
		assertTrue(other.contains(300));
		assertTrue(other.contains(3000));
		
		list.mergeIn(other);
		assertEquals(9, list.size());
		
		// Make sure other wasn't changed in the union
		assertEquals(6, other.size());
	}
	
	@Test
	public void shouldConvertToArrayList() {
		
		list.add(100);
		list.add(15);
		list.add(200);
		list.add(1);
		list.add(3);
		list.add(100);
		
		Integer[] expected = {1, 3, 15, 100, 200};
		Integer[] actual = new Integer[expected.length];
		
		assertArrayEquals(expected, list.asArrayList().toArray(actual));
	}

}
