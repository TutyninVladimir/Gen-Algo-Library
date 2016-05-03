package genetics.simplecreature;

public class SimpleCreatureExample extends SimpleCreature {
	private int x;

	void init(int x) {
		this.x = x;
	}

	public SimpleCreatureExample(int bytes, SimpleCrossFunction c, 
	SimpleMutationFunction m) {
		super(bytes, c, m);
	}

	double fit() {
		// TO DO: write function.
		return x;
	}
}