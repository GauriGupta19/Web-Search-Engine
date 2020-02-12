import java.lang.Math.*;

public class InvertedPageIndex {

	public MyHashTable table;
	public static MySet<PageEntry> collection;
	//constructor
	public InvertedPageIndex(){
		table=new MyHashTable();
		collection = new MySet<PageEntry>();

	}

	//Add a new page entry p to the inverted page index.
	public void addPage(PageEntry p){
		try{
			collection.Insert(p);
			Node<WordEntry> n= p.pIndex.l.head;
			while(n!=null){
				table.addPositionsForWord(n.data);
				n=n.next;
			}
		}
		catch(Exception e){
			System.out.println("Null Pointer Exception");
		}
	}

	//Return a set of page-entries of webpages which contain the word str.
	public static MySet<PageEntry> getPagesWhichContainWord(String str){
		MySet<PageEntry> c = new MySet<PageEntry>();
		Node<PageEntry> n=collection.l.head;
		while(n!=null){
			if(n.data.pIndex.containsWord(str)){
				c.Insert(n.data);
				
			}
			n=n.next;
		}
		return c;
	}

	//returns the set of pages that contain the given phrase
	public static MySet<PageEntry> getPagesWhichContainPhrase(String str[]){
		//try{
			MySet<PageEntry> ans=new MySet<PageEntry>();
			MySet<PageEntry> set=getPagesWhichContainWord(str[0].toLowerCase());
			//System.out.println(set.l.head.data.PageName);
			//System.out.println("pe7");
			Node<PageEntry> n= set.l.head;
			while(n!=null){
				//System.out.println("pe8");
				Node<WordEntry> temp=n.data.pIndex.l.head;
				//System.out.println(temp.data.word);
				while(temp!=null){
					//System.out.println("p3");
					if(temp.data.word.equals(str[0])) {
						//System.out.println("pe2");
						break;
					}
					temp=temp.next;
				}
				//System.out.println(temp==null);
				Node<Position> n1=temp.data.l.head;
				//System.out.println(n1==null);
				while(n1!=null){
					int key1=n1.data.wIndex2;
					int i;
					//System.out.println("pe9");
					for(i=1;i<str.length;i++){
						//System.out.println(n.data.pIndex.getWordForKey(1));
						if(n.data.pIndex.getWordForKey(key1+i)==null){

							//System.out.println("no");
							break;
						}
						else{
							//System.out.println(key1+i);
							if(n.data.pIndex.getWordForKey(key1+i).equals(str[i])){
									//System.out.println("pe6");
								continue;
							} 
							else break;
						}
					}
					if(i==str.length) {
						ans.Insert(n.data);
						break;
					}
					n1=n1.next;
				} 
				n=n.next;
			}
			return ans;
		// }
		// catch(Exception e){
		// 	System.out.println(e);
		// 	return null;
		// }
	}

	//returns the value of number of such phrases in a given page
	public static int numberOfPhrases(String[] str,PageEntry p){
		MySet<PageEntry> myset=getPagesWhichContainPhrase(str);
		int ans=0;
		if(myset.IsMember(p)){
			MySet<PageEntry> Set=getPagesWhichContainWord(str[0].toLowerCase());
			Node<PageEntry> n= Set.l.head;
			while(n!=null){
				if(n.data.equals(p)) break;
				n=n.next;
			}
			Node<WordEntry> temp=n.data.pIndex.l.head;
			while(!temp.data.word.equals(str[0])){
				temp=temp.next;
			}
			Node<Position> n1=temp.data.l.head;
			while(n1!=null){
				int key1=n1.data.wIndex2;
				int i=0;
				for(i=0;i<str.length;i++){
					if(n.data.pIndex.getWordForKey(key1+i).equals(str[i])) {
						continue;
					}
					else break;
				}
				if(i==str.length) {
					ans++;
				}
				n1=n1.next;
			}
			return ans;
		}
		else return 0;
	}

	//returns the total number of pages
	public static int numberOfPages(){
		Node<PageEntry> n=collection.l.head;
		int count=0;
		while(n!=null){
			count++;
			n=n.next;
		}
		return count;
	}

	//finds the relevance of a word in a given page
	public static float relevanceOfWordInPage(String s,PageEntry p){
		MySet<PageEntry> set=getPagesWhichContainWord(s);
		if(set.l.head==null) return 0;
		else{
			int nw=set.l.Size();
			float idf=(float)Math.log((float)numberOfPages()/(float)nw);
			int W=p.numberOfWords;
			int f=0;
			Node<WordEntry> n= p.pIndex.l.head;
			if(n==null) return 0;
			else{
				while(n!=null){
					if(n.data.word.equals(s)){
						f=n.data.l.Size();
						break;
					}
					n=n.next;
				}
			}
			return (float)f*idf/W;
		}
	}

	//finds the relavence of given set of words in a given page
	public static float relevanceOfWords(String str[],PageEntry p){
		float ans=0;
		for(int i=0;i<str.length;i++){
			ans=ans+relevanceOfWordInPage(str[i],p);
		}
		return ans;
	} 

}

