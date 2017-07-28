package StringGenetics;

public class Population {

	//The list of words
	private Phrase[] phrases;

	/**
	 * 
	 * @param length the size of the population
	 */
	public Population(int length) {
		phrases = new Phrase[length];
	}

	/**
	 * Generates a new population of words
	 * @return
	 */
	public Population initializePopulation() {

		for (int x = 0; x < phrases.length; x++) {
			phrases[x] = new Phrase(GeneticAlgorithm.TARGET_PHRASE.length()).initializePhrase();
		}

		//Sort
		sortByFitness();
		return this;
	}

	/**
	 * Sorts the list of words by fitness
	 */
	public void sortByFitness() {
		int currentFitness;
		for (int numSwitches = 0; numSwitches < phrases.length; numSwitches++) {
			currentFitness = numSwitches;
			for (int fitnessCompare = numSwitches; fitnessCompare < phrases.length; fitnessCompare++) {
				if (phrases[currentFitness].getFitness() < (phrases[fitnessCompare].getFitness())) {
					currentFitness = fitnessCompare;
				}
			}
			Phrase tempPhrase = phrases[currentFitness];
			phrases[currentFitness] = phrases[numSwitches];
			phrases[numSwitches] = tempPhrase;
		}

	}

	public Phrase[] getPhrases() {
		return phrases;
	}

}
