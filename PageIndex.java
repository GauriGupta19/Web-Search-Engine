public class PageIndex {
	public MyLinkedList<WordEntry> l;

	//constructor
	public PageIndex(){
		l= new MyLinkedList<WordEntry>();
	}

	// checks if the given pageentry contains the word 
 	public Boolean containsWord(String str ){
 		try{
	 		Node<WordEntry> n = l.head;
			while(n!=null){
				if(n.data.word.equals(str)) return true;
				else n=n.next;
			}
			return false;
		}
		catch(Exception e){
			System.out.println("Null Pointer Exception");
			return null;
		}
 	}

    // 	Add position p to the word-entry of str. If a word entry for str is already
	// present in the page index, then add p to the word entry. Otherwise,
	// create a new word-entry for str with just one position entry p.
	public void addPositionForWord(String str, Position p){
		Node<WordEntry> n = l.head;
		if(containsWord(str)){
			while(!n.data.word.equals(str)){
				n=n.next;
			}

			n.data.addPosition(p);
		}
		else{
			WordEntry w=new WordEntry(str);
			w.addPosition(p);
			l.InsertRear(w);
		}
	}
	public MyLinkedList<WordEntry> getWordEntries(){
		return l;
	}

	public String getWordForKey(int k){
		//System.out.println("pi6");
		Node<WordEntry> n=l.head;
		while(n!=null){
			// System.out.println("ffffffffff"+n.data.tree.IsEmpty());
			// System.out.println(n.data.tree.IsMember(n.data.tree.root,k));
			//System.out.println(n.data.tree.root.key);
			if(n.data.tree.IsMember(n.data.tree.root,k)) {
				//System.out.println("pi8");
				return n.data.word;
			}
			n=n.next;
		}
		return null;
	}
}