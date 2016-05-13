package genetics.population;

/**
 *  ласс Selecting - абстрактный класс стратегии останова.
 * @author Tutynin Vladimir
 */
public abstract class Stopping {
	/**
	 * јбстрактна€ функци€. ƒл€ выполнени€ стратегии останова необходимо 
	 * создать класс-наследник, который будет содержать 
	 * реализацию данной функции.
	 * @param n „исло особей попул€ции.
	 * @param cr  ћассив особей, представл€ющих исходную попул€цию.
	 * @return ѕеременна€ типа boolean, указывающа€, выполнено ли условие останова.
	 */
	public abstract boolean isEnding(int n, Creature[] cr);
}