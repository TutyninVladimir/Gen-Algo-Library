package genetics.neurocreature;

import genetics.population.Creature;

public abstract class NeuroCreature extends Creature {
	protected int num_layers;
	protected int layer[];
	protected double w[][][];
	protected NeuroCrossFunction c;
	protected NeuroMutationFunction m;

	public NeuroCreature(int n, int[] layers, NeuroCrossFunction c, 
	NeuroMutationFunction m) {
		if (n > 100000) {
			n = 100000;
		}
		if (n < 2) {
			n = 2;
		}
		this.num_layers = n;
		this.layer = new int[n];
		for (int i = 0; i < n; i++) {
			layer[i] = layers[i];
			if (layer[i] < 1) {
				layer[i] = 1;
			}
			if (layer[i] > 100000) {
				layer[i] = 100000;
			}
		}
		w = new double[num_layers - 1][][];
		for (int i = 0; i < num_layers - 1; i++) {
			w[i] = new double[layer[i]][];
			for (int j = 0; j < layer[i]; j++) {
				w[i][j] = new double[layer[i + 1]];
				for (int k = 0; k < layer[i + 1]; k++) {
					w[i][j][k] = 0.0;
				}
			}
		}
		this.c = c;
		this.m = m;
	}

	abstract double fit();

	public double fitness() {
		return this.fit();
	}

	public void copyData(Creature a) {
		((NeuroCreature) a).num_layers = this.num_layers;
		for (int i = 0; i < this.num_layers; i++) {
			((NeuroCreature) a).layer[i] = this.layer[i];
		}
		for (int i = 0; i < num_layers - 1; i++) {
			for (int j = 0; j < layer[i]; j++) {
				for (int k = 0; k < layer[i + 1]; k++) {
					((NeuroCreature) a).w[i][j][k] = this.w[i][j][k];
				}
			}
		}
	}

	public void generate() {
		int[] tmp = new int[num_layers];
		
		for (int i = 0; i < num_layers; i++) {
			tmp[i] = layer[i];
		}
		layer = tmp;
		w = new double[num_layers - 1][][];
		for (int i = 0; i < num_layers - 1; i++) {
			w[i] = new double[layer[i]][];
			for (int j = 0; j < layer[i]; j++) {
				w[i][j] = new double[layer[i + 1]];
				for (int k = 0; k < layer[i + 1]; k++) {
					w[i][j][k] = (double) Math.random();
				}
			}
		}
	}

	public void cross(Creature cr) {
		c.cross(this, (NeuroCreature) cr);
	}

	public void mutation() {
		m.mutate(this);
	}

	public void setweight(double weight[][][]) {
		for (int i = 0; i < num_layers - 1; i++) {
			for (int j = 0; j < layer[i]; j++) {
				for (int k = 0; k < layer[i + 1]; k++) {
					this.w[i][j][k] = weight[i][j][k];
				}
			}
		}
	}

	public double[][][] getweight() {
		double[][][] tmp = new double[num_layers][][];
		
		for (int i = 0; i < num_layers - 1; i++) {
			tmp[i] = new double[layer[i]][];
			for (int j = 0; j < layer[i]; j++) {
				tmp[i][j] = new double[layer[i + 1]];
				for (int k = 0; k < layer[i + 1]; k++) {
					tmp[i][j][k] = w[i][j][k];
				}
			}
		}
		return tmp;
	}

	public int getlength() {
		return this.num_layers;
	}

	public int[] getlayer() {
		int[] tmp = new int[num_layers];
		
		for (int i = 0; i < num_layers; i++) {
			tmp[i] = this.layer[i];
		}
		return tmp;
	}
}