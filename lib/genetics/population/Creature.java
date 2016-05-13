package genetics.population;

/**
 * Класс Creature - абстрактынй класс особи.
 * @author Tutynin Vladimir
 */
public abstract class Creature implements Cloneable {
	
	/**
	 * Функция клонирования, используется классом CreatureMachine для 
	 * создания клона.
	 * @return Клонированная особь.
	 */
	@Override
	public Creature clone() {
		Creature copy = null;
		try {
			copy = (Creature) super.clone();
		} catch (CloneNotSupportedException e) {
			// e.printStackTrace();
		}
		return copy;
	}
	
	/**
	 * Абстрактная функция вычисления значения приспособленности. Зависит от 
	 * решаемой задачи. 
	 * @return Значение функции приспособленности.
	 */
	public abstract double fitness();

	/**
	 * Абстрактная функция копирования значения генотипа из одной особи в другую.
	 * @param cr Особь, в которую копируются параметры.
	 */
	public abstract void copyData(Creature cr);

	/**
	 * Абстрактная функция скрещивания, зависит от структуры генотипа особи. 	
	 * @param cr Особь, с которой будет скрещиваться искомая особь.
	 */
	public abstract void cross(Creature cr);

	/**
	 * Абстрактная функция мутации, зависит от структуры генотипа особи.
	 */
	public abstract void mutation();
	
	/**
	 * Абстрактная функция генерации произвольного значения генотипа,
	 * зависит от структуры генотипа особи.
	 */
	public abstract void generate();
}