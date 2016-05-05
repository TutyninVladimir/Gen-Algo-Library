package genetics.backpackcreature;

/**
 * Класс BackpackMutationOneItem - наследник класса BackpackMutationFunction, 
 * реализующий функцию мутации одного предмета для 
 * особи с генотипм в виде набора предметов.
 * @author Tutynin Vladimir
 */
public class BackpackMutationOneItem extends BackpackMutationFunction {
	/**
	 * Реализация стратегии мутации одного предмета для особи с генотипом 
	 * в виде набора предметов.
	 * @param a Мутирующая особь.
	 */
	void mutate(BackpackCreature a) {
		int n;
		int u;
		int q;
		int[] olda; 
		
		n = a.getnum_items();
		u = a.getnum_of_use();
		q = (int) (Math.random() * n);
		olda = a.getitems();
		if (u != 1) {
			olda[q] = (int) (Math.random() * 10 * u);
			olda[q] = olda[q] % (u + 1);
		} else {
			olda[q] = 1 - olda[q];
		}
		a.regenerate();
		a.setitems(olda);
	}
}