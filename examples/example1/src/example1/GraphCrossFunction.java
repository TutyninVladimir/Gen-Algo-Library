package example1;

/**
 * Класс GraphCrossFunction - абстрактный класс стратегии скрещивания.
 * @author Tutynin Vladimir
 */
public abstract class GraphCrossFunction {
	/**
	 * Абстрактная функция. Для выполнения стратегии скрещивания необходимо 
	 * создать класс-наследник, который будет содержать 
	 * реализацию данной функции.
	 * @param a Первая особь.
	 * @param b Вторая особь.
	 */
	abstract void cross(GraphCreature a, GraphCreature b);
}