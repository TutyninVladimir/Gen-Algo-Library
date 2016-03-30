package genetics.graphcreature;

public class GraphCrossExtended extends GraphCrossFunction
{
	void cross(GraphCreature a, GraphCreature b)
	{
		int tmp,i,n;
		n=a.getlength();
		int q1=1+((int)Math.random()*(a.getlength()-1));
		int q2=1+((int)Math.random()*(a.getlength()-1));
		if (q1==q2) q2++;
		if (q1>q2)
		{
			tmp=q1;
			q1=q2;
			q2=tmp;
		}
		int olda[], newa[], oldb[], newb[];
		olda=a.get();
		oldb=b.get();
		newa = new int[n];
		newb = new int[n];
		int p1=q2;
		for(i=0;i<q1;i++)
		{
			newa[p1]=oldb[i];
			newb[p1]=olda[i];
			p1++;
			if (p1>=n) p1-=n;
			if (p1==q1) p1=q2;
		}
		for(i=q1;i<q2;i++)
		{
			newa[i]=olda[i];
			newb[i]=oldb[i];
		}
		for(i=q2;i<n;i++)
		{
			newa[p1]=oldb[i];
			newb[p1]=olda[i];
			p1++;
			if (p1>=n) p1=0;
			if (p1==q1) p1=q2;
		}
		int[] cnt=new int[n];
		for(i=0;i<n;i++)
		{
			cnt[i]=0;
		}
		p1=q1;
		for(i=0;i<q1;i++)
		{
			if (cnt[newa[i]]==0) cnt[newa[i]]=1;
			else 
			{
				newa[i]=oldb[p1];
				p1++;
			}
		}
		for(i=q2;i<n;i++)
		{
			if (cnt[newa[i]]==0) cnt[newa[i]]=1;
			else 
			{
				newa[i]=oldb[p1];
				p1++;
			}
		}
		for(i=0;i<n;i++)
		{
			cnt[i]=0;
		}
		p1=q1;
		for(i=0;i<q1;i++)
		{
			if (cnt[newb[i]]==0) cnt[newb[i]]=1;
			else 
			{
				newb[i]=olda[p1];
				p1++;
			}
		}
		for(i=q2;i<n;i++)
		{
			if (cnt[newb[i]]==0) cnt[newb[i]]=1;
			else 
			{
				newb[i]=olda[p1];
				p1++;
			}
		}
		a.set(newa);
		b.set(newb);
		a.regenerate();
		b.regenerate();
	}
}