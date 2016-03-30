package genetics.population;

public abstract class Creature implements Cloneable 
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
    public abstract double fitness();
    public abstract void copyData(Creature cr);
    public abstract void cross(Creature cr);
    public abstract void mutation();
    public abstract void generate();
}