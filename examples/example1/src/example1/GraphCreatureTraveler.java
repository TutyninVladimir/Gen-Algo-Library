package example1;

/**
 * Класс GraphCreatureTraveler - наследник класса GraphCreature, 
 * предназначенный для решения задачи коммивояжёра. 
 * @author Tutynin Vladimir
 */
public class GraphCreatureTraveler extends GraphCreature {
	private double map[][];
	private double mx;
	
	/**
	 * Конструктор класса, идентичен конструктору класса GraphCreature.
	 * @param n Размер массива.
	 * @param c Объект, реализующий функцию скрещивания.
	 * @param m Объект, реализующий функцию мутации.
	 */
	public GraphCreatureTraveler(int n, GraphCrossFunction c, 
	GraphMutationFunction m) {
		super(n, c, m);
	}
	
	/**
	 * Инициализация графа.
	 * @param n Размер массива.
	 * @param map Матрица расстояний между вершинами.
	 */
	public void init(int n, double map[][]) {
		double tmp;
		
		this.map = new double[n][n];
		mx = 0.0;
		for (int i = 0; i < n; i++) {
			tmp = 0.0;
			for (int j = 0; j < n; j++) {
				this.map[i][j] = map[i][j];
				if (map[i][j] > tmp) {
					tmp = map[i][j];
				}
			}
			mx += tmp;
		}
	}

	/**
	 * Функция вычисления приспособленности, которая выражается как разность
	 * между максимальными значениями строк и расстоянием данного маршрута.
	 * @return Значение приспособленности.
	 */
	double fit() {
		int i;
		double sum = 0.0;
		
		for (i = 0; i < n - 1; i++) {
			sum += map[a[i]][a[i + 1]];
		}
		sum += map[a[n - 1]][a[0]];
		return mx - sum;
	}

	/**
	 * Функция вычисления расстояния по маршруту данной особи.
	 * @return Расстояние по данному маршруту.
	 */
	public double getAns() {
		int i;
		double sum = 0.0;
		
		for (i = 0; i < n - 1; i++) {
			sum += map[a[i]][a[i + 1]];
		}
		sum += map[a[n - 1]][a[0]];
		return sum;
	}

	/**
	 * Функция получения маршрута особи.
	 * @return Маршрут особи.
	 */
	public int[] doPath() {
		return a;
	}
}