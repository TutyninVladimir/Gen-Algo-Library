package genetics.simplecreature;

import genetics.population.Creature;

/**
 * Класс SimpleCreature - наследник класса Creature, реализующий операции для
 * работы с осоьбю, генотип которой представлен в виде целого числа.
 * @author Tutynin Vladimir
 */
public abstract class SimpleCreature extends Creature {
	protected int bytes;
	protected int q;
	protected SimpleCrossFunction c;
	protected SimpleMutationFunction m;

	/**
	 * Конструктор класса. При создании особи указывается разрядность 
	 * числа (от 1 до 31), а также объекты, реализующие стратегии
	 * скрещивания и мутации.
	 * @param bytes Разрядность числа.
	 * @param c Объект, реализующий функцию скрещивания.
	 * @param m Объект, реализующий функцию мутации.
	 */
	public SimpleCreature(int bytes, SimpleCrossFunction c, 
	SimpleMutationFunction m) {
		if (bytes > 31) {
			bytes = 31;
		}
		if (bytes < 1) {
			bytes = 1;
		}
		this.bytes = bytes;
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
	 * Функция копирования значения генотипа - числа - из одной особи в другую.
	 * @param a Особь, в которую копируются параметры.
	 */
	public void copyData(Creature a) {
		((SimpleCreature) a).bytes = this.bytes;
		((SimpleCreature) a).q = this.q;
	}

	/**
	 * Функция генерации произвольного числа с заданной разрядностью. 
	 */
	public void generate() {
		int tmp = 1;
		
		for (int i = 1; i <= this.bytes; i++) {
			tmp *= 2;
		}
		this.q = (int) (Math.random() * tmp);
	}

	/**
	 * Функция скрещивания особи.
	 */
	public void cross(Creature cr) {
		c.cross(this, (SimpleCreature) cr);
	}

	/**
	 * Функция мутации особи.
	 */
	public void mutation() {
		m.mutate(this);
	}

	/**
	 * Функция установки заданного числа особи.
	 * @param q Устанавливаемое значение особи.
	 */
	public void set(int q) {
		int tmp = 1;
		
		for (int i = 1; i <= this.bytes; i++) {
			tmp *= 2;
		}
		if (q > tmp - 1) {
			q = tmp - 1;
		}
		if (q < 0) {
			q = 0;
		}
		this.q = q;
	}

	/**
	 * Функция получения числа особи.
	 * @return Число особи.
	 */
	public int get() {
		return this.q;
	}

	/**
	 * Функция получения числа разрядов.
	 * @return Число разрядов, установленное для данной особи.
	 */
	public int getbytes() {
		return this.bytes;
	}
}