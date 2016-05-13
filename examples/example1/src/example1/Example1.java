package example1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import genetics.choosing.ChoosingRandom;
import genetics.population.Choosing;
import genetics.population.Population;
import genetics.population.Selecting;
import genetics.population.Stopping;
import genetics.selecting.SelectingMax;
import genetics.stopping.StoppingIterations;

public class Example1 {

	public static void main(String arg[]) {
		int map_size = 0; 
		int size = 100;
		int iters = 100;
		int tmp;
		boolean map[][] = null;
		String str1 = "input1.txt";
		
		CommandLineParser parser = new DefaultParser();
		Options options = new Options();
		Option opt1 = new Option("s", "size", true, "Size");
		opt1.setArgs(1);
		Option opt2 = new Option("f", "file", true, "File");
		opt2.setArgs(1);
		Option opt3 = new Option("i", "iter", true, "Iterations");
		opt3.setArgs(1);
		options.addOption(opt1);
		options.addOption(opt2);
		options.addOption(opt3);
		
		try {
		    CommandLine line = parser.parse( options, arg );
		    if (line.hasOption("s")) {
		    	size = Integer.parseInt(line.getOptionValue("s"));
		    }
		    if (line.hasOption("f")) {
		    	str1 = line.getOptionValue("f");
		    }
		    if (line.hasOption("i")) {
		    	iters = Integer.parseInt(line.getOptionValue("i"));
		    }
		}
		catch( ParseException exp ) {
		    System.out.println( "Unexpected exception:" + exp.getMessage() );
		}
		
		File file = new File(str1);
		try {
			Scanner scan = new Scanner(file);
			map_size = scan.nextInt();
			map = new boolean[map_size][map_size];
			for (int i = 0; i < map_size; i++) {
				for (int j = 0; j < map_size; j++) {
					tmp = scan.nextInt();
					if (tmp == 0) {
						map[i][j] = false;
					} else {
						map[i][j] = true;
					}
				}
			}
			scan.close();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		GraphCrossFunction cross1 = new GraphCrossExtended();
		GraphMutationFunction mutate1 = new GraphMutationOneChange();
		GraphCreature cr1 = new GraphCreaturePainting(map_size, cross1, mutate1);
		((GraphCreaturePainting)cr1).init(map_size, map);
		
		Choosing ch1 = new ChoosingRandom();
		Selecting sel1 = new SelectingMax();
		Stopping st1 = new StoppingIterations(iters);
		if (size<10) {
			size=10;
		}
		if (size>100000) {
			size=100000;
		}
		Population p1 = new Population(size, 0.4, 0.05, cr1, ch1, sel1, st1);
		p1.run();
		GraphCreaturePainting ans1 = (GraphCreaturePainting) p1.getAnswerCreature();
		System.out.print("Answer: " + ans1.getAns());
	}
}
