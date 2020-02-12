public class SearchResult{
	PageEntry p;
	float r;
	public SearchResult(PageEntry p, float r){
		this.p=p;
		this.r=r;
	}
	public PageEntry getPageEntry(){
		return p;
	}
	public float getRelevance(){
		return r;
	}
	public int compareTo(SearchResult otherObject){
		if(otherObject.r==this.r) return 0;
		else if(this.r>otherObject.r) return 1;
		else return -1;
	}
}