package genetics.stopping;

import genetics.population.*;

/**
 * Класс StoppingIterations - наследник класса Stopping, реализующий функцию 
 * останова после заданного числа итераций алгоритма.
 * @author Tutynin Vladimir
 */
public class StoppingIterations extends Stopping {
	int turns;
	int turn;
	
	/**
	 * Констркутор класса. При создании указывается число итераций алгоритма,
	 * после которого алгоритм завершает работу.
	 * @param turns Число итераций.
	 */
	public StoppingIterations(int turns) {
		if (turns < 1) {
			turns = 1;
		}
		this.turns = turns;
		this.turn = 0;
	}
	
	/**
	 * Функция проверки условия останова.
	 * @param n Число особей популяции.
	 * @param cr Массив особей, представляющих исходную популяцию.
	 * @return Переменная типа boolean, указывающая, выполнено ли условие останова.
	 */
	public boolean isEnding(int n, Creature[] cr) {
		this.turn++;
		if (this.turn >= this.turns) {
			return true;
		}
		return false;
	}

	/**
	 * Устанавливает число итераций для стратегии останова.
	 * @param x Устанавливаемое число итераций.
	 */
	public void setTurns(int x) {
		if (x < 1) {
			x = 1;
		}
		this.turns = x;
		this.turn = 0;
	}

	/**
	 * Функция, возвращающая параметр числа итераций алгоритма.
	 * @return Установленное число итераций.
	 */
	public int getTurns() {
		return this.turns;
	}
}