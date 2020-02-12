public class Position {
	public PageEntry pEntry;
	public int wIndex;
	public int wIndex2;
	//constructor
	public Position(PageEntry p, int wordIndex){
		pEntry=p;
		wIndex=wordIndex;
		wIndex2=0;
	}
	// public Position(PageEntry p,int wordIndex1,int wordIndex2){
	// 	pEntry=p;
	// 	wIndex=wordIndex1;
	// 	wIndex2=wordIndex2;
	// }
	public PageEntry getPageEntry(){
		return pEntry;
	}
	public int getWordIndex(){
		return wIndex;
	}
}