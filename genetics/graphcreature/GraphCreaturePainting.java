package genetics.graphcreature;

public class GraphCreaturePainting extends GraphCreature
{
	private boolean map[][];
	public void init(int n, boolean map[][])
	{
		int i,j;
		this.map = new boolean[n][n];
		for(i=0;i<n;i++)
			for(j=0;j<n;j++)
			{
				this.map[i][j]=map[i][j];
			}
	}
	public GraphCreaturePainting(int n, GraphCrossFunction c, GraphMutationFunction m) 
	{
		super(n, c, m);
	}
	double fit() 
	{
		int i,j;
		int[] colors=new int[n+1];
		boolean[] use=new boolean[n+1]; 
		for(i=0;i<n;i++)
			colors[i]=0;
		int num=0;		
		for(i=0;i<n;i++)
		{
			for(j=1;j<=num;j++)
				use[i]=true;
			for(j=0;j<i;j++)
				if (map[a[i]][a[j]]==true)
				{
					use[colors[j]]=false;
				}
			for(j=1;j<=num;j++)
			{
				if (colors[i]==0&&use[j]==true)
					colors[i]=j;
			}
			if (colors[i]==0)
			{
				num++;
				colors[i]=num;
			}
		}
		return n-num;
	}
	int[] doPaint()
	{
		int i,j;
		int[] colors=new int[n];
		boolean[] use=new boolean[n]; 
		for(i=0;i<n;i++)
			colors[i]=0;
		int num=0;		
		for(i=0;i<n;i++)
		{
			for(j=1;j<=num;j++)
				use[i]=true;
			for(j=0;j<i;j++)
				if (map[a[i]][a[j]]==true)
				{
					use[colors[j]]=false;
				}
			for(j=1;j<=num;j++)
			{
				if (colors[i]==0&&use[j]==true)
					colors[i]=j;
			}
			if (colors[j]==0)
			{
				num++;
				colors[j]=num;
			}
		}
		return colors;
	}
}