package Genetics;

//import java.math.*;

class Population
{
	private int cnt;
	private double chooses;
	private double mutates;
	private Creature[] cr;
	private CreatureMachine cm;
	private Choosing ch;
	private Selecting sel;
	private Stopping st;
	private int[] a;
	private int turns;
	public int getTurns()
	{
		return turns;
	}
	public Population(int n, double chooses, double mutates, Creature c, Choosing ch, Selecting sel, Stopping st)
	{
		if (n<0)
			n=0;
		if (n>1048576)
			n=1048576;
		this.cnt = n;
		cm = new CreatureMachine(c);
		cr = new Creature[2*n+1];
		a = new int[2*n+1];
		int i;
		for(i=0;i<2*n;i++)
		{
			cr[i] = cm.makeCreature();
			cr[i].generate();
		}
		this.ch = ch;
		this.sel = sel;
		this.st = st;
		if (chooses<0.0) chooses=0.0;
		else if (chooses>1.0) chooses=1.0;
		if (mutates<0.0) mutates=0.0;
		else if (mutates>1.0) mutates=1.0;
		this.chooses = chooses;
		this.mutates = mutates;
	}
	private void copyTo(int a, int b)
	{
		cm.setCreature(cr[a]);
		cr[b] = cm.makeCreature();
	}
	private void mutations(int n1, int n2)
	{
		int[] use = new int[n2-n1];
		double f=0.0;
		int q;
		int tmp;
		int tries=0;
		while(f+0.000001<mutates)
		{
			q=(int)(Math.random()*(n2-n1));
			if (use[q]==0)
			{
				use[q]=1;
				cr[q+n1].mutation();
				f+=1.0/(n2-n1);
			}
			else
			{
				tries++;
				if (tries>10)
				{
					tries=0;
					tmp=(q+1)%(n2-n1);
					while(tmp!=q&&use[tmp]!=0)
						tmp=(tmp+1)%(n2-n1);
					if (tmp==q)
						f=2.0;
					else
					{
						use[tmp]=1;
						cr[tmp+n1].mutation();
						f+=1.0/(n2-n1);
					}
				}
			}
		}
	}
	public void run()
	{
		//tmp
		//System.out.printf("%d %.3f \n", this.cnt, this.chooses);
		turns=0;
		int n1=cnt;
		int n2=cnt;
		boolean flag=false;
		while (flag!=true)
		{
			/*for(int i=0;i<n1;i++)
			{
				System.out.print(cr[i].fitness());
				System.out.print(" ");
			}
			System.out.print("\n");*/
			for(int i=0;i<n1;i++)
				a[i]=0;
			ch.crossing(chooses, n1, a, cr);
			for(int i=0;i<n1;i++)
			{
				if (a[i]!=0&&a[i]!=i)
				{
					copyTo(i,n2);
					n2++;
					copyTo(a[i], n2);
					n2++;
					a[a[i]]=0;
					a[i]=0;
				}
			}
			for(int i=n1;i<n2;i+=2)
			{
				cr[i].cross(cr[i+1]);
			}
			if (n2>n1)
				mutations(n1,n2);
			for(int i=0;i<n2;i++)
				a[i]=0;
			sel.select(n2, n1, a, cr);
			for(int i=n2-1;i>=0;i--)
			{
				if (a[i]==0&&n2>n1)
				{
					if (i!=n2-1)
					{
						copyTo(n2-1,i);
					}
					n2--;
				}
			}
			n2=n1;
			turns++;
			flag=st.isEnding(n1, cr);
		}
	}
	public double getAnswer()
	{
		double x=cr[0].fitness();
		double tmp;
		for(int i=0;i<cnt;i++)
		{
			tmp=cr[i].fitness();
			if (tmp>x)
				x=tmp;
		}
		return x;
	}
	public void generateCreatures()
	{
		for(int i=0;i<cnt;i++)
		{
			cr[i].generate();
		}
	}
}

class CreatureMachine 
{
    private Creature cr;
    public CreatureMachine(Creature cr) 
    {
        this.cr = cr;
    }
    public void setCreature(Creature cr)
    {
    	this.cr = cr;
    }
    public Creature makeCreature() 
    {
        return (Creature) this.cr.clone();
    }
}

abstract class Creature implements Cloneable 
{	
    @Override
    public Creature clone()
    {
    	Creature copy = null;
		try 
		{
			copy = (Creature) super.clone();
		} catch (CloneNotSupportedException e) 
		{
			//e.printStackTrace();
		}
    	return copy;
	}
    abstract double fitness();
    abstract void cross(Creature cr);
    abstract void mutation();
    abstract void generate();
}

abstract class Choosing
{
	abstract void crossing(double f, int n, int[] use, Creature[] cr);
}

abstract class Selecting
{
	abstract void select(int n1, int n2, int[] use, Creature[] cr);
}

abstract class Stopping
{
	abstract boolean isEnding(int n, Creature[] cr);
}

class MyStopping extends Stopping
{
	int turns;
	int turn;
	double lastMax;
	public boolean isEnding(int n, Creature[] cr)
	{
		this.turn++;
		double thisMax=cr[0].fitness();
		double tmp;
		for(int i=1;i<n;i++)
		{
			tmp=cr[i].fitness();
			if (tmp>thisMax)
				thisMax=tmp;
		}
		if (thisMax!=lastMax)
		{
			lastMax=thisMax;
			this.turn=0;
		}
		if (this.turn>=this.turns)
			return true;
		else
			return false;		
	}
	public MyStopping(int turns)
	{
		if (turns<1)
			turns=1;
		this.turns=turns;
		this.turn=0;
		this.lastMax=0;
	}
}

class MyCreature extends SimpleCreature
{
	public MyCreature(int bytes, SimpleCrossFunction c, SimpleMutationFunction m) 
	{
		super(bytes, c, m);
	}
	double fit(int x)
	{
		//return x;
		return (1+Math.log10(2*x+1)/(2+Math.sin(x)));
	}
}

class PopulationExample 
{
	public static void main(String[] args)
    {
		GraphCrossFunction scf = new ExtendedGraphCross();
		GraphMutationFunction smf = new OneChangeMutation();
		PaintingGraphCreature cr = new PaintingGraphCreature(10, scf, smf);
		boolean map[][] = new boolean[10][10];
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				if (i!=j) map[i][j]=true;
				else map[i][j]=false;
			}
		}
		cr.init(10, map);
		Choosing rc = new RandomChoosing();
		Selecting ms = new MaxSelecting();
		Stopping st = new IterationsStopping(100);
		Population p = new Population(1000, 0.25, 0.25, cr, rc, ms, st);		
		p.generateCreatures();
		p.run();
		double x=p.getAnswer();
		System.out.printf("%f\n",cr.getlength()-x);
    }
}
/*
class PopulationExample
{
	public static void main(String[] args)
    {
		SimpleCrossFunction scf = new OnePointCross();
		SimpleMutationFunction smf = new OneBitMutation();
		Creature cr = new MyCreature(10, scf, smf);
		long start = System.nanoTime();
		TestModule tst = new TestModule();
		Population p = tst.startTest(cr, 0.001);
		long end = System.nanoTime();
		long traceTime = end-start;
		double x=0.0;
		for(int i=0;i<25;i++)
		{			
			p.generateCreatures();
			p.run();
			x+=p.getAnswer();
		}
		System.out.printf("population avg answer: %5.4f\n", x/25);
		System.out.printf("module answer: %5.4f\n",tst.getanswer());
		System.out.printf("time: %d\n",traceTime/1000);

    }
}*/

/*class PopulationExample 
{
	public static void main(String[] args)
    {
		SimpleCrossFunction scf = new OnePointCross();
		SimpleMutationFunction smf = new OneBitMutation();
		Creature cr = new MyCreature(10, scf, smf);
		Choosing rc = new RandomChoosing();
		Selecting ms = new MaxSelecting();
		//Stopping st = new IterationsStopping(100);
		Stopping st = new MyStopping(10);
		Population p = new Population(25000, 0.25, 0.25, cr, rc, ms, st);
		long start = System.nanoTime();
		double x=0.0;
		for(int i=0;i<1;i++)
		{			
			p.generateCreatures();
			p.run();
			x+=p.getAnswer();
		}
		long end = System.nanoTime();
		long traceTime = end-start;
		System.out.printf("%5.4f %d\n", x/1,traceTime/(1000*1));
		start = System.nanoTime();
		TestModule tst = new TestModule();
		Population p2=tst.startTest(cr,0.000001);
		System.out.printf(">>>%5.4f \n", tst.getanswer());
		p2.generateCreatures();
		p2.run();
		x=p2.getAnswer();
		end = System.nanoTime();
		traceTime = end-start;
		x=0.0;
		for(int i=0;i<10;i++)
		{			
			p2.generateCreatures();
			p2.run();
			x+=p2.getAnswer();
			System.out.printf("%5.4f \n", p2.getAnswer());
		}
		System.out.printf("%5.4f %d\n", x/50,traceTime/(1000));
    }
}*/

/*
Stopping st1 = new IterationsStopping(10);
Stopping st2 = new MaxNotChangedStopping(5);
Population p1 = new Population(100, 0.25, 0.25, cr, rc, ms, st1);
Population p2 = new Population(100, 0.25, 0.25, cr, rc, ms, st2);
double sum1=0.0;
double sum2=0.0;
for(int i=0;i<=10;i++)
{
	long start = System.nanoTime();
	p1.generateCreatures();
	p1.run();
	double x=p1.getAnswer();
	if (i!=0) 
	{
		sum1+=x;
		System.out.printf("%4.2f",x);
		long end = System.nanoTime();
		long traceTime = end-start;
		System.out.print(" ");
		System.out.print(traceTime/1000000);
		System.out.print("\n");
	}
}
System.out.print("\n");
for(int i=0;i<10;i++)
{
	long start = System.nanoTime();
	p2.generateCreatures();
	p2.run();
	double x=p2.getAnswer();
	sum2+=x;
	System.out.printf("%4.2f",x);
	long end = System.nanoTime();
	long traceTime = end-start;
	System.out.print(" ");
	System.out.print(traceTime/1000000);
	System.out.print("\n");
}
System.out.print("\n");
System.out.printf("%5.3f",sum1/10.0);
System.out.print(" ");
System.out.printf("%5.3f",sum2/10.0);*/
