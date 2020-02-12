public class AVLTree
{
	public AVLNode root;
	
	//constructor
	public AVLTree()
	{
		root=null;
	}

	//constructor 2
	public AVLTree(AVLNode t)
	{
		root=t;
	}

	//to check emptyness
	public boolean IsEmpty()
	{ return(root==null); }

	//Search in BST and tell if this key exists
	public boolean IsMember(AVLNode node,int k)
	{
		if(node.key==k) return true;

		else if(node.key>k) 
		{	
			if(node.left!=null)
			return IsMember(node.left,k);
			else return false;  // if it doesn't have left node it means that it doesn't have this key and so return this node itself;
		}
		
		else {
			if(node.right!=null)
			return IsMember(node.right,k);
			else return false;
		}
	}	

	//to find maximum of 2 values
	public int max(int l, int r)
	{
		if(l>=r) return l;
		else return r;
	}

	//Search in a binary Search tree and return the node 
	public AVLNode Search(AVLNode node,int k)
	{
		//System.out.println("Search1  "+ node.key);
		if(node.key==k) return node;
		else if(k<node.key) 
		{	
			if(!node.hasLeft()) return node;
			
			else{
			 //System.out.println("Search2  "+ node.left.key);
			 return Search(node.left,k);  // if it doesn't have left node it means that it doesn't have this key and so return this node itself;
			}
		}
		
		//System.out.println("Search2");
		else {
			if(!node.hasRight()) return node;
			
			else{
			 // System.out.println("Search2  "+ node.right.key);
			 return Search(node.right,k);
			}
		}
	}


	//Insert a node
	public void Insert(Position pos,int k)
	{
		try
		{
			//System.out.println("key added"+k);
			if(root==null)
			{
				//System.out.println("1");
				root= new AVLNode(pos);
				return;
			}

			AVLNode t= this.Search(root,k);

	//			System.out.println("where added " +t.key);
			if(k<=t.key)
			{
	//			System.out.println("22");
				t.left= new AVLNode(pos);
				t.left.parent=t;
			}

			if(k>t.key)
			{
				//System.out.println("33");
				t.right= new AVLNode(pos);
				t.right.parent=t;
			}

			t.ht=1;

			//to update height as we move up 
			// if(!t.hasLeft() || !t.hasRight())
			// {

			// }
			AVLNode temp=t.parent;

			if(temp!=null)
			//System.out.println("parent "+temp.key);

			while(temp!=null)
			{
				int l,r;
				 l=temp.htLeft();

				 r=temp.htRight();

				if(l-r>-2 && l-r<2)
					{
						temp.ht= max(l,r)+1;
						//System.out.println(temp.ht);
						temp=temp.parent;
					}
				else if(l-r==-2)
				{
					//System.out.println("44");
					rotateRight(temp);
					//System.out.println("rotate right");
					break;
				}

				else if(l-r==2)
				{
					//System.out.println("100");
					rotateLeft(temp);
					//System.out.println("rotate left");
					break;
				}
			}
		}
		catch(Exception e){System.out.println(e);}
	}

	//rotate from right
	public void rotateLeft(AVLNode t)
	{
		AVLNode t1, t2;
		int flag=0;

		try{
		t1=t.left;
		if(t1.htLeft()>=t1.htRight()) 
			{
				t2=t1.left;
				if(t.parent!=null){
				if(t.parent.key>=t.key)
					t.parent.setLeft(t1);
				else t.parent.setRight(t1);}

				else flag=1;

				if(t1.hasRight())
				t.setLeft(t1.right);

				else t.left=null;

				t1.setRight(t);
				t.ht= max(t.htLeft(), t.htRight())+1;
				t2.ht= max(t2.htLeft(), t2.htRight())+1;
				t1.ht= max(t1.htLeft(), t1.htRight())+1;
				if(flag==1) 
					{
						root=t1;
						t1.parent=null;
					}
			}
		else 
		{
			t2=t1.right;

			if(t.parent!=null){
			if(t.parent.key>=t.key)
				t.parent.setLeft(t2);
			else t.parent.setRight(t2);}
			else flag=1;

			if(t2.hasLeft())
			t1.setRight(t2.left);
			else t1.right=null;
			
			if(t2.hasRight())
			t.setLeft(t2.right);
			else t.left=null;
			
			t2.setLeft(t1);
			t2.setRight(t);
			 
			t1.ht= max(t1.htLeft(), t1.htRight())+1;
			t.ht= max(t.htLeft(), t.htRight())+1;
			t2.ht= max(t2.htLeft(), t2.htRight())+1;

			if(flag==1) 
					{
						root=t2;
						t2.parent=null;
					}

		}}
		catch(Exception e){System.out.println(e);}
	}

	//rotate from right
	public void rotateRight(AVLNode t)
	{
		AVLNode t1, t2;
		int flag=0;
		try{
		t1=t.right;
		if(t1.htRight()>t1.htLeft()) 
			{
				//System.out.println("55");
				t2=t1.right;
				if(t.parent!=null)
				{
					if(t.parent.key>=t.key)
						t.parent.setLeft(t1);
					else t.parent.setRight(t1);
				}

				else flag=1;

				//System.out.println("66");

				if(t1.left !=null)
				{ t.setRight(t1.left); }
				else t.right=null;

				t1.setLeft(t);
				
				t.ht= max(t.htLeft(), t.htRight())+1;
				t2.ht= max(t2.htLeft(), t2.htRight())+1;
				t1.ht= max(t1.htLeft(), t1.htRight())+1;

				if(flag==1) 
					{
						root=t1;
						t1.parent=null;
					}
			}
		else 
		{
			t2=t1.left;
			if(t.parent!=null)
			{
			if(t.parent.key>=t.key)
				t.parent.setLeft(t2);
			else t.parent.setRight(t2);}

			else flag=1;

	//System.out.println("77");
	
			if(t2.hasRight())
			t1.setLeft(t2.right);
			else t1.left=null;

			if(t2.hasLeft())
			t.setRight(t2.left);
			else t.right=null;
			
			t2.setLeft(t);
			t2.setRight(t1);
			//System.out.println("88");
			t1.ht= max(t1.htLeft(), t1.htRight())+1;
			t.ht= max(t.htLeft(), t.htRight())+1;
			t2.ht= max(t2.htLeft(), t2.htRight())+1;

			if(flag==1) 
					{
						root=t2;
						t2.parent=null;
					}

		}}
		catch(Exception e){System.out.println(e);}
	}	


}