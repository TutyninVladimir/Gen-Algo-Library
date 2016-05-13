package example1;

/**
 * Класс GraphMutationFunction - абстрактный класс стратегии мутации.
 * @author Tutynin Vladimir
 */
public abstract class GraphMutationFunction {
	/**
	 * Абстрактная функция. Для выполнения стратегии мутации необходимо 
	 * создать класс-наследник, который будет содержать 
	 * реализацию данной функции.
	 * @param a Мутирующая особь.
	 */
	abstract void mutate(GraphCreature a);
}