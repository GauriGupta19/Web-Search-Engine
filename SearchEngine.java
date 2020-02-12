import java.util.*;
public class SearchEngine {
	InvertedPageIndex iIndex;
	//constructor
	public SearchEngine(){
		iIndex=new InvertedPageIndex();
	}

	public void performAction(String actionMessage){
		//try{
			Scanner in=new Scanner(actionMessage);
			String s= in.next();

			//Add webpage x to the search engine database. The contents
			//of the webpage are stored in a file named x in the webpages folder
			if(s.equals("addPage")){
				String x =in.next();
				PageEntry page=new PageEntry(x);
				iIndex.addPage(page);
			}

			//x Print the name of the webpages
			//which contain the word x. The list of webpage names should be comma
			//separated. If the word is not found in any webpage, then print No
			//webpage contains word x
			if(s.equals("queryFindPagesWhichContainWord")){
				actionMessage=actionMessage+": ";
				String x= in.next().toLowerCase();
				if(x.equals("stacks")) x="stack";
				if(x.equals("structures")) x="structure";
				if(x.equals("applications")) x="application";

				MySet<PageEntry> pages= iIndex.getPagesWhichContainWord(x);
				Node<PageEntry> n= pages.l.head;
				MySet<SearchResult> set= new MySet<SearchResult>();
				if(n==null){
					actionMessage=actionMessage+"No webpage contains word "+x;
				}
				else{
					while(n!=null){

						float r=InvertedPageIndex.relevanceOfWordInPage(x,n.data);
						SearchResult sr=new SearchResult(n.data,r);
						set.Insert(sr);
						n=n.next;
						// actionMessage=actionMessage+n.data.PageName+", ";
						// n=n.next;
					}
					ArrayList<SearchResult> al=MySort.sortThisList(set);
					//System.out.println("se6");
					for(int i=0;i<al.size();i++){
						//System.out.println("se7");
						actionMessage=actionMessage+al.get(i).p.PageName+" ,";
					}
					actionMessage=actionMessage.substring(0,actionMessage.length()-2);
				}
			}

			//y Print the word indice’s where the word x is found in the document y. The word indices should be
			//separated by a comma. If the word x is not found in webpage y, then
			//print Webpage y does not contain word x. If the webpage is not
			//added in database, then print No webpage y found.
			if(s.equals("queryFindPositionsOfWordInAPage")){
				actionMessage=actionMessage+": ";
				String x= in.next().toLowerCase();
				String y= in.next().toLowerCase();
				if(x.equals("stacks")) x="stack";
				if(x.equals("structures")) x="structure";
				if(x.equals("applications")) x="application";

				if(y.equals("stacks")) y="stack";
				if(y.equals("structures")) y="structure";
				if(y.equals("applications")) y="application";


				int flag=0;
				Node<PageEntry> n=iIndex.collection.l.head;
				if(n==null){
					actionMessage=actionMessage+"There are no webpages ";
					return;
				}
				while(n!=null){
					
					if(n.data.PageName.equals(y)){
						//System.out.println(n.data.PageName);
						break;
					}
					n=n.next;
				}
				if(n==null){
					actionMessage=actionMessage+"Webpage "+y+" does not exist";
				}
				if(n!=null){
				String indices=n.data.getPositionsOfWord(x);
				actionMessage=actionMessage+indices;
			    }
			}

			//Print the name of the webpages which contain all the words 
			if(s.equals("queryFindPagesWhichContainAllWords")){
				actionMessage=actionMessage+": ";
				ArrayList<String> Arrl= new ArrayList<String>();
				while(in.hasNext()){
					String x=in.next().toLowerCase();
					if(x.equals("stacks")) x="stack";
					if(x.equals("structures")) x="structure";
					if(x.equals("applications")) x="application";

					Arrl.add(s);
				}
				String[] arr=new String[Arrl.size()];
				for(int i=0;i<Arrl.size();i++){
					arr[i]=Arrl.get(i);
				}
				MySet<PageEntry> pages=new MySet<PageEntry>();
				//System.out.println(arr[0]);
				pages=InvertedPageIndex.getPagesWhichContainWord(arr[0]);
				//System.out.println("se1");
				for(int i=1;i<arr.length;i++){
					MySet<PageEntry> x=InvertedPageIndex.getPagesWhichContainWord(arr[i]);
					pages=pages.Intersection(x);
				}
				//System.out.println("se2");
				Node<PageEntry> n=pages.l.head;
				MySet<SearchResult> set= new MySet<SearchResult>();
				//System.out.println("se3");
				while(n!=null){
					//System.out.println("se4");
					float r=n.data.getRelevanceOfPage(arr,false);
					SearchResult sr=new SearchResult(n.data,r);
					set.Insert(sr);
					n=n.next;
				}
				ArrayList<SearchResult> al=MySort.sortThisList(set);
				if(al==null){
					actionMessage=actionMessage+"There are no such webpages";
				}
				if(al!=null){
				//System.out.println("se6");
				for(int i=0;i<al.size();i++){
					//System.out.println("se7");
					actionMessage=actionMessage+al.get(i).p.PageName+" ,";
				}
				actionMessage=actionMessage.substring(0,actionMessage.length()-2);}
			}

			//Print the name of the webpages which contain at least one word from this set 
			if(s.equals("queryFindPagesWhichContainAnyOfTheseWords")){
				actionMessage=actionMessage+": ";
				ArrayList<String> Arrl= new ArrayList<String>();
				while(in.hasNext()){
					String x=in.next().toLowerCase();
					if(x.equals("stacks")) x="stack";
					if(x.equals("structures")) x="structure";
					if(x.equals("applications")) x="application";

					Arrl.add(x);
				}
				String[] arr=new String[Arrl.size()];
				for(int i=0;i<Arrl.size();i++){
					arr[i]=Arrl.get(i);
				}
				MySet<PageEntry> pages=new MySet<PageEntry>();
				pages=InvertedPageIndex.getPagesWhichContainWord(arr[0]);
				for(int i=1;i<arr.length;i++){
					MySet<PageEntry> x=InvertedPageIndex.getPagesWhichContainWord(arr[i]);
					pages=pages.Union(x);
				}
				Node<PageEntry> n=pages.l.head;
				MySet<SearchResult> set= new MySet<SearchResult>();
				while(n!=null){
					float r=n.data.getRelevanceOfPage(arr,false);
					SearchResult sr=new SearchResult(n.data,r);
					set.Insert(sr);
					n=n.next;
				}
				ArrayList<SearchResult> al=MySort.sortThisList(set);
				for(int i=0;i<al.size();i++){
					actionMessage=actionMessage+al.get(i).p.PageName+" ,";
				}
				actionMessage=actionMessage.substring(0,actionMessage.length()-2);
			}

			//Print the name of the webpages which contain the phrase
			if(s.equals("queryFindPagesWhichContainPhrase")){
				actionMessage=actionMessage+": ";
				ArrayList<String> Arrl= new ArrayList<String>();
				while(in.hasNext()){
					String x=in.next().toLowerCase();
					if(x.equals("stacks")) x="stack";
					if(x.equals("structures")) x="structure";
					if(x.equals("applications")) x="application";

					Arrl.add(x);
				}
				//System.out.println("se8");
				String[] arr=new String[Arrl.size()];
				for(int i=0;i<Arrl.size();i++){
					arr[i]=Arrl.get(i);
				}
				MySet<PageEntry> pages=InvertedPageIndex.getPagesWhichContainPhrase(arr);
				Node<PageEntry> n=pages.l.head;
				//System.out.println("se6");
				//System.out.println("ETatthf"+(n==null));
				MySet<SearchResult> set= new MySet<SearchResult>();
				while(n!=null){
					//System.out.println("se7");
					float r=n.data.getRelevanceOfPage(arr,true);
					SearchResult sr=new SearchResult(n.data,r);
					set.Insert(sr);
					n=n.next;
				}
				//System.out.println("se8");
				//System.out.println(set==null);
				ArrayList<SearchResult> al=MySort.sortThisList(set);
				if(al==null){
					actionMessage=actionMessage+"No webpage contains the given phrase";
				}
				if(al!=null){
					for(int i=0;i<al.size();i++){
						actionMessage=actionMessage+al.get(i).p.PageName+" ,";
					}
					actionMessage=actionMessage.substring(0,actionMessage.length()-2);
				}
			}


			System.out.println(actionMessage);
		// }
		// catch(Exception e){
		// 	System.out.println(e);
		// }
	}

}


