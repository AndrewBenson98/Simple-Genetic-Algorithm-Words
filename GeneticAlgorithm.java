package StringGenetics;

public class GeneticAlgorithm {

	// The target phrase
	public static final String TARGET_PHRASE = "I love you <3";

	// The number of random elements being created
	public static final int POPULATION_SIZE = 8;

	// number of characters guaranteed to be passed on to the next generation
	public static final int NUM_OF_ELITE_CHARACTERS = 1;

	// The amount of words chosen to pass on their genes / characteristics
	public static final int TOURNAMENT_SELECTION_SIZE = TARGET_PHRASE.length() / 2;

	// Chance of random mutation in a gene
	public static final double MUTATION_RATE = 0.10;

	private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz?!.,';#~<>1234567890-";

	/**
	 * Implements random evolution into the population
	 * 
	 * @param population
	 * @return
	 */
	public Population evolve(Population population) {
		return mutatePopulation(crossOverPopulation(population));
	}

	/**
	 * 
	 * @param population
	 * @return
	 */
	private Population crossOverPopulation(Population population) {
		Population crossOverPopulation = new Population(population.getPhrases().length);

		// Exclude elite chromosome from mutation and crossover
		for (int x = 0; x < NUM_OF_ELITE_CHARACTERS; x++) {
			crossOverPopulation.getPhrases()[x] = population.getPhrases()[x];
		}

		// Chooses two words from the tournament selection and crosses them
		for (int x = NUM_OF_ELITE_CHARACTERS; x < population.getPhrases().length; x++) {
			Phrase chromosome1 = selectTournamentPopulation(population).getPhrases()[0];
			Phrase chromosome2 = selectTournamentPopulation(population).getPhrases()[0];
			crossOverPopulation.getPhrases()[x] = crossOverPhrase(chromosome1, chromosome2);
		}
		return crossOverPopulation;
	}

	/**
	 * Creates a new word given two other words
	 * 
	 * @param phrase1
	 * @param phrase2
	 * @return
	 */
	private Phrase crossOverPhrase(Phrase phrase1, Phrase phrase2) {

		Phrase crossOverChromosome = new Phrase(TARGET_PHRASE.length());

		// Randomly takes a letter from one of the two parents at index i and
		// assigns it the to new word at the same index
		for (int i = 0; i < phrase1.getLetters().length; i++) {
			if (Math.random() <= 0.5)
				crossOverChromosome.getLetters()[i] = phrase1.getLetters()[i];
			else
				crossOverChromosome.getLetters()[i] = phrase2.getLetters()[i];
		}
		return crossOverChromosome;
	}

	/**
	 * Randomly selects words that will pass on their genes / characteristics
	 * 
	 * @param population
	 * @return
	 */
	private Population selectTournamentPopulation(Population population) {

		Population tournamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE);

		// Pick up number of chromosomes randomly
		for (int x = 0; x < TOURNAMENT_SELECTION_SIZE; x++) {
			tournamentPopulation.getPhrases()[x] = population
					.getPhrases()[(int) (Math.random() * population.getPhrases().length)];
		}

		// Sort
		tournamentPopulation.sortByFitness();

		return tournamentPopulation;
	}

	/**
	 * 
	 * @param population
	 * @return
	 */
	private Population mutatePopulation(Population population) {
		Population mutatePopulation = new Population(population.getPhrases().length);

		// Exclude elite chromosomes from mutation and crossover
		for (int x = 0; x < NUM_OF_ELITE_CHARACTERS; x++) {
			mutatePopulation.getPhrases()[x] = population.getPhrases()[x];
		}
		// Mutate the rest of the population
		for (int x = NUM_OF_ELITE_CHARACTERS; x < population.getPhrases().length; x++) {
			mutatePopulation.getPhrases()[x] = mutatePhrase(population.getPhrases()[x]);
		}
		return mutatePopulation;
	}

	/**
	 * Applies a chance of mutation for a member of a population
	 * 
	 * @param phrase
	 * @return
	 */
	private Phrase mutatePhrase(Phrase phrase) {
		// Applies randomness to randomly change a character
		Phrase mutatePhrase = new Phrase(TARGET_PHRASE.length());

		// for each character
		for (int x = 0; x < phrase.getLetters().length; x++) {
			// Chance to mutate
			if (Math.random() <= MUTATION_RATE) {
				// change character to a random one in the alphabet
				mutatePhrase.getLetters()[x] = alphabet.charAt((int) (Math.random() * alphabet.length()));

			} else
				// If no mutation then carry over gene normally
				mutatePhrase.getLetters()[x] = phrase.getLetters()[x];
		}
		return mutatePhrase;
	}

}