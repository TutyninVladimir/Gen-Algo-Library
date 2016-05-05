package genetics.backpackcreature;

/**
 * Класс BackpackCrossFunction - абстрактный класс стратегии скрещивания.
 * @author Tutynin Vladimir
 */
public abstract class BackpackCrossFunction {
	/**
	 * Абстрактная функция. Для выполнения стратегии скрещивания необходимо 
	 * создать класс-наследник, который будет содержать 
	 * реализацию данной функции.
	 * @param a Первая особь.
	 * @param b Вторая особь.
	 */
	abstract void cross(BackpackCreature a, BackpackCreature b);
}