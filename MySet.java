public class MySet<Object> extends MyLinkedList<Object>{
	MyLinkedList<Object> l;

	//MySet constructor
	public MySet(){
		l= new MyLinkedList<Object>();
	}

	//checks if the set is empty
	public boolean IsEmpty(){
		return l.IsEmpty();
	}

	//checks if a the member of the set
	public boolean IsMember(Object o){
		return l.IsMember(o);
	}

	//inserts a in the set
	public void Insert(Object o){
		if(IsMember(o)) return;
		else l.InsertRear(o);

	}

	//deletes a if it is present in the set else throws exception
	public void Delete(Object o){
		try{
			if(!IsMember(o)){
				throw new Exception();
			}
			else l.Delete(o);
		}
		catch(Exception e){
			System.out.println("The Set does not contain the element");
		}
	}

	//returns union of two sets
	public MySet<Object> Union(MySet<Object> a){
		MySet<Object> USet= new MySet<Object>();
		USet=a;
		Node<Object> n=l.head;
		while(n!=null){
			try{
				USet.Insert(n.data);
			}
			catch(Exception e){}
			n=n.next;
		}
		
		return USet;
	}

	//returns intersection of two sets
	public MySet<Object> Intersection (MySet<Object> a){
	 MySet<Object> ISet = new MySet<Object>();
		Node<Object> n=l.head;
		while(n!=null){
			if(a.IsMember(n.data)){
				ISet.Insert(n.data);
				n=n.next;
			}
			else n=n.next;
		}
		return ISet;
	}
}


	
