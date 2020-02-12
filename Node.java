public class Node<Object>{
		 Object data;
		Node<Object> next;
		Node<Object> prev;
	//Node constructor
		public Node(){
		}
		public Node(Object d){
			data=d;
			next=null;
			prev=null;
		}
	}	