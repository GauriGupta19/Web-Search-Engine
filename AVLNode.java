public class AVLNode
{
	AVLNode left, right, parent;
	Position p;
	int key;
	int ht;

	//constructor 1
	public AVLNode()
	{
		left=null;
		right=null;
		parent=null;
		p=null;
		ht=-1;
		key=0;
	}

	//constructor 2
	public AVLNode(Position pos)
	{
		left=null;
		right=null;
		parent=null;
		p=pos;
		key=pos.wIndex2;
		ht=0;	

	}

	public boolean hasLeft()
	{ return left!=null; }

	public boolean hasRight()
	{ return right!=null; }

	public void setLeft(AVLNode left)
	{ 
		this.left=left;
		left.parent=this;
	}

	public void setRight(AVLNode right)
	{ 
		this.right=right;
		right.parent=this;
	}

	public int htLeft()
	{
		if(left==null) return -1;
		else return left.ht;
	}

	public int htRight()
	{
		if(right==null) return -1;
		else return right.ht;
	}

}