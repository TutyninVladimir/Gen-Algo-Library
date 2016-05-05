package genetics.simplecreature;

/**
 * Класс SimpleMutationOneBit - наследник класса SimpleMutationFunction, 
 * реализующий функцию мутации одного бита для 
 * особи с генотипм в виде целого числа.
 * @author Tutynin Vladimir
 */
public class SimpleMutationOneBit extends SimpleMutationFunction {
	/**
	 * Реализация стратегии мутации одного бита для особи с генотипом 
	 * в виде целого числа.
	 * @param a Мутирующая особь.
	 */
	void mutate(SimpleCreature a) {
		int m;
		int q;
		int olda;
		int newa;
		int x;
		
		m = a.getbytes();
		q = (int) (Math.random() * m);
		olda = a.get();
		newa = 0;
		x = 1;
		for (int i = 0; i < q; i++) {
			x = x * 2;
		}
		newa = olda ^ x;
		a.set(newa);
	}
}
