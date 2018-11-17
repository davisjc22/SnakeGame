//package forKids;

public class OrderedList {
	
	private OrderedListNode first;
	private int size;
	
	public OrderedList(){
		first = null;
		size = 0;
	}
	
	public boolean isEmpty(){return first==null;}
	
	public void addFirst( Comparable newValue){
		first = new OrderedListNode( newValue, first);
	}
	
	/*
	 * Preconditions:  The elements that are currently in the list
	 *  (if there are any) are arranged in order
	 *  Postconditions:  A new OrderedListNode is created containing newValue
	 *  	This new node is inserted into the list into the appropriate place
	 *  	so that the list is STILL in order
	 *  	(you can decide if you want ascending or descending order) 
	 */
	public void add(Comparable newValue){
		
		if(first == null){//special case, empty list
			addFirst(newValue);
			size++;
			return;
		}
		
		OrderedListNode prev = first;
		OrderedListNode curr = first.getNext();
		
		if(newValue.compareTo(first.getValue())<0){//if the new value is less than the first value
			addFirst(newValue);
			size++;
			return;
		}
		while(curr!=null && newValue.compareTo(curr.getValue())>0){
			prev = curr;
			curr = curr.getNext();
		}
		OrderedListNode nu = new OrderedListNode(newValue, curr);
		prev.setNext(nu);
		size++;
		
		
	}
	
	public Object get( int index ){
		OrderedListNode curr = first;
		while (index > 0){
			curr = curr.getNext();
			index--;
		}
		return curr.getValue();//returns the object in the index corresponding to the user input
	}
	
	
	public int size(){return size;}//size is 0(1)
	
	public String toString(){
		String ans="";
		OrderedListNode curr=first;
		while(curr!=null){
			ans+=curr.getValue()+"\n";
			curr = curr.getNext();
		}
		return ans;
	}
	
	public String toFileString(){
		String ans="";
		OrderedListNode curr=first;
		while(curr!=null){
			ScoreRecord s = (ScoreRecord)curr.getValue();
			ans+=s.formatForFile()+"\r\n";
			curr = curr.getNext();
		}
		return ans;
	}
	
}
