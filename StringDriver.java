package StringGenetics;

public class StringDriver {

	public static void main(String[] args) {
		Population population = new Population(GeneticAlgorithm.POPULATION_SIZE).initializePopulation();
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();

		System.out.println("-----------------------------------------------");
		System.out.println(
				"Generation # 0 " + " | Fittest Phrase Level : " + population.getPhrases()[0].getFitness());
		printPopulation(population, "Target Phrase: " + GeneticAlgorithm.TARGET_PHRASE);

		int generationNumber = 0;
		while (population.getPhrases()[0].getFitness() < GeneticAlgorithm.TARGET_PHRASE.length()) {
			generationNumber++;
			System.out.println("\n----------------------------------------------\n");
			// System.out.println(population.getChromosomes().length);
			population = geneticAlgorithm.evolve(population);

			population.sortByFitness();
			System.out.println("Generation # " + generationNumber + " | Fittest Phrase fitness : "
					+ population.getPhrases()[0].getFitness());
			printPopulation(population, "Target Phrase: " + GeneticAlgorithm.TARGET_PHRASE);

		}

	}

	public static void printPopulation(Population population, String heading) {
		System.out.println(heading);
		
			System.out.println("Fittest  : " + population.getPhrases()[0].toString()
					+ " | Fitness : " + population.getPhrases()[0].getFitness());
	}

}
