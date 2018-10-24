package Prob1; 
public class DaryHeap    
{
    private int childct;
    private int heapdimen;
    private int[] hepe;
    
    public DaryHeap(int d)
    {
        heapdimen = 0;
        childct = d;
        hepe = new int[10];

        for(int i=0;i<hepe.length;i++)
        {
        	hepe[i]=-1;
        }
    }
    public void doubleArray()
    {
    	int[] hepe1=new int[hepe.length*2];
    	for(int i=0;i<hepe.length;i++)
    	{
    		hepe1[i]=hepe[i];
    	}
    	hepe=hepe1;
    }
 
    public void insert(int x)
    {
    	if(heapdimen+1==hepe.length)
    		doubleArray();
        hepe[heapdimen++] = x;
        int k=heapdimen-1;
        int kelem = hepe[k];    
        while ( kelem < hepe[(k-1)/childct]&&k > 0)
        {
            hepe[k] = hepe[(((k-1)/childct)-1)/childct];
            k = (((k-1)/childct)-1)/childct;
        }                   
        hepe[k] = kelem;
        
    }
 
    public int delete(int i)
    {
        if (heapdimen == 0)
            System.out.println("Empty heap");
        int obj = hepe[i];
        hepe[i] = hepe[heapdimen - 1];
        heapdimen--;
        int cle;
        int kelem = hepe[i];
        while (1+i*childct < heapdimen)
        {
            int chil = 1+i*childct;
            int tpe=2;
            int locat = tpe+i*childct;
            while ((tpe <= childct)&&(locat < heapdimen)) 
            {
                if (hepe[locat] < hepe[chil]) 
                    chil = locat;
                locat = tpe+1+i*childct;
            }    
            cle=chil;
            if (hepe[cle] < kelem)
                hepe[i] = hepe[cle];
            else
                break;
            i = cle;
        }
        hepe[i] = kelem;        
        return obj;
    }
    
    public int delMax()
    {
    	int max=hepe[0];
    	int addr=0;
    	for(int i=0;i<hepe.length;i++)
    	{
    		if(hepe[i]>max)
    		{
    			max=hepe[i];
    			addr=i;
    		}
    	}
    	delete(addr);
    	return max;
    }
    
    public void printHeap()
    {
        for (int i = 0; i < heapdimen; i++)
            System.out.print(hepe[i] +" ");
    }
    void recursort(int upt, int delim) 
    { 
        int rside = 2+delim*2;
        int lside = 1+delim*2;
        int deli1=delim;
        if (rside<upt && hepe[rside] > hepe[deli1]) 
            deli1=rside; 
        if (lside < upt && hepe[lside] > hepe[deli1]) 
            deli1=lside; 
        if (deli1!=delim) 
        { 
            int swap = hepe[delim]; 
            hepe[delim] = hepe[deli1]; 
            hepe[deli1] = swap; 
            recursort(upt, deli1); 
        } 
    } 
    public void daryHeapsort() 
    { 
    	int i=heapdimen/2-1;
        while (i>=0) 
        {
            recursort(heapdimen,i);
            
            i--;
        }
        i=heapdimen-1;
        while(i>=0) 
        { 
            int store = hepe[0]; 
            hepe[0]=hepe[i]; 
            hepe[i]=store; 
            recursort(i, 0); 
            i--;
        } 
    }
    public static void main(String[] args)
    {
    	DaryHeap p1=new DaryHeap(3);
    	p1.insert(1);
    	p1.insert(2);
    	p1.insert(3);
    	p1.insert(4);
    	p1.insert(15);
    	p1.insert(12);
    	p1.insert(20);
    	p1.insert(18);
    	System.out.println("max="+p1.delMax());
    	p1.daryHeapsort();
    	p1.printHeap();
    	
    }
}