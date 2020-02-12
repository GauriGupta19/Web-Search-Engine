import java.util.*;
import java.io.*;
public class PageEntry {
	public  String PageName;
	public PageIndex pIndex;
	public int numberOfWords;

	//creates a page entry of a given webpage
	public PageEntry(String pageName){
		try{
			//create a set of all punctuations
			MySet<Character> Punc= new MySet<Character>();
			Punc.Insert('{'); Punc.Insert('}'); Punc.Insert('['); Punc.Insert(']'); 
			Punc.Insert('<'); Punc.Insert('>'); Punc.Insert('='); Punc.Insert('('); 
			Punc.Insert('.'); Punc.Insert(','); Punc.Insert(';'); Punc.Insert('\''); 
			Punc.Insert('"'); Punc.Insert('?'); Punc.Insert('#'); Punc.Insert('!'); 
			Punc.Insert('-'); Punc.Insert(':'); Punc.Insert(')'); 

		 	PageName=pageName;
		 	pIndex=new PageIndex();
		 	FileInputStream fstream = new FileInputStream("webpages\\"+pageName);
		 	Scanner in = new Scanner(fstream);
		 	int count=0;
		 	int count2=0;
		 	while(in.hasNext()){
		 		String s= in.next().toLowerCase();
		 		int len=s.length();
		 		int flag=0;
		 		for(int i=0;i<len;i++){
		 			if(Punc.IsMember(s.charAt(i))){
		 				s=s.replace(s.charAt(i),' ');
		 			}
		 		}
		 		Scanner scan=new Scanner(s);
		 		//System.out.println("pe22");
		 		while(scan.hasNext()){
		 			String s1=scan.next();

		 			if(s1.equals("stacks")) s1="stack";
					if(s1.equals("structures")) s1="structure";
					if(s1.equals("applications")) s1="application";

					MySet<String> Connec= new MySet<String>();
					Connec.Insert("a"); Connec.Insert("an"); Connec.Insert("the"); Connec.Insert("they");
					Connec.Insert("these"); Connec.Insert("this"); Connec.Insert("for"); Connec.Insert("is");
					Connec.Insert("are"); Connec.Insert("was"); Connec.Insert("of"); Connec.Insert("or");
					Connec.Insert("and"); Connec.Insert("does"); Connec.Insert("will"); Connec.Insert("whose");



		 			//System.out.println(Connec.IsMember("a"));
		 			//System.out.println(s1);
		 			if(Connec.IsMember(s1)){
		 				//System.out.println("why");
		 				count++;
					}
					else{
						count++;
						count2++;
						Position p=new Position(this,count);
						p.wIndex2=count2;
						pIndex.addPositionForWord(s1,p);
						//System.out.println(p.wIndex2+s1);
						//System.out.println(p.wIndex+s1);

					}
		 		}
		 		
		 	}
		 	//System.out.println(count);

		 	numberOfWords=count2;
		 	//System.out.println(numberOfWords);

		 }
		 catch(FileNotFoundException e){
		 	System.out.println("File not found");
		 }

	}


	public PageIndex getPageIndex(){
		return pIndex;
	}

	//returns the position of a given word in the page entry
	public String getPositionsOfWord(String str){
		String s1="";
		int flag=0;
		Node<WordEntry> n= pIndex.l.head;
		while(n!=null){
			if(n.data.word.equals(str)){
				flag=1;
				Node<Position> temp = n.data.l.head;
				while(temp!=null){
					s1=s1+" "+temp.data.wIndex+", ";
					temp=temp.next;
				}
			}
			n=n.next;
		}
		if((n==null)&&(flag==0)){
			s1="Webpage "+this.PageName+" does not contain word "+str;
			return s1;
		}
		else return s1.substring(0,s1.length()-2);
	}

	// returns the relevance of a given set of words according to OR AND and phrase queries
	public float getRelevanceOfPage(String[] str, boolean doTheseWordsRepresentAPhrase){
		if(doTheseWordsRepresentAPhrase==false){
			return InvertedPageIndex.relevanceOfWords(str,this);
		}
		else{
			MySet<PageEntry> set=InvertedPageIndex.getPagesWhichContainPhrase(str);
			if(set.l.head==null) return 0;
			else{
				int nw=set.l.Size();
				float idf=(float)Math.log((float)InvertedPageIndex.numberOfPages()/(float)nw);
				int W=numberOfWords;
				int m=InvertedPageIndex.numberOfPhrases(str,this);
				float x=W-(str.length-1)*m;
				//System.out.println(idf);
				//System.out.println(W);
				return (float)m*idf/x;
				
			}
		}
	}
}