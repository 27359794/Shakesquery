package main.retrieval;
import java.util.ArrayList;


public class BooleanModel {
	
	
	public ArrayList<Document> documents;
	
	private InvertedIndex index;
	
	
	
	public static void main(String[] args) throws Exception {
//		BooleanModel model = new BooleanModel();
	}
	
	public BooleanModel(ArrayList<Document> docsForModel) {
		index = new InvertedIndex();
		documents = new ArrayList<Document>();
		
		for (Document d : docsForModel) {
			this.addDocument(d);
		}
		
		index.optimise();
	}
	
	protected void addDocument(Document doc) {
		int docID = documents.size();
		documents.add(doc);
		
		index.indexDocument(doc, docID);
	}
	
	public ArrayList<Document> wordQuery(String query) { 
		PostingsList list = index.getPostingsListForTerm(query);
		return postingsListToDocs(list);
	}
	
	public ArrayList<Document> disjunctiveWordQuery(String[] query) {
		PostingsList intermediate = new PostingsList("");
		for (String term : query) {
			PostingsList cur = index.getPostingsListForTerm(term);
			intermediate.unionIn(cur);
		}
		return postingsListToDocs(intermediate);
	}
	
	public ArrayList<Document> conjunctiveWordQuery(String[] query) {
		// TODO: merge using heuristic -- frequency sort
		
		// Note that we can't start with an empty intermediate, since it would
		// never grow after intersections.
		PostingsList intermediate = index.getPostingsListForTerm(query[0]);
		for (int i=1 ; i<query.length ; i++) {
			PostingsList cur = index.getPostingsListForTerm(query[i]);
			intermediate.intersectIn(cur);
		}
		return postingsListToDocs(intermediate);
	}
	
	protected ArrayList<Document> postingsListToDocs(PostingsList list) {
		ArrayList<Document> docs = new ArrayList<Document>();
		for (Integer id : list.asArrayList()) {
			docs.add(documents.get(id));
		}
		return docs;
	}
}
