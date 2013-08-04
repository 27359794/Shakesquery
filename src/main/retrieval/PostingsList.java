package main.retrieval;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class PostingsList implements Iterable<Integer> {
	
	public String term;
	protected TreeSet<Integer> postingsSet; 
	
	
	public PostingsList(String term) {
		this.term = term;
		postingsSet = new TreeSet<Integer>();
	}
	
	public ArrayList<Integer> getList() {
		return new ArrayList<>(postingsSet);
	}
	
	public void mergeIn(PostingsList other) {
		this.postingsSet.addAll(other.postingsSet);
		term += " + " + other.term;
	}
	
	public int size() {
		return postingsSet.size();
	}
	
	public boolean contains(int docID) {
		return postingsSet.contains(docID);
	}
	
	
	@Override
	public Iterator<Integer> iterator() {
		return postingsSet.iterator();
	}
	
	public void add(int docID) {
		postingsSet.add(docID);
	}
}
