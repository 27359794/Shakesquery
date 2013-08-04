package main.retrieval;

import java.util.ArrayList;

public class Document {
	public String text;
	protected ArrayList<String> terms;
	
	public Document(String docText) {
		terms = new ArrayList<String>();
		text = docText;
		String[] tokens = docText.split(" ");
		
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
