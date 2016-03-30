package genetics.backpackcreature;

public class BackpackCrossSimple extends BackpackCrossFunction
{
	void cross(BackpackCreature a, BackpackCreature b)
	{
		int n=a.getnum_items();
		int olda[], newa[], oldb[], newb[];
		olda=a.getitems();
		oldb=b.getitems();
		newa=new int[n];
		newb=new int[n];
		int i;
		int q=(int)(Math.random()*n);
		for(i=0;i<n;i++)
		{
			if (i<=q)
			{
				newa[i]=oldb[i];
				newb[i]=olda[i];
			}
			else
			{
				newa[i]=olda[i];
				newb[i]=oldb[i];
			}
		}
		a.regenerate();
		b.regenerate();
		a.setitems(newa);
		b.setitems(newb);
	}
}