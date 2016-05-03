package genetics.selecting;

import java.util.Arrays;
import java.util.Comparator;
import genetics.population.Creature;
import genetics.population.Selecting;

public class SelectingMax extends Selecting {
	class Record {
		int ind;
		double value;
	}

	public void select(int n1, int n2, int[] use, Creature[] cr) {
		Record[] a = new Record[n1];
		for (int i = 0; i < n1; i++) {
			use[i] = 0;
			a[i] = new Record();
			a[i].ind = i;
			a[i].value = cr[i].fitness();
		}
		Arrays.sort(a, new Comparator<Record>() {
			public int compare(Record o1, Record o2) {
				if (o1.value > o2.value)
					return -1;
				else if (o1.value < o2.value)
					return 1;
				else
					return 0;
			}
		});
		for (int i = 0; i < n2; i++) {
			use[a[i].ind] = 1;
		}
	}
}
