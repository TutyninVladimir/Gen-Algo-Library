package neurocreature;

public class SimpleNeuroCross extends NeuroCrossFunction
{
	void cross(NeuroCreature a, NeuroCreature b)
	{
		double olda[][][], newa[][][], oldb[][][], newb[][][];
		olda=a.getweight();
		oldb=b.getweight();
		newa=a.getweight();
		newb=b.getweight();
		
		int i,j,k,n=0;
		for(i=0;i<a.num_layers-1;i++)
		{
			n+=a.layer[i]*a.layer[i+1];
		}
		int q=(int)(Math.random()*n);
		int tmp=0;
		for(i=0;i<a.num_layers-1;i++)
			for(j=0;j<a.layer[i];j++)
				for(k=0;k<a.layer[i+1];k++)
				{
					if (tmp<=q)
					{
						newa[i][j][k]=oldb[i][j][k];
						newb[i][j][k]=olda[i][j][k];
					}
					else
					{
						newa[i][j][k]=olda[i][j][k];
						newb[i][j][k]=oldb[i][j][k];
					}
					tmp++;
				}
		a.generate();
		b.generate();
		a.setweight(newa);
		b.setweight(newb);
	}
}