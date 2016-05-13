package example1;

import genetics.population.Creature;

/**
 * Класс GraphCreature - наследник класса Creature, реализующий операции для
 * работы с осоьбю, генотип которой представлен в виде 
 * массива неповторяющихся чисел от 1 до n для решения задач на графах.
 * @author Tutynin Vladimir
 */
public abstract class GraphCreature extends Creature {
	protected int n;
	protected int a[];
	protected GraphCrossFunction c;
	protected GraphMutationFunction m;
	
	/**
	 * Конструктор класса. При создании особи указывается размер
	 * массива, а также объекты, реализующие стратегии скрещивания и мутации.
	 * @param n Размер массива.
	 * @param c Объект, реализующий функцию скрещивания.
	 * @param m Объект, реализующий функцию мутации.
	 */
	public GraphCreature(int n, GraphCrossFunction c, 
	GraphMutationFunction m) {
		if (n > 100000) {
			n = 100000;
		}
		if (n < 2) {
			n = 2;
		}
		this.n = n;
		a = new int[n];
		for(int i = 0; i < n; i++) {
			a[i] = i;
		}
		this.c = c;
		this.m = m;
	}
	
	/**
	 * Абстрактная функция вычисления значения приспособленности. Зависит от 
	 * решаемой задачи. 
	 * @return Значение функции приспособленности.
	 */
	abstract double fit();
	
	/**
	 * Функция вычисления значения приспособленности, используемая объектами 
	 * класса Population.
	 * @return Значение функции приспособленности.
	 */
	public double fitness() {
		return this.fit();
	}
	
	/**
	 * Функция копирования значения генотипа - массива - из одной особи в другую.
	 * @param a Особь, в которую копируются параметры.
	 */
	public void copyData(Creature a) {
		((GraphCreature) a).n = this.n;
		for (int i = 0; i < n; i++) {
			((GraphCreature) a).a[i] = this.a[i];
		}
	}
	
	/**
	 * Функция исправления массива с некорректными значениями.
	 */
	public void regenerate() {
		int i;
		int[] tmp = new int[n];
		int p2;
		
		for (i = 0; i < n; i++) {
			tmp[i] = 0;
		}
		for (i = 0; i < n; i++) {
			if (tmp[a[i]] == 0) { 
				tmp[a[i]] = 1;
			} else {
				a[i] = -1;
			}
		}
		p2 = 0;
		for (i = 0; i < n; i++) {
			if (a[i] == -1) {
				while (tmp[p2] != 0) {
					p2++;
				}
				tmp[p2] = 1;
				a[i] = p2;
			}
		}
	}
	
	/**
	 * Функция генерации произвольной перестановки элементов от 1 до n. 
	 */
	public void generate() {
		int tmp;
		int tmp2;
		
		a = new int[n];
		for (int i = 0; i < n; i++) {
			this.a[i] = i;
		}
		for (int i = n-1; i >= 0; i--) {
			tmp = ((int) (Math.random() * (i+1)));
			tmp2 = this.a[i];
			this.a[i] = this.a[tmp]; 
			this.a[tmp] = tmp2;
		}
	}
	
	/**
	 * Функция скрещивания особи.
	 */
	public void cross(Creature cr) {
		c.cross(this, (GraphCreature) cr);
	}
	
	/**
	 * Функция мутации особи.
	 */
	public void mutation() {
		m.mutate(this);
	}
	
	/**
	 * Функция установки значения массива.
	 * @param a Устанавливаемое значение массива.
	 */
	public void set(int a[]) {
		for (int i = 0; i < n; i++) {
			this.a[i] = a[i];
		}
		regenerate();
	}
	
	/**
	 * Функция получения генотипа особи.
	 * @return Генотип особи.
	 */
	public int[] get() {
		int[] tmp = new int[n];
		for (int i = 0; i < n; i++) {
			tmp[i] = this.a[i];
		}
		return tmp;
	}
	
	/**
	 * Функция получения длины массива.
	 * @return Длина массива.
	 */
	public int getlength() {
		return this.n;
	}
}