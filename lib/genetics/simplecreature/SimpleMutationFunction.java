package genetics.simplecreature;

/**
 * Класс SimpleMutationFunction - абстрактный класс стратегии мутации.
 * @author Tutynin Vladimir
 */
public abstract class SimpleMutationFunction {
	/**
	 * Абстрактная функция. Для выполнения стратегии мутации необходимо 
	 * создать класс-наследник, который будет содержать 
	 * реализацию данной функции.
	 * @param a Мутирующая особь.
	 */
	abstract void mutate(SimpleCreature a);
}