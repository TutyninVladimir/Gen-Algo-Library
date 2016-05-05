package genetics.neurocreature;

/**
 * Класс GraphMutationFunction - абстрактный класс стратегии мутации.
 * @author Tutynin Vladimir
 */
public abstract class NeuroMutationFunction {
	/**
	 * Абстрактная функция. Для выполнения стратегии мутации необходимо 
	 * создать класс-наследник, который будет содержать 
	 * реализацию данной функции.
	 * @param a Мутирующая особь.
	 */
	abstract void mutate(NeuroCreature a);
}