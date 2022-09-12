public class List {
	private ListNode firstNode; // First Node of list
	private ListNode lastNode; // Last Node of list
	
	private int size = 0; // Size of list
	
	// Constructor
	public List(){
		firstNode = lastNode = null;
	}
	
	// Add a suspect in list
	public void add(Suspect suspect){
		ListNode node = new ListNode(suspect);
		if(size == 0){
			firstNode = lastNode = node;
		}else{
			lastNode.nextNode = node;
			lastNode = node;
		}
		size++;
	}
	
	// Get size of list
	public int getSize(){
		return size;
	}
	
	// Clear list
	public void clear(){
		size = 0;
		firstNode = lastNode = null;
	}
	
	// Print suspects of list
	public void print(){
		if (size == 0){
			System.out.println( "Suspects not found!" + "\n" );
			return;
		}else{
			ListNode current = firstNode;
			
			while(current != null ){
				System.out.println(current.getSuspect());
				current = current.nextNode;
			}
		}
	}
}
