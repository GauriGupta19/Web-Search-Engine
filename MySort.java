import java.util.*;
import java.io.*;

public class MySort{
	public static ArrayList<SearchResult> sortThisList(MySet<SearchResult> listOfSortableEntries){
		ArrayList<SearchResult> arr= new ArrayList<SearchResult>();
		Node<SearchResult> n=listOfSortableEntries.l.head;
		//System.out.println(listOfSortableEntries.l.Size());
		//System.out.println(n==null);
		//System.out.println("ms1");
		if(n==null) return null;
		arr.add(0,n.data);
		n=n.next;
		//System.out.println(n==null);
		while(n!=null){
			int flag=0;
			int i=arr.size()-1;
			while(i>=0){
				//System.out.println(arr.size()+"size");
				//System.out.println(i+"i");
				if(n.data.compareTo(arr.get(i))<=0){
					//System.out.println("in");
					if(i<arr.size()-1){
						//System.out.println("ms2");
						arr.add(i+1,n.data);
						n=n.next;
						flag=1;
						i--;
						break;
					}
					else{
						arr.add(n.data);
						n=n.next;
						flag=1;
						i--;
						break;
					}
				}
				i--;
			}
			//System.out.println(i==-1);
			//System.out.println(flag);
			if((i==-1)&&(flag==0)){
				//System.out.println("ms3");
				arr.add(0,n.data);
				n=n.next;
			}
			//System.out.println(n==null);
		}
		//System.out.println("ms5");
		return arr;
	}
}