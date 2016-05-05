package genetics.neurocreature;

/**
 * Класс NeuroCreatureExample - наследник класса NeuroCreature, 
 * предназначенный для демонстрации способа использования класса для 
 * решения собственной задачи. 
 * @author Tutynin Vladimir
 */
public class NeuroCreatureExample extends NeuroCreature {
	/**
	 * Конструктор класса, повторяющий конструктор класса NeuroCreature.
	 * @param n Число слоёв.
	 * @param layers Число нейронов каждого слоя.
	 * @param c Объект, реализующий функцию скрещивания.
	 * @param m Объект, реализующий функцию мутации.
	 */
	public NeuroCreatureExample(int n, int layers[], 
	NeuroCrossFunction c, NeuroMutationFunction m) {
		super(n, layers, c, m);
	}

	/**
	 * Реализация абстрактной функции fit класса NeuroCreature.
	 * @return Значение функции приспособленности.
	 */
	double fit() {
		// TO DO: write function.
		return 0;
	}
}