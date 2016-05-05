package genetics.neurocreature;

/**
 * Класс NeuroMutationOneWeight - наследник класса NeuroMutationFunction, 
 * реализующий функцию мутации одного веса для особи 
 * в виде матрицы весов.
 * @author Tutynin Vladimir
 */
public class NeuroMutationOneWeight extends NeuroMutationFunction {
	/**
	 * Реализация стратегии мутации одного веса для особи с генотипом 
	 * в виде матрицы весов.
	 * @param a Мутирующая особь.
	 */
	void mutate(NeuroCreature a) {
		int l, n = 0;
		double tmp2;
		double tmp3;
		int rnd;
		int q;
		int tmp;
		double[][][] olda;
		
		for (int i = 0; i < a.num_layers - 1; i++) {
			n += a.layer[i] * a.layer[i + 1];
		}
		q = (int) (Math.random() * n);
		tmp = 0;
		olda = a.getweight();
		for (int i = 0; i < a.num_layers - 1; i++) {
			for (int j = 0; j < a.layer[i]; j++) {
				for (int k = 0; k < a.layer[i + 1]; k++) {
					if (tmp == q) {
						tmp2 = olda[i][j][k];
						tmp3 = 1.0;
						rnd = (int) (Math.random() * 20);
						for (l = 1; l <= rnd; l++) {
							tmp2 *= 2;
							tmp3 /= 2;
						}
						if (((int) tmp2) % 2 == 0) {
							olda[i][j][k] += tmp3;
						} else {
							olda[i][j][k] -= tmp3;
						}
						if (olda[i][j][k] < 0.0) {
							olda[i][j][k] = 0.0;
						}
						if (olda[i][j][k] > 1.0) {
							olda[i][j][k] = 1.0;
						}
					}
					tmp++;
				}
			}
		}
		a.setweight(olda);
	}
}