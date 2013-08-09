package retrieval;

import java.util.ArrayList;

import datastructures.list.Node;
import datastructures.list.SinglyLinkedList;

public class PostingsList {
	
	public String term;
	protected SinglyLinkedList<Integer> docIDs;
	
	
	public PostingsList(String term) {
		this.term = term;
		docIDs = new SinglyLinkedList<Integer>();
	}
	
	/**
	 * @return document IDs, in sorted order.
	 */
	public SinglyLinkedList<Integer> getList() {
		return docIDs;
	}
	
	/**
	 * In-place merges other into this list. The final list is sorted and all
	 * elements are unique.
	 * 
	 * @param other The other postings list to merge into this one (disjunctive).
	 * 				Pre-condition: its postingsList be in sorted order.
	 */
	public void unionIn(PostingsList other) {
		// Don't do anything if other has no elements
		if (other.size() == 0) {
			return;
		}
		Node<Integer> meCur = this.getHeadNode(), meNext = meCur.next;
		Node<Integer> them = other.getHeadNode().next;
		while (them != null) {
			if (meCur != this.getHeadNode() && 
				them.value.compareTo(meCur.value) == 0) {
				// If them == meCur, advance it
				them = them.next;
			} else if ((meCur == this.getHeadNode() || 
					    meCur.value.compareTo(them.value) < 0) &&
					   (meNext == null ||
					    meNext.value.compareTo(them.value) > 0)) {
				// If (them is at the start or them >= meCur) and (them < meNext
				// or there is no meNext), then insert here
				Node<Integer> newNode = new Node<Integer>();
				newNode.value = them.value;
				meCur.next = newNode;
				newNode.next = meNext;
				
				// Advance all pointers forward by one
				them = them.next;
				meCur = newNode;
				meNext = meCur.next;
				
				docIDs.size ++;
			} else {
				// Advance me pointers, them stays the same
				meCur = meNext;
				meNext = meNext.next;
			}	
		}
	}
	
	/**
	 * In-place merges other into this list conjunctively, so that by the end,
	 * this list is the intersection of itself and other.
	 */
	public void intersectIn(PostingsList other) {
		Node<Integer> meCur = this.getHeadNode(), meNext = meCur.next;
		Node<Integer> them = other.getHeadNode().next;
		
		// Note that we can terminate when *either* pointer reaches the end
		while (them != null && meNext != null) {
			if (meNext.value.equals(them.value)) {
				// Keep meNext in the list, advance everything
				meCur = meNext;
				meNext = meCur.next;
				them = them.next;
			} else if (them.value.compareTo(meNext.value) > 0) {
				// meNext pointer is behind, remove it and shift forward
				meCur.next = meNext.next;
				meCur = meNext;
				meNext = meCur.next;
				docIDs.size --;
			} else {
				// them pointer is behind, DON'T remove it but shift it forward
				them = them.next;
			}
		}
		
		if (them == null) {
			// Ignore every element after meCur, make meCur the new tail
			while (meCur != null && meCur.next != null) {
				meCur.next = meCur.next.next;
				docIDs.size --;
			}
		}

	}
	
	public int size() {
		return docIDs.size;
	}
	
	public ArrayList<Integer> asArrayList() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Node<Integer> cur = docIDs.head.next;
		while (cur != null) {
			result.add(cur.value);
			cur = cur.next;
		}
		return result;
	}
	 
	public Node<Integer> getHeadNode() {
		return docIDs.head;
	}
	
	public boolean contains(int docID) {
		return docIDs.contains(docID);
	}
	
	/**
	 * Add a doc to the postings list, by its ID. Duplicates are ignored.
	 */
	public void add(int docID) {
		if (!contains(docID)) {
			docIDs.insertSorted(docID);
		}
	}
}
