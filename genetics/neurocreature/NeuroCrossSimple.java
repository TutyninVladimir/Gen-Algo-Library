package genetics.neurocreature;

public class NeuroCrossSimple extends NeuroCrossFunction {
	void cross(NeuroCreature a, NeuroCreature b) {
		double olda[][][];
		double newa[][][];
		double oldb[][][];
		double newb[][][];
		int n = 0;
		int q;
		int tmp = 0;
		
		olda = a.getweight();
		oldb = b.getweight();
		newa = a.getweight();
		newb = b.getweight();

		q = (int) (Math.random() * n);
		n = 0;
		for (int i = 0; i < a.num_layers - 1; i++) {
			n += a.layer[i] * a.layer[i + 1];
		}
		for (int i = 0; i < a.num_layers - 1; i++) {
			for (int j = 0; j < a.layer[i]; j++) {
				for (int k = 0; k < a.layer[i + 1]; k++) {
					if (tmp <= q) {
						newa[i][j][k] = oldb[i][j][k];
						newb[i][j][k] = olda[i][j][k];
					} else {
						newa[i][j][k] = olda[i][j][k];
						newb[i][j][k] = oldb[i][j][k];
					}
					tmp++;
				}
			}
		}
		a.generate();
		b.generate();
		a.setweight(newa);
		b.setweight(newb);
	}
}