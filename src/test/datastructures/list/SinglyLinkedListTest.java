package datastructures.list;

import static org.junit.Assert.*;
import datastructures.list.Node;
import datastructures.list.SinglyLinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SinglyLinkedListTest {

	public SinglyLinkedList<Integer> list;

	@Before
	public void setUp() throws Exception {
		list = new SinglyLinkedList<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		list = null;
	}

	@Test
	public void shouldInsertAfterNode() {
		list.insertAfter(list.head, 4);
		list.insertAfter(list.head, 6);
		list.insertAfter(list.head, 8);
		// List is now HEAD->8->6->4->null
		assertEquals(8, list.head.next.value.intValue());
		assertEquals(6, list.head.next.next.value.intValue());
		assertEquals(4, list.head.next.next.next.value.intValue());
		assertEquals(null, list.head.next.next.next.next);

		list.insertAfter(list.head.next.next, 7);
		list.insertAfter(list.head.next.next.next, 5);
		// List is now HEAD->8->6->7->5->4->null
		assertEquals(8, list.head.next.value.intValue());
		assertEquals(6, list.head.next.next.value.intValue());
		assertEquals(7, list.head.next.next.next.value.intValue());
		assertEquals(5, list.head.next.next.next.next.value.intValue());
		assertEquals(4, list.head.next.next.next.next.next.value.intValue());
		assertEquals(null, list.head.next.next.next.next.next.next);

		list.insertAfter(list.head.next.next.next.next.next, 3);
		// List is now HEAD->8->6->7->5->4->3->null
		assertEquals(8, list.head.next.value.intValue());
		assertEquals(6, list.head.next.next.value.intValue());
		assertEquals(7, list.head.next.next.next.value.intValue());
		assertEquals(5, list.head.next.next.next.next.value.intValue());
		assertEquals(4, list.head.next.next.next.next.next.value.intValue());
		assertEquals(3,
				list.head.next.next.next.next.next.next.value.intValue());
		assertEquals(null, list.head.next.next.next.next.next.next.next);
	}

	@Test(expected = Exception.class)
	public void shouldFailOnInsertAfterNullNode() {
		Node<Integer> nullNode = null;
		list.insertAfter(nullNode, 10);
	}

	@Test
	public void shouldInsertInSortedOrder() {
		list.insertSorted(10);
		list.insertSorted(3);
		list.insertSorted(6);
		list.insertSorted(-1);
		list.insertSorted(15);
		list.insertSorted(6);
		// List is now HEAD->-1->3->6->7->10->15->null
		assertEquals(-1, list.head.next.value.intValue());
		assertEquals(3, list.head.next.next.value.intValue());
		assertEquals(6, list.head.next.next.next.value.intValue());
		assertEquals(6, list.head.next.next.next.next.value.intValue());
		assertEquals(10, list.head.next.next.next.next.next.value.intValue());
		assertEquals(15,
				list.head.next.next.next.next.next.next.value.intValue());
		assertEquals(null, list.head.next.next.next.next.next.next.next);
	}

	@Test
	public void shouldGetSize() {
		assertEquals(0, list.size);
		list.insertAfter(list.head, 2);
		assertEquals(1, list.size);
		list.insertAfter(list.head, 3);
		assertEquals(2, list.size);
		list.insertSorted(10);
		assertEquals(3, list.size);
		list.insertSorted(-1);
		assertEquals(4, list.size);
	}

	@Test
	public void shouldGetContainsStatus() {
		list.insertSorted(10);
		list.insertSorted(3);
		list.insertSorted(6);
		list.insertSorted(-1);

		assertTrue(list.contains(10));
		assertTrue(list.contains(3));
		assertTrue(list.contains(6));
		assertTrue(list.contains(-1));

		assertFalse(list.contains(1));
		assertFalse(list.contains(0));
		assertFalse(list.contains(7));
		assertFalse(list.contains(5));
		assertFalse(list.contains(null));
	}

}
