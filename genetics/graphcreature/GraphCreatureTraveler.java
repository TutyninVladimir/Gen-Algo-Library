package genetics.graphcreature;

public class GraphCreatureTraveler extends GraphCreature
{
	private double map[][];
	private double mx;
	public void init(int n, double map[][])
	{
		int i,j;
		this.map = new double[n][n];
		mx=0.0;
		double tmp;
		for(i=0;i<n;i++)
		{
			tmp=0.0;
			for(j=0;j<n;j++)
			{
				this.map[i][j]=map[i][j];
				if (map[i][j]>tmp)
					tmp=map[i][j];
			}
			mx+=tmp;
		}
	}
	public GraphCreatureTraveler(int n, GraphCrossFunction c, GraphMutationFunction m) 
	{
		super(n, c, m);
	}
	double fit() 
	{
		int i;
		double sum=0.0;
		for(i=0;i<n-1;i++)
		{
			sum+=map[a[i]][a[i+1]];
		}
		sum+=map[a[n-1]][a[0]];
		return mx-sum;
	}
}