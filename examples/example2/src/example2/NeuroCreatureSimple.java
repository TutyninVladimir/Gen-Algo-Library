package example2;

/**
 * Класс NeuroCreatureSimple - наследник класса NeuroCreature, 
 * предназначенный для решения задачи настройки нейронной сети. 
 * @author Tutynin Vladimir
 */
public class NeuroCreatureSimple extends NeuroCreature {
	private int examples;
	private double in[][];
	private double out[][];

	/**
	 * Конструктор класса, идентичен конструктору класса NeuroCreature.
	 * @param n Число слоёв.
	 * @param layers Число нейронов каждого слоя.
	 * @param c Объект, реализующий функцию скрещивания.
	 * @param m Объект, реализующий функцию мутации.
	 */
	public NeuroCreatureSimple(int n, int layers[], NeuroCrossFunction c, 
	NeuroMutationFunction m) {
		super(n, layers, c, m);
	}

	/**
	 * Инициализация обучающей выборки.
	 * @param n Количество обучающих примеров.
	 * @param inputs Входные данные обучающих примеров.
	 * @param outputs Выходные данные обучающих примеров.
	 */
	public void init(int n, double inputs[][], double outputs[][]) {
		examples = n;
		in = new double[n][this.layer[0]];
		out = new double[n][this.layer[num_layers - 1]];
		// Число входов равняется числу нейронов первого слоя, число выходов -
		// последнего
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < this.layer[0]; j++) {
				in[i][j] = inputs[i][j];
			}
			for (int j = 0; j < this.layer[num_layers - 1]; j++) {
				out[i][j] = outputs[i][j];
			}
		}
	}

	/**
	 * Функция вычисления приспособленности, которая выражается как разность
	 * между числом нейронов и суммароной ошибкой, вычисленной для всех примеров.
	 * @return Значение приспособленности.
	 */
	double fit() {
		double neuro[][] = new double[this.num_layers][];
		double err = 0.0;
		double l_err = 0.0;
		
		for (int i = 0; i < num_layers; i++) {
			neuro[i] = new double[this.layer[i]];
		}
		for (int c = 0; c < examples; c++) {
			for (int i = 0; i < layer[0]; i++) {
				neuro[0][i] = in[c][i];
			}
			for (int i = 1; i < num_layers; i++) {
				for (int j = 0; j < layer[i]; j++) {
					neuro[i][j] = 0.0;
					for (int k = 0; k < layer[i - 1]; k++) {
						neuro[i][j] += neuro[i - 1][k] * w[i - 1][k][j];
					}
					neuro[i][j] /= layer[i - 1];
				}
			}
			l_err = 0.0;
			for (int i = 0; i < layer[num_layers - 1]; i++) {
				l_err += Math.abs(neuro[num_layers - 1][i] - out[c][i]);
			}
			err += l_err;
		}
		return layer[num_layers - 1] * examples - err;
	}

	/**
	 * Функция получения ответа для заданных входных данных.
	 * @param input Входной пример.
	 * @return Ответ для данного примера.
	 */
	public double[] solve(double input[]) {
		double neuro[][] = new double[this.num_layers][];
		
		for (int i = 0; i < num_layers; i++) {
			neuro[i] = new double[this.layer[i]];
		}
		for (int i = 0; i < layer[0]; i++) {
			neuro[0][i] = input[i];
		}
		for (int i = 1; i < num_layers; i++) {
			for (int j = 0; j < layer[i]; j++) {
				neuro[i][j] = 0.0;
				for (int k = 0; k < layer[i - 1]; k++) {
					neuro[i][j] += neuro[i - 1][k] * w[i - 1][k][j];
				}
				neuro[i][j] /= layer[i - 1];
			}
		}
		return neuro[num_layers - 1];
	}
}