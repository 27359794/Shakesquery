package query.datastructures.list;

/**
 * A simple singly-linked list.
 * 
 * @author Daniel Goldbach
 * 
 */
public class SinglyLinkedList<V extends Comparable<V>> {
	public Node<V> head;
	public int size;

	public SinglyLinkedList() {
		head = new Node<V>();
		size = 0;
	}

	/**
	 * NOTE: this is the only function that modifies size.
	 * 
	 * @param before
	 *            The node whose next node will become toInsert. This node
	 *            should be in the list -- but this is not enforced!
	 */
	public void insertAfter(Node<V> before, V valueToInsert) {
		Node<V> toInsert = new Node<V>();
		toInsert.value = valueToInsert;
		toInsert.next = before.next;
		before.next = toInsert;

		size++;
	}

	/**
	 * Inserts a new node before the first node in the list whose value is
	 * greater than the new val, if no such node exists. Behaviour is undefined
	 * if the list is not sorted.
	 */
	public void insertSorted(V newVal) {
		Node<V> cur = head;
		while (cur.next != null && cur.next.value.compareTo(newVal) <= 0) {
			cur = cur.next;
		}
		insertAfter(cur, newVal);
	}

	/**
	 * Checks if any nodes in the list have value val.
	 */
	public boolean contains(V val) {
		if (val == null) {
			return false;
		}

		Node<V> cur = head.next;
		while (cur != null) {
			if (val.equals(cur.value)) {
				return true;
			}
			cur = cur.next;
		}
		return false;
	}

}
