import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class ST {
	
	// TreeNode class
	private class TreeNode {
		Suspect item; // Suspect of node
		TreeNode p; // Parent of node
		TreeNode l; // Left of node
		TreeNode r; // Right of node
		int N; // Number of nodes on subtree of node
		
		// Constructor
		TreeNode(Suspect item, TreeNode p, TreeNode l, TreeNode r, int N){
			this.item = item;
			this.p = p;
			this.l = l;
			this.r = r;
			this.N = N;
		}
	};
	private List list = new List(); // List for searchByLastname method
	private Suspect[] suspectsArray; // Array of Suspects for printTopSuspects method
	int counter = 0; // Counter for the suspectsArray
	
	private int nodes = 0; // Number of nodes on the tree
	private double savings; // Double for getMeanSavings method
	

	private TreeNode head; // Head of the tree
	
	// Constructor
	public ST(){
		head = null;
		nodes = 0;
	}
	
	// Search tree to find suspect by AFM
	public Suspect searchByAFM(int AFM) {
        TreeNode h = head;
        while (h != null) {
            if (AFM == h.item.key()) { // if suspect found
                return h.item; // return suspect item
            }
            else if(AFM < h.item.key()){ // else if AFM < afm of current node
            	h = h.l; // h node equals left of h
            }
            else{ // else
            	h = h.r; // h node equals right of h
            }
        }
        return null; // if suspect not found return null
    }
	
	// Search tree to find suspect by Last Name
	private void searchByLastname(TreeNode node, String last_name){
		if(node.l != null) searchByLastname(node.l, last_name); // if left of node is available call searchByLastname with left of node
	    if(node.item.getLastName().equals(last_name)){ // if last name equals last name of node
	    	list.add(node.item); // add suspect to list
	    }
	    if(node.r != null) searchByLastname(node.r, last_name);	// if right of node is available call searchByLastname with right of node
    }    
	public List searchByLastname(String last_name){
		list.clear(); // clear list
		if(head != null){ // if tree is not empty
			searchByLastname(head, last_name); // call searchByLastname with head
		}
		return list; // return list
	}
    
	// Insert a suspect in tree normally
	public void insert(Suspect item){  
    	nodes++; // increase nodes by 1
    	
    	if(head == null){ // if tree is empty
    		head = new TreeNode(item, null, null, null, 1); // change head
    	} // else
    	else insertRec(item, head); // call insertRec with head
    }
	private void insertRec(Suspect item, TreeNode n){
		n.N++; // increase N of node by 1
    	if(item.key() < n.item.key()) { // if afm of item is less than afm of node
    		if (n.l == null){ // if left of node is not available
    			n.l = new TreeNode(item, n, null, null, 1); // left of node equals new node with item
    		} // else
    		else insertRec(item, n.l); // call insertRec with left of node
		}
    	else if(n.r == null){ // if right of node is not available 
    		n.r=new TreeNode(item, n, null, null, 1); // right of node equals new node with item
    	}
    	else insertRec(item, n.r); // else call insertRec with right of node
	}
	
	public void insert_at_root(Suspect item){
		nodes++;
		head = insert_at_root(head, null, item);//insert as leaf and rotate until it becomes the root
		//starts form top, reaches leaf, recursively rotates subtrees
		
	}
	private TreeNode insert_at_root(TreeNode n, TreeNode parent, Suspect item) {
		if (n == null) {
			n = new TreeNode(item,parent,null,null,1);
			return n;
		}
		if (item.key()<n.item.key()) {//smaller value goes to left subtree
			n.l= insert_at_root(n.l,n,item); //insert at root of left subtree
			n = rotateRight(n); 
		} //rotate right so that n.l becomes root of subtree
		else{//bigger value goes to right subtree
			n.r= insert_at_root(n.r,n,item); //insert at root of right subtree
			n = rotateLeft(n); //rotate left so that n.r becomes root of subtree
		}
		return n; 
	}
	
	private TreeNode rotateLeft(TreeNode n){
		TreeNode parent = n.p;
		TreeNode child= n.r;
		
		//fix N of child and n 
		n.N=1;
		if(n.l!=null)n.N+=n.l.N;
		if (child.l!=null) n.N+=child.l.N;//n gets n.l and child.l as children(if n.l or child.l existed)
		child.N=1+n.N;
		if(child.r!=null) child.N+=child.r.N;//child gets n and child.r as children(if child.r existed);
		//now continue with the rotation	
		
		if(parent==null){//no parent means rotation is at tree's first layer
			head=child;//that's where head is
		}
		else if(parent.l==n){//if n was left child
			parent.l=child;//n.r becomes left child of parent (value is smaller than parent)
		}
		else{//else n was right child
			parent.r=child;//n.r becomes right child of parent (value is bigger than parent)
		}
		child.p=n.p; //child get's connected to n's parent
		n.p=child; //n's new parent is child
		n.r=child.l; //hang's left subtree of child at n's right child position
		if(child.l!=null) child.l.p=n;//connects left subtree of child (if it exists) as right child of parent n
		child.l=n;//n goes to the left child position of it's initial position (rotated as we wanted)
		return child;
	}
	
	private TreeNode rotateRight(TreeNode n){
		TreeNode parent = n.p;
		TreeNode child= n.l;
		
		//fix N of child and n
		n.N=1;
		if(n.r!=null)n.N+=n.r.N;
		if (child.r!=null) n.N+=child.r.N;//n gets n.r and child.r as children(if n.r or child.r existed)
		child.N=1+n.N;
		if(child.l!=null) child.N+=child.l.N;//child gets n and child.l as children(if child.l existed);
		//now continue with the rotation
		
		if(parent==null){//no parent means rotation is at tree's first layer
			head=child;//that's where head is
		}
		else if(parent.l==n){//if n was left child
			parent.l=child;//n.r becomes left child of parent (value is smaller than parent)
		}
		else{//else n was right child
			parent.r=child;//n.r becomes right child of parent (value is bigger than parent)
		}
		child.p=n.p; //child get's connected to n's parent
		n.p=child; //n's new parent is child
		n.l=child.r; //hang's right subtree of child at n's left child position
		if(child.r!=null) child.r.p=n;//connects right subtree of child (if it exists) as left child of parent n
		child.r=n;//n goes to the right child position of it's initial position (rotated as we wanted)
		return child;
	}
	
	public void remove(int AFM){
		if(head == null){ // if tree is empty
			System.out.println("Tree is empty. Remove failed!");
		}
		else if(searchByAFM(AFM) == null){ // if given afm is not in tree
			System.out.println("AFM not in tree. Remove failed!");
		}
		else{ // if afm is in tree
			remove(head,AFM);
			nodes--;
			System.out.println("Remove Succesful!");
		}
	}
	
	private void remove(TreeNode n, int AFM) {
		n.N--;
		if(AFM < n.item.key()) remove(n.l,AFM);
		else if (AFM > n.item.key()) remove(n.r,AFM);
		else {
			TreeNode parent=n.p;
			 TreeNode child = n.l != null ? n.l : n.r;
			// Case 1:  No child
			if(n.l== null && n.r == null) { 
				if(n==head){
					head=null;
					return;
				}
				if(n==parent.l) parent.l=null;
				else parent.r=null;
			}
			//Case 2: One child 
			else if(n.l == null||n.r==null) {
				if(n==head){
					head=child;
					return;
				}
				if(n==parent.l)parent.l=child;
				else parent.r=child;
				child.p=parent;
			}
			// case 3: 2 children
			else { 
				TreeNode temp = findMin(n.r);//find minimum of right subtree to swap with n
				n.item = temp.item;//swaps n's item with a duplicate from minimum of right subtree
				remove(n.r,temp.item.key());//deletes duplicate from right subtree
			}
		}
	}
	
	private TreeNode findMin(TreeNode n){
		while(n.l != null) n = n.l;
		return n;
	}
	
	// Load suspects from a txt file
	public void load(String filename){
		File f = null;
		BufferedReader reader = null;
		
		String line;
		
		try{ //creates a new File
			f = new File(filename);
		} catch (NullPointerException e) {
			System.err.println("File not found");
		}
		
		try{ //creates a new BufferedReader
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		} catch (FileNotFoundException e) {
			System.err.println("Error opening file!");
		}
		
		try{
			line = reader.readLine();
		
			int AFM;
			String first_name, last_name;
			double savings, taxed_income;

			while(line!=null){
				AFM = 0;
				savings = 0; 
				taxed_income = 0;
				first_name = null;
				last_name = null;
				
				AFM = Integer.parseInt(line.trim().substring(0,5).trim()); // save afm
				
				line = line.trim().substring(5).trim();
				
				int i = 0;
				
				while(line.charAt(i) != ' '){
					i++;
				}
				
				first_name = line.substring(0, i).trim(); // Save first_name
				line = line.substring(i).trim();
				
				i = 0;
				
				while(line.charAt(i) != ' '){
					i++;
				}
				
				last_name = line.substring(0, i).trim(); // Save last_name
				line = line.substring(i).trim();
				
				i = 0;
				
				while(line.charAt(i) != ' '){
					i++;
				}
				
				savings = Double.parseDouble(line.substring(0, i).trim()); // Save savings
				line = line.substring(i).trim();
				
				taxed_income = Double.parseDouble(line.trim()); // Save taxed_income				
				
				Suspect suspect = new Suspect(AFM, first_name, last_name, savings, taxed_income); // create suspect
				insert(suspect); // insert suspect in tree
				
				line = reader.readLine(); // read next line of file
			}
		}catch (IOException e) {
			System.out.println("Error reading file!");
		}
	}
	
	// Get mean savings of suspects in tree
	private void getMeanSavingsRec(TreeNode node) {
		if(node.l != null) getMeanSavingsRec(node.l); // if left of node is available call getMeanSavingsRec with left of node
		savings = savings + node.item.getSavings(); // save savings
	    if(node.r != null) getMeanSavingsRec(node.r); // if right of node is available call getMeanSavingsRec with right of node
	}	
	public double getMeanSavings(){
		savings = 0;
		getMeanSavingsRec(head); // save savings of suspects
		return savings / nodes; // return mean savings of suspects
	}
	
	// Print top suspects of tree
	private void quickSort(int l, int h){
		Suspect tmp;
		int i = l;
		int j = h;
		
		Suspect pivot = suspectsArray[l+(h-l)/2];
		double savingsSubTaxed = pivot.getSavings() - pivot.getTaxedIncome();
		
		while(i <= j){
			double savingsSubTaxedAr = suspectsArray[i].getSavings() - suspectsArray[i].getTaxedIncome();
			
			while (savingsSubTaxedAr > savingsSubTaxed){ // While MoviesArray[i] > pivot
				i++;
				savingsSubTaxedAr = suspectsArray[i].getSavings() - suspectsArray[i].getTaxedIncome();
			}
			
			savingsSubTaxedAr = suspectsArray[j].getSavings() - suspectsArray[j].getTaxedIncome();
			
			while (savingsSubTaxed > savingsSubTaxedAr){ // While pivot > MoviesArray[j]
				j--;
				savingsSubTaxedAr = suspectsArray[j].getSavings() - suspectsArray[j].getTaxedIncome();
			}
			
			if(i<=j){
				tmp = suspectsArray[i];
				suspectsArray[i] = suspectsArray[j];
				suspectsArray[j] = tmp;
				i++;
				j--;
			}
		}
		
		if (l < j) quickSort(l, j); // Call quickSort
		if (i < h) quickSort(i, h); // Call quickSort
	}
	private void printTopSuspects(TreeNode node){
		if(node.l != null) printTopSuspects(node.l); // if left of node is available call printTopSuspects with left of node
		suspectsArray[counter] = node.item; // save suspect on array
		counter++; // increase counter
	    if(node.r != null) printTopSuspects(node.r); // if right of node is available call printTopSuspects with right of node
	}
	public void printTopSuspects(PrintStream stream, int k){
		if(k > nodes) System.out.println("There are not so many suspects available."); // if k greater than available suspects
		else{
			suspectsArray = new Suspect[nodes]; // add suspect to array
			
			printTopSuspects(head); // call printTopSuspects with head
			
			quickSort(0, nodes-1); // sort array with quickSort
			
			// Print first k of array
			for(int i = 0; i<k; i++){
				System.out.println(suspectsArray[i]);
			}
		}
		counter = 0;
	}
	
	// Print suspects of tree by AFM
	private void printTreeByAFMRec(PrintStream stream, TreeNode node){
		if(node.l != null) printTreeByAFMRec(stream, node.l); // if left of node is available call printTreeByAFMRec with left of node
	    stream.println(node.item); // print suspect informations of node
	    if(node.r != null)printTreeByAFMRec(stream, node.r); // if right of node is available call printTreeByAFMRec with right of node		
	}
	public void printTreeByAFM(PrintStream stream){
		if(nodes==0){ System.out.println("Tree Is Empty!" +"\n"); return; }
		printTreeByAFMRec(stream, head); // call printTreeByAFMRec with head node
	}
}