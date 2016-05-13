package example3;

import genetics.population.Creature;

/**
 * Класс BackpackCreature - наследник класса Creature, реализующий операции для
 * работы с осоьбю, генотип которой представлен в виде набора предметов для 
 * решения задачи о ранце.
 * @author Tutynin Vladimir
 */
public abstract class BackpackCreature extends Creature {
	protected int num_items;
	protected int items[];
	protected int num_of_use;
	protected BackpackCrossFunction c;
	protected BackpackMutationFunction m;
	
	/**
	 * Конструктор класса. При создании особи указывается размер
	 * массива, количество предметов одного типа, которые допускается
	 * использовать, а также объекты, реализующие 
	 * стратегии скрещивания и мутации.
	 * @param n Размер массива.
	 * @param num_of_use Максимальное количество предметов одного типа, которые
	 * можно взять в рюкзак.
	 * @param c Объект, реализующий функцию скрещивания.
	 * @param m Объект, реализующий функцию мутации.
	 */
	public BackpackCreature(int n, int num_of_use,
	BackpackCrossFunction c, BackpackMutationFunction m) {
		if (n > 100000) {
			n = 100000;
		}
		if (n < 2) {
			n = 2;
		}
		this.num_items = n;
		items = new int[num_items];
		for(int i = 0; i < num_items - 1; i++) {
			items[i] = 0;
		}
		if (num_of_use < 1) {
			num_of_use = 1;
		}
		if (num_of_use > 100000) {
			num_of_use = 100000;
		}
		this.num_of_use = num_of_use;
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
	public double fitness()	{
		return this.fit();
	}
	
	/**
	 * Функция копирования значения генотипа - массива - из одной особи в другую.
	 * @param a Особь, в которую копируются параметры.
	 */
	public void copyData(Creature a) {
		((BackpackCreature) a).num_items = this.num_items;
		((BackpackCreature) a).num_of_use = this.num_of_use;
		for (int i = 0; i < this.num_items; i++) {
			((BackpackCreature) a).items[i] = this.items[i];
		}
	}
	
	/**
	 * Функция исправления массива с некорректными значениями.
	 */
	public void regenerate() {
		for (int i=0; i<num_items; i++) {
			items[i] = items[i] % (num_of_use + 1);
		}
	}
	
	/**
	 * Функция генерации произвольного набора рюкзака. 
	 */
	public void generate() {	
		items = new int[num_items];
		
		for (int i = 0; i < num_items; i++)
		{
			items[i] = (int) (Math.random() * 2 * num_of_use);
			items[i] = items[i] % (num_of_use + 1);
		}
	}
	
	/**
	 * Функция скрещивания особи.
	 */
	public void cross(Creature cr) {
		c.cross(this, (BackpackCreature) cr);
	}
	
	/**
	 * Функция мутации особи.
	 */
	public void mutation() {
		m.mutate(this);
	}
	
	/**
	 * Функция установки набора рюкзака.
	 * @param item Устанавливаемое значение особи.
	 */
	public void setitems(int item[]) {
		for (int i = 0; i < num_items; i++) {
			this.items[i] = item[i];
		}
	}
	
	/**
	 * Функция получения набора рюкзака.
	 * @return Набор рюкзака.
	 */
	public int[] getitems() {
		int[] tmp = new int[num_items];
		
		for (int i = 0; i < num_items; i++) {
			tmp[i] = items[i];
		}
		return tmp;
	}
	
	/**
	 * Функция получения размера массива.
	 * @return Размер массива.
	 */
	public int getnum_items() {
		return this.num_items;
	}
	
	/**
	 * Функция получения количества предметов.
	 * @return Количество предметов.
	 */
	public int getnum_of_use() {
		return this.num_of_use;
	}
}