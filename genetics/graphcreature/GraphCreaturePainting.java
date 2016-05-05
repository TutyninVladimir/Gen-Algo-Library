package genetics.graphcreature;

/**
 * Класс GraphCreaturePainting - наследник класса GraphCreature, 
 * предназначенный для решения задачи раскраски графа. 
 * @author Tutynin Vladimir
 */
public class GraphCreaturePainting extends GraphCreature {
	private boolean map[][];
	
	/**
	 * Конструктор класса, идентичен конструктору класса GraphCreature.
	 * @param n Размер массива.
	 * @param c Объект, реализующий функцию скрещивания.
	 * @param m Объект, реализующий функцию мутации.
	 */
	public GraphCreaturePainting(int n, GraphCrossFunction c, 
	GraphMutationFunction m) {
		super(n, c, m);
	}

	/**
	 * Инициализация графа в виде матрицы смежности.
	 * @param n Размер массива.
	 * @param map Матрица смежности. 
	 * 1 - есть связь между вершинами, 0 - нет связи.
	 */
	public void init(int n, boolean map[][]) {
		this.map = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.map[i][j] = map[i][j];
			}
		}
	}

	/**
	 * Функция вычисления приспособленности, которая выражается как разность
	 * между размером массива и числом цветов.
	 * @return Значение приспособленности.
	 */
	double fit() {
		int[] colors = new int[n + 1];
		boolean[] use = new boolean[n + 1];
		int num;

		for (int i = 0; i < n; i++) {
			colors[i] = 0;
		}
		num = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= num; j++) {
				use[i] = true;
			}
			for (int j = 0; j < i; j++) {
				if (map[a[i]][a[j]] == true) {
					use[colors[j]] = false;
				}
			}
			for (int j = 1; j <= num; j++) {
				if (colors[i] == 0 && use[j] == true) {
					colors[i] = j;
				}
			}
			if (colors[i] == 0) {
				num++;
				colors[i] = num;
			}
		}
		return n - num;
	}

	/**
	 * Функция вычисления числа цветов, которыми раскрашивается граф при
	 * применении жадного алгоритма раскраски при заданном порядке обхода.
	 * @return Число цветов.
	 */
	public int getAns() {
		int[] colors = new int[n + 1];
		boolean[] use = new boolean[n + 1];
		int num;

		for (int i = 0; i < n; i++)
			colors[i] = 0;
		num = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= num; j++) {
				use[i] = true;
			}
			for (int j = 0; j < i; j++) {
				if (map[a[i]][a[j]] == true) {
					use[colors[j]] = false;
				}
			}
			for (int j = 1; j <= num; j++) {
				if (colors[i] == 0 && use[j] == true) {
					colors[i] = j;
				}
			}
			if (colors[i] == 0) {
				num++;
				colors[i] = num;
			}
		}
		return num;
	}

	/**
	 * Функция вычисления раскраски графа при
	 * применении жадного алгоритма раскраски при заданном порядке обхода.
	 * @return Способ раскраски.
	 */
	int[] doPaint() {
		int[] colors = new int[n];
		boolean[] use = new boolean[n];
		int num;
		
		for (int i = 0; i < n; i++) {
			colors[i] = 0;
		}
		num = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= num; j++) {
				use[i] = true;
			}
			for (int j = 0; j < i; j++) {
				if (map[a[i]][a[j]] == true) {
					use[colors[j]] = false;
				}
			}
			for (int j = 1; j <= num; j++) {
				if (colors[i] == 0 && use[j] == true) {
					colors[i] = j;
				}
			}
			if (colors[i] == 0) {
				num++;
				colors[i] = num;
			}
		}
		return colors;
	}
}