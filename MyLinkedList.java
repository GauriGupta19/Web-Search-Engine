	public class MyLinkedList<Object>{
	//Node class
		
	public Node<Object> head=new Node<Object>();;

	//MyLinkedList Constructor
	public MyLinkedList(){
		head=null;
	}

	//checks if the MyLinkedList is empty
	public boolean IsEmpty(){
		return head==null;

	}

	//checks if a is the member of the MyLinkedList
	public boolean IsMember(Object o){
		Node<Object> n=head;
		while(n!=null){
			if(n.data.equals(o))
				return true;
			else n=n.next;
		}
		return false;
	}

	public int Size(){
		Node<Object> n=head;
		int count=0;
		while(n!=null){
			count++;
			n=n.next;
			
		}
		return count;
	}

	//inserts a at the front of the MyLinkedList
	public void InsertFront(Object o){
		Node<Object> x=new Node<Object>(o);
		x.next=head;
		head=x;
	}

	//inserts a at the rear end of the MyLinkedList
	public void InsertRear(Object o){
		Node<Object> n=head;
		Node<Object> x=new Node<Object>(o);
		if(n==null){
			head=x;
		}
		else{
		while(n.next!=null) n=n.next;
		n.next=x;
		x.prev=n;
		}
	}

	//deletes a if it in the list else throws exception
	public void Delete(Object o){
		Node<Object> n=head; 
		try{
			if(n==null){  //when the MyLinkedList is empty
				throw new Exception();
			}
			if(n.next==null && n.data.equals(o)) head=null;   //if the MyLinkedList has only one node which is to be deleted
			
			else if(n.data.equals(o)){ //if the node to be delted is the head of the MyLinkedList
				
				head=n.next;
				head.prev=null;
			}
			else {
				while(n!=null){
					if(n.data.equals(o)) break;
					n=n.next;
				}
				if(n.next!=null){    //if the node to be deleted is not the last node
					n.next.prev=n.prev;
				}
				if(n.prev!=null){   //if the node to be deleted is not the first node
					n.prev.next=n.next;
				}

			}
			return;
		}

		catch(Exception e){
			System.out.println("The list is empty");
		}
	}

	public MyLinkedList<Object> Join(MyLinkedList<Object> l2){
		Node<Object> n=head;
		if(n==null) return l2; 
		while(n.next!=null){
			n=n.next;
		}
		n.next=l2.head;
		return this;
	}
}