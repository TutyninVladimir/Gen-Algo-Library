package example3;

/**
 * Класс BackpackCreatureExample - наследник класса BackpackCreature, 
 * предназначенный для демонстрации способа использования класса для 
 * решения собственной задачи. 
 * @author Tutynin Vladimir
 */
public class BackpackCreatureExample extends BackpackCreature {
	/**
	 * Конструктор класса, повторяющий конструктор класса SimpleCreature.
	 * @param n Размер массива.
	 * @param num_of_use Максимальное количество предметов одного типа, которые
	 * можно взять в рюкзак.
	 * @param c Объект, реализующий функцию скрещивания.
	 * @param m Объект, реализующий функцию мутации.
	 */
	public BackpackCreatureExample(int n, int num_of_use, 
	BackpackCrossFunction c, BackpackMutationFunction m) {
		super(n, num_of_use, c, m);
	}
	
	/**
	 * Реализация абстрактной функции fit класса BackpackCreature.
	 * @return Значение функции приспособленности.
	 */
	double fit() {
		//TO DO: write function.
		return 0;
	}
}