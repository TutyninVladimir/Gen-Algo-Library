package genetics.simplecreature;

/**
 * Класс SimpleCreatureExample - наследник класса SimpleCreature, 
 * предназначенный для демонстрации способа использования класса для 
 * решения собственной задачи. 
 * @author Tutynin Vladimir
 */
public class SimpleCreatureExample extends SimpleCreature {

	/**
	 * Конструктор класса, повторяющий конструктор класса SimpleCreature.
	 * @param bytes Разрядность числа.
	 * @param c Объект, реализующий функцию скрещивания.
	 * @param m Объект, реализующий функцию мутации.
	 */
	public SimpleCreatureExample(int bytes, SimpleCrossFunction c, 
	SimpleMutationFunction m) {
		super(bytes, c, m);
	}
	
	/**
	 * Реализация абстрактной функции fit класса SimpleCreature. В данном случае
	 * представлена функция y=x^2+2*x+1, она может быть изменена на функцию, 
	 * необходимую пользователю.
	 * @return Значение функции приспособленности.
	 */
	double fit() {
		// TO DO: write function.
		double f = super.q * super.q + super.q * 2 + 1;
		return f;
	}
}