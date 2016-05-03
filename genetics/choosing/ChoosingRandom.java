package genetics.choosing;

import genetics.population.Choosing;
import genetics.population.Creature;

public class ChoosingRandom extends Choosing {
	final double eps=0.000001;
	
	public void crossing(double fraction, int n, int[] use, Creature[] cr) {
		double f;
		int[] a = new int[n];
		int tmp;
		int tmp2;	
		for (int i = 0; i < n; i++) {
			a[i] = i;
		}
		for (int i = n - 1; i >= 0; i--) {
			tmp = ((int) (Math.random() * (i + 1)));
			tmp2 = a[i];
			a[i] = a[tmp]; 
			a[tmp] = tmp2;
		}
		f = 0.0;
		for (int i = 0; i < n; i++) {
			use[i] = -1;
		}
		tmp = 0;
		while (((f + eps) < fraction) && (tmp<n-1)) {
			use[a[tmp]] = use[a[tmp+1]];
			use[a[tmp+1]] = use[a[tmp]];
			tmp=tmp+2;
			f += 2.0 / n;
		}
	}
}