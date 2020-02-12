
public class MyHashTable {
	BucketArray[] arr;
	MyLinkedList<WordEntry> ll=new MyLinkedList<WordEntry>();

	//constructor
	public MyHashTable(){
		arr = new BucketArray[26];
		for(int i=0;i<26;i++){
			arr[i]=new BucketArray();
		}
	}

	//returns the hash index of a given word input
	private int getHashIndex(String str){
		String s=str.toLowerCase();
		int i =(int)s.charAt(0)-97;
		if((i<26)&&(i>=0)) return i;
		else return 0;
	}

	// 	This adds an entry
	// to the hashtable: stringName(w) − > positionList(w). If no wordentry
	// exists, then create a new word entry. However, if a wordentry
	// exists, then merge w with the existing word-entry.

	public void addPositionsForWord(WordEntry w){
		String s=w.word.toLowerCase();
		int index =getHashIndex(s);

		
		MyLinkedList<WordEntry> list=arr[index].l;
		Node<WordEntry> n=ll.head;
		
		while(n!=null){
			if(n.data.word.equals(s)) {
				break;
			}
			n=n.next;
		}
		if(n!=null){
			n.data.l=n.data.l.Join(w.l);
		}
		else{
			list.InsertRear(w);
		}
	}
}
