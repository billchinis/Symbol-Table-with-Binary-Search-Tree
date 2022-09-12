public class ListNode {
	private Suspect suspect; // Suspect of node
	public ListNode nextNode; // Next node
	
	// Constructors
	public ListNode(Suspect suspect){
		this( suspect, null );
	}
	
	public ListNode( Suspect suspect, ListNode node ){
		this.suspect = suspect;
		nextNode = node;
	}
	// Get suspect of node
	public Object getSuspect()	{
		return suspect; // return Suspect in this node
	}
	
	// Get next node
	public ListNode getNext()	{
		return nextNode; // get next node
	}
}
