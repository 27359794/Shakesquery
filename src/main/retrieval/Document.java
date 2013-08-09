package retrieval;

import java.util.ArrayList;

public class Document {
	// The document name -- an easy identifier e.g. "Hamlet"
	public String name;
	
	// The document content
	public String text;
	
	// The terms in the document (in sorted order)
	protected ArrayList<String> terms;
	
	/**
	 * @param name    An easy identifier for the doc e.g. "Hamlet". This is only
	 * 				  for human readability.
	 * @param docText The actual content of the document.
	 */
	public Document(String name, String docText) {
		terms = new ArrayList<String>();
		this.name = name;
		text = docText;
		String[] tokens = docText.split("\\s");
		
		for (String token : tokens) {
			String cleanToken = token.toLowerCase().replaceAll("\\W", "");
			if (!cleanToken.isEmpty()) {
				terms.add(cleanToken);
			}
		}
	}
	
	public boolean containsTerm(String term) {
		return terms.contains(term);
	}
}
