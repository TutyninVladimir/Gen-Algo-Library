package simplecreature;

public class ExampleSimpleCreature extends SimpleCreature
{
	int x;
	void init(int x)
	{
		this.x=x;
	}
	public ExampleSimpleCreature(int bytes, SimpleCrossFunction c, SimpleMutationFunction m) 
	{
		super(bytes, c, m);
	}
	double fit()
	{
		//TO DO: write function.
		return x;
	}
}