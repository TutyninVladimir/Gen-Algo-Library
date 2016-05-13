package example1;

/**
 * Класс GraphCreatureExample - наследник класса GraphCreature, 
 * предназначенный для демонстрации способа использования класса для 
 * решения собственной задачи. 
 * @author Tutynin Vladimir
 */
public class GraphCreatureExample extends GraphCreature {
	/**
	 * Конструктор класса, повторяющий конструктор класса GraphCreature.
	 * @param n Размер массива.
	 * @param c Объект, реализующий функцию скрещивания.
	 * @param m Объект, реализующий функцию мутации.
	 */
	public GraphCreatureExample(int n, GraphCrossFunction c, 
	GraphMutationFunction m) {
		super(n, c, m);
	}
	
	/**
	 * Реализация абстрактной функции fit класса GraphCreature.
	 * @return Значение функции приспособленности.
	 */
	double fit() {
		//TO DO: write function.
		return 0;
	}
}