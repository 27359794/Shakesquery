package main.retrieval;

import java.util.HashMap;
import java.util.HashSet;

public class InvertedIndex {
	
	protected HashMap<String, PostingsList> invIndex;

	// These are *document* frequencies. Repetition of a term in a document is
	// ignored.
	protected HashMap<String, Integer> frequencies; 
	
	public InvertedIndex() {
		invIndex = new HashMap<String, PostingsList>();
		frequencies = new HashMap<String, Integer>();
	}
	
	/**
	 * Add a document to the index.
	 * @param doc The document to add.
	 * @param docID The document's ID in the model. 
	 */
	public void indexDocument(Document doc, int docID) {
		HashSet<String> seenWords = new HashSet<String>();
		
		for (String term : doc.terms) {
			if (!invIndex.containsKey(term)) {
				invIndex.put(term, new PostingsList(term));
			}
			invIndex.get(term).add(docID);
			
			// Only count each term once per document for frequencies.
			if (!seenWords.contains(term)) {
				if (!frequencies.containsKey(term)) {
					frequencies.put(term, 1);
				} else {
					frequencies.put(term, frequencies.get(term) + 1);
				}
				seenWords.add(term);
			}
		}	
	}
	
	/**
	 * Get the postings list for some term in the inv. index.
	 * @param term
	 * @return 
	 */
	public PostingsList getPostingsListForTerm(String term) {
		if (invIndex.containsKey(term)) {
			return invIndex.get(term);
		} else {
			return new PostingsList(term);
		}
	}
	
	public int getFrequency(String term) {
		return frequencies.containsKey(term) ? frequencies.get(term) : 0;
	}
}
