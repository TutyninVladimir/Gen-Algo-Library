package Genetics;

abstract class SimpleCreature extends Creature
{
	private int bytes;
	private int q;
	private SimpleCrossFunction c;
	private SimpleMutationFunction m;
	abstract double fit(int x);
	
	public double fitness()
	{
		return this.fit(q);
	}
	public void generate()
	{
		int tmp=1;
		for(int i=1;i<=this.bytes;i++)
			tmp*=2;
		this.q=(int)(Math.random()*tmp);
	}
	public void cross(Creature cr)
	{
		c.cross(this, (SimpleCreature) cr);
	}
	public void mutation()
	{
		m.mutate(this);
	}
	public void set(int q)
	{
		int tmp=1;
		for(int i=1;i<=this.bytes;i++)
			tmp*=2;
		if (q>tmp-1)
			q=tmp-1;
		if (q<0)
			q=0;
		this.q=q;
	}
	public int get()
	{
		return this.q;
	}
	public int getbytes()
	{
		return this.bytes;
	}
	public SimpleCreature(int bytes, SimpleCrossFunction c, SimpleMutationFunction m)
	{
		if (bytes>31)
			bytes=31;
		if (bytes<1)
			bytes=1;
		this.bytes = bytes;
		this.c = c;
		this.m = m;
	}
}

abstract class SimpleCrossFunction
{
	abstract void cross(SimpleCreature a, SimpleCreature b);
}

abstract class SimpleMutationFunction
{
	abstract void mutate(SimpleCreature a);
}

class OnePointCross extends SimpleCrossFunction
{
	void cross(SimpleCreature a, SimpleCreature b)
	{
		int m=a.getbytes();
		int q=1+((int)Math.random()*(m-1));
		int olda,oldb,newa,newb;
		olda=a.get();
		oldb=b.get();
		newa=0;
		newb=0;
		int x=1;
		for(int i=1;i<=q;i++)
		{
			newa+=(olda%2)*x;
			olda=olda/2;
			newb+=(oldb%2)*x;
			oldb=oldb/2;
			x*=2;
		}
		for(int i=q+1;i<=m;i++)
		{
			newa+=(oldb%2)*x;
			oldb=oldb/2;
			newb+=(olda%2)*x;
			olda=olda/2;
			x*=2;
		}
		a.set(newa);
		b.set(newb);
	}
}

class OneBitMutation extends SimpleMutationFunction
{
	void mutate(SimpleCreature a)
	{
		int m=a.getbytes();
		int q=(int)(Math.random()*m);
		int olda=a.get();
		int newa=0;
		int x=1;
		for(int i=0;i<q;i++)
			x=x*2;
		newa=olda^x;
		a.set(newa);
	}
}

class ExampleSimpleCreature extends SimpleCreature
{
	public ExampleSimpleCreature(int bytes, SimpleCrossFunction c, SimpleMutationFunction m) 
	{
		super(bytes, c, m);
	}
	double fit(int x)
	{
		//TO DO: write function.
		return x;
	}
}