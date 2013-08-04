package main.retrieval;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;


public class BooleanModel {
	
	
	public ArrayList<Document> documents;
	
	private InvertedIndex index;
	
	
	
	public static void main(String[] args) throws Exception {
		BooleanModel model = new BooleanModel();
	}
	
	public BooleanModel() {
		documents = new ArrayList<Document>();
		index = new InvertedIndex();
		
	}
	
	public void addDocument(Document doc) {
		int docID = documents.size();
		documents.add(doc);
		
		index.indexDocument(doc, docID);
	}
	
	public ArrayList<Document> wordQuery(String query) { 
		PostingsList list = index.getPostingsListForTerm(query);
		return postingsListToDocs(list);
	}
	
	public ArrayList<Document> disjunctiveWordQuery(String[] query) {
		// TODO: A heuristic tells us that we should merge postings lists in
		// sorted order (ascending) to minimise processing time
//		TreeMap<Integer, String> sorted = new Tree<Integer, String>();
//		for (String word : query) {
//			sorted.put(index.getFrequency(word), word);
//		}
		
		PostingsList intermediate = new PostingsList("");
		for (String term : query) {
			PostingsList cur = index.getPostingsListForTerm(term);
			intermediate.mergeIn(cur);
		}
		return postingsListToDocs(intermediate);
	}
	
	protected ArrayList<Document> postingsListToDocs(PostingsList list) {
		ArrayList<Document> docs = new ArrayList<Document>();
		for (Integer id : list) {
			docs.add(documents.get(id));
		}
		return docs;
	}
}
