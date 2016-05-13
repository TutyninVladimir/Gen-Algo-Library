package example3;

/**
 * Класс BackpackMutationFunction - абстрактный класс стратегии мутации.
 * @author Tutynin Vladimir
 */
public abstract class BackpackMutationFunction {
	/**
	 * Абстрактная функция. Для выполнения стратегии мутации необходимо 
	 * создать класс-наследник, который будет содержать 
	 * реализацию данной функции.
	 * @param a Мутирующая особь.
	 */
	abstract void mutate(BackpackCreature a);
}