package genetics.graphcreature;

/**
 *  ласс GraphMutationOneChange - наследник класса GraphMutationFunction, 
 * реализующий функцию мутации одной перестановки дл€ особи 
 * в виде массива неповтор€ющихс€ чисел от 1 до n.
 * @author Tutynin Vladimir
 */
public class GraphMutationOneChange extends GraphMutationFunction {
	/**
	 * –еализаци€ стратегии мутации обмена соседних чисел дл€ особи с генотипом 
	 * в виде массива неповтор€ющихс€ чисел от 1 до n.
	 * @param a ћутирующа€ особь.
	 */
	void mutate(GraphCreature a) {
		int m;
		int q;
		int[] olda;
		int tmp;
		
		m = a.getlength();
		q = (int) (Math.random() * (m - 1));
		olda = a.get();
		tmp = olda[q];
		olda[q] = olda[q + 1];
		olda[q + 1] = tmp;
		a.set(olda);
	}
}