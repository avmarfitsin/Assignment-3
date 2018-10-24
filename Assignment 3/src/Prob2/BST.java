package Prob2;

public class BST 
{
	int numelem;
	private static int sze;
	class Node 
	{
		Node l, r;
		int obj;
		public Node(int nod) {
			l = null;
			r = null;
			obj = nod;
		}
	}

	private Node ctr;
	public BST() 
	{
		ctr = null;
	}
		public void put(int d)
		{
			sze++;
			Node newNode = new Node(d);
			if(ctr==null)
			{
				ctr = newNode;
				return;
			}
			Node tmploc = ctr;
			Node parent = null;
			while(true)
			{
				parent = tmploc;
				if(d<tmploc.obj)
				{		
					tmploc = tmploc.l;
					if(tmploc==null)
					{
						parent.l = newNode;
						return;
					}
				}
				else
				{
					tmploc = tmploc.r;
					if(tmploc==null)
					{
						parent.r = newNode;
						return;
					}
				}
			}
			
		}
		
		private Node rotateLeft(Node h)
		 {
			if(h.r==null)
				return null;
		 Node tmp = h.r;
		 h.r = tmp.l;
		 tmp.l = h;
		 tmp.obj = h.obj;
		 return tmp;
		 }

		private Node rotateRight(Node h)
		 {
			if(h.l==null)
				return null;
		 Node tmp = h.l;
		 h.l = tmp.r;
		 tmp.r = h;
		 return tmp;
		 }
		public void balanceTreeTwo()
	    {
	        int M = sze+1-(int)Math.pow(2, Math.floor(Math.log(2)/Math.log(sze)));
	        transformToList();
	        Node ctr1=ctr;
	        Node temp = ctr;
	        for(int i=1; i< M*2; i++)
	        {
	            if(i%2 == 1&&temp!=null)
	            {
	                rotateLeft(temp);
	                temp = temp.r;
	            }
	        }
	        int K = (int)Math.floor(Math.log(2)/Math.log(sze))-1;
	        while(K>1)
	        {
	            rotateLeft(ctr);
	            K--;
	        }
	        if(K==1)
	        	ctr1=rotateLeft(ctr1);
	        ctr=ctr1;  	
	    }
		public void transformToList() 
		{
			Node tmp=ctr;
			while(tmp!=null)
			{
			while(tmp.l!=null)
			{
				tmp=rotateRight(tmp);
			}
			tmp=tmp.r;
			}
	        }

		public static int returnSize()
		{
			return sze;
		}
		public void put(int []a)
		{
			for(int tmp=0; tmp < a.length; tmp++)
			{
				put(a[tmp]);
			}
			
		}

		public int search(int addr)
		{
			int numcomp=0;
			Node tmploc = ctr;
			while(tmploc!=null)
			{
				if(tmploc.obj>addr)
				{
					numcomp++;
					tmploc = tmploc.l;
				}
				else if(tmploc.obj==addr)
				{
					numcomp++;
					System.out.println(numcomp+" comparisons made");
					return tmploc.obj;
				}
				else if (tmploc.obj!=addr)
				{
					numcomp++;
					tmploc = tmploc.r;
				}
			}
			System.out.println("Key not found");
			return 0;
		}		
		
		public int[] sortedTree()
		{
			int size = sze;
			int[] arr = new int[size];
			drilDn(ctr,arr,0);
			int nod = arr.length; 
	        for (int sze = 0; sze < nod-1; sze++) 
	        { 
	            int lowe = sze; 
	            for (int j = sze+1; j < nod; j++) 
	                if (arr[j] < arr[lowe]) 
	                    lowe = j; 
	            int temp = arr[lowe]; 
	            arr[lowe] = arr[sze]; 
	            arr[sze] = temp; 
	        } 
	        
			return arr;			
		}
		private static int drilDn(Node n, int[] arrE, int adr) 
		{
		    if (n.l != null) 
		    {
		        adr = drilDn(n.l, arrE, adr);
		    }
		    if (n.r != null) 
		    {
		        adr = drilDn(n.r, arrE, adr);
		    }
		    arrE[adr] = n.obj;
		    return adr+1;
		}
		public void printList()
		{
			Node ptr=ctr;
			while(ptr!=null)
			{
				System.out.println(ptr.obj);
				ptr=ptr.r;
			}
		}
	
	public static void main(String[] args)
    {         
		BST bst = new BST();
        int[]d= {1,2,3,9,11,3465436,3253275};
        int[]a;
        bst.put(1);
        bst.put(5);
        bst.put(69);
        bst.put(4376);
        bst.put(d);
        bst.printList();
       System.out.println(returnSize());
       System.out.println(bst.search(69));
       a=bst.sortedTree();
       for(int i=0;i<a.length;i++)
       {
    	   System.out.println(a[i]);
       }
       // bst.transformToList();
       // bst.balanceTreeTwo(); 
    }
}

