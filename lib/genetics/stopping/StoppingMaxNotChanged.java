package genetics.stopping;

import genetics.population.Creature;
import genetics.population.Stopping;

/**
 * Класс StoppingMaxNotChanged - наследник класса Stopping, реализующий функцию 
 * останова после отсутствия изменения максимального значения приспособленности
 * в течение заданного числа итераций.
 * @author Tutynin Vladimir
 */
public class StoppingMaxNotChanged extends Stopping {
	int turns;
	int turn;
	double lastMax;

	/**
	 * Констркутор класса. При создании указывается число итераций алгоритма,
	 * после отсутствия изменений функции приспособленности в течение 
	 * заданного числа итераций алгоритм завершает работу.
	 * @param turns Число итераций.
	 */
	public StoppingMaxNotChanged(int turns) {
		if (turns < 1) {
			turns = 1;
		}
		this.turns = turns;
		this.turn = 0;
		this.lastMax = 0;
	}
	
	/**
	 * Функция проверки условия останова.
	 * @param n Число особей популяции.
	 * @param cr  Массив особей, представляющих исходную популяцию.
	 * @return Переменная типа boolean, указывающая, выполнено ли условие останова.
	 */
	public boolean isEnding(int n, Creature[] cr) {
		double thisMax;
		double tmp;
		
		this.turn++;
		thisMax = cr[0].fitness();
		for (int i = 1; i < n; i++) {
			tmp = cr[i].fitness();
			if (tmp > thisMax) {
				thisMax = tmp;
			}
		}
		if (thisMax != lastMax) {
			lastMax = thisMax;
			this.turn = 0;
		}
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
