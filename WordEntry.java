public class WordEntry {
	public String word;
	public MyLinkedList<Position> l;
	public AVLTree tree ;
	public WordEntry(String word){
		l=new MyLinkedList<Position>();
		this.word=word;
		tree=new AVLTree();
	}
	public void addPosition(Position p){
		//System.out.println("we1");
		l.InsertRear(p);
		tree.Insert(p,p.wIndex2);
		// System.out.println("p.wIndex2 "+p.wIndex2);
		// System.out.println(tree.root==null);
	}
	public void addPositions(MyLinkedList<Position> positions){
		l=l.Join(positions);
	}
	public MyLinkedList<Position> getAllPositionsForThisWord(){
		return l;
	}
	public float getTermFrequency(String word){
		return l.Size();
	}
}