package genetics.population;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import genetics.choosing.ChoosingRandom;
import genetics.graphcreature.GraphCreatureTraveler;
import genetics.graphcreature.GraphCrossExtended;
import genetics.graphcreature.GraphCrossFunction;
import genetics.graphcreature.GraphMutationFunction;
import genetics.graphcreature.GraphMutationOneChange;
import genetics.selecting.SelectingMax;
import genetics.stopping.StoppingMaxNotChanged;

public class Population
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
		//Copy data from a to b
		cr[a].copyData(cr[b]);
		//cm.setCreature(cr[a]);
		//cr[b] = cm.makeCreature();
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
	public Creature getAnswerCreature()
	{
		double x=cr[0].fitness();
		int q=0;
		double tmp;
		for(int i=0;i<cnt;i++)
		{
			tmp=cr[i].fitness();
			if (tmp>x)
			{
				x=tmp;
				q=i;
			}
		}
		return cr[q];
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

/*
class PopulationExample 
{
	public static void main(String[] args)
    {
		BackpackCrossFunction scf = new SimpleBackpackCross();
		BackpackMutationFunction smf = new OneItemMutation();
		SimpleBackpackCreature cr = new SimpleBackpackCreature(20, 1, scf, smf);
		int weights[] = new int[20];
		int costs[] = new int[20];
		File file = new File("input.txt");
		try 
		{
			Scanner scan = new Scanner(file);
			for(int i=0;i<20;i++)
				weights[i] = scan.nextInt();
			for(int i=0;i<20;i++)
				costs[i] = scan.nextInt();
			scan.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		cr.init(15, weights, costs);
		Choosing rc = new RandomChoosing();
		Selecting ms = new MaxSelecting();
		Stopping st = new MaxNotChangedStopping(25);
		Population p = new Population(2500, 0.25, 0.25, cr, rc, ms, st);		
		p.generateCreatures();
		p.run();
		double x=p.getAnswer();
		System.out.printf("%f\n", x);
		SimpleBackpackCreature crr=(SimpleBackpackCreature) p.getAnswerCreature();
		int[] answer = new int[20];
		answer = crr.getanswer();
		for (int i=0;i<20;i++)
			System.out.printf("%d", answer[i]);
		System.out.printf("\n%d", p.getTurns());
		//System.out.printf("%f", (inputs[0][0]*inputs[0][0]+inputs[0][1]+inputs[0][2])/3.0);
		//System.out.printf("\n%f\n",cr.getlength()-x);
    }
}
*/
/*class PopulationExample 
{
	public static void main(String[] args)
    {
		int[] layers={3,3,3,1};
		NeuroCrossFunction scf = new SimpleNeuroCross();
		NeuroMutationFunction smf = new OneWeightMutation();
		SimpleNeuroCreature cr = new SimpleNeuroCreature(4, layers, scf, smf);
		double[][] inputs= new double[100][3];
		double[][] outputs= new double[100][1];
		for(int i=0;i<100;i++)
		{
			inputs[i][0]=Math.random();
			inputs[i][1]=Math.random();
			inputs[i][2]=Math.random();
			outputs[i][0]=(inputs[i][0]*inputs[i][0]+inputs[i][1]+inputs[i][2])/3.0;
		}
		inputs[0][0]=0.0;
		inputs[0][1]=0.0;
		inputs[0][2]=0.0;
		outputs[0][0]=0.0;
		inputs[99][0]=1.0;
		inputs[99][1]=1.0;
		inputs[99][2]=1.0;
		outputs[99][0]=1.0;
		cr.init(100, inputs, outputs);
		Choosing rc = new RandomChoosing();
		Selecting ms = new MaxSelecting();
		Stopping st = new MaxNotChangedStopping(25);
		Population p = new Population(2500, 0.25, 0.25, cr, rc, ms, st);		
		p.generateCreatures();
		p.run();
		double x=p.getAnswer();
		System.out.printf("%f\n", x);
		SimpleNeuroCreature crr=(SimpleNeuroCreature) p.getAnswerCreature();
		double[] answer = new double[1];
		inputs[0][0]=0.5;
		inputs[0][1]=0.65;
		inputs[0][2]=0.6;
		answer = crr.solve(inputs[0]);
		System.out.printf("%f\n", answer[0]);
		System.out.printf("%f", (inputs[0][0]*inputs[0][0]+inputs[0][1]+inputs[0][2])/3.0);
		//System.out.printf("\n%f\n",cr.getlength()-x);
    }
}*/


class PopulationExample 
{
	public static void main(String[] args)
    {
		GraphCrossFunction scf = new GraphCrossExtended();
		GraphMutationFunction smf = new GraphMutationOneChange();
		GraphCreatureTraveler cr = new GraphCreatureTraveler(10, scf, smf);
		double map[][] = new double[10][10];
		File file = new File("input1.txt");
		try 
		{
			//int tmp;
			Scanner scan = new Scanner(file);
			for(int i=0;i<10;i++)
			{
				for(int j=0;j<10;j++)
				{
					map[i][j] = scan.nextDouble();
				}
			}
			scan.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		cr.init(10, map);
		Choosing rc = new ChoosingRandom();
		Selecting ms = new SelectingMax();
		Stopping st = new StoppingMaxNotChanged(25);
		Population p = new Population(10000, 0.25, 0.25, cr, rc, ms, st);		
		p.generateCreatures();
		p.run();
		//double x=p.getAnswer();
		GraphCreatureTraveler crr=(GraphCreatureTraveler) p.getAnswerCreature();
		int[] a = crr.get();
		for(int i=0;i<crr.getlength();i++)
		{
			System.out.printf("%d ", a[i]);
		}
		//System.out.printf("\n%f\n",cr.getlength()-x);
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