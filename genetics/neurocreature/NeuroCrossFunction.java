package genetics.neurocreature;

/**
 * Класс NeuroCrossFunction - абстрактный класс стратегии скрещивания.
 * @author Tutynin Vladimir
 */
public abstract class NeuroCrossFunction {
	/**
	 * Абстрактная функция. Для выполнения стратегии скрещивания необходимо 
	 * создать класс-наследник, который будет содержать 
	 * реализацию данной функции.
	 * @param a Первая особь.
	 * @param b Вторая особь.
	 */
	abstract void cross(NeuroCreature a, NeuroCreature b);
}