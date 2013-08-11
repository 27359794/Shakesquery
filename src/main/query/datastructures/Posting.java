package query.datastructures;

public class Posting implements Comparable<Posting> {
	public int docID;

	// pos is the index of the term in the doc 
	public int pos;
	
	public Posting(int docID, int pos) {
		this.docID = docID;
		this.pos = pos;
	}
	
	@Override
	public int compareTo(Posting other) {
		if (this.docID == other.docID) {
			return 0;
		} else {
			return this.docID - other.docID;
		}
	}
	
	public boolean equals(Posting other) {
		return (this.docID == other.docID);
	}
}
