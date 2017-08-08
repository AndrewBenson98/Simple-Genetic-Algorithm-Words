

public class Phrase {

	private char[] letters;
	private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz?!.,';#~<>1234567890-";
	private int fitness=0;
	private boolean isFitnessChanged= true;
	
	/**
	 * Creates a new word
	 * @param length
	 */
	public Phrase(int length) {
		letters = new char[length];
		initializePhrase();
	}

	/**
	 * Randomly generates the word
	 * @return
	 */
	public Phrase initializePhrase() {
		for (int i = 0; i < GeneticAlgorithm.TARGET_PHRASE.length(); i++) {
			letters[i] = alphabet.charAt((int) (Math.random() * alphabet.length()));
		}
		return this;

	}

	/**
	 * Calculates fitness based on similarities between word and target phrase
	 * @return
	 */
	public int recalculateFitness()
	{
		int chromosomeFitness=0;
		for(int i=0;i<letters.length;i++)
		{
			if(letters[i]==GeneticAlgorithm.TARGET_PHRASE.charAt(i))
				chromosomeFitness++;
		}
		return chromosomeFitness;
		
	}
	
	
	public String toString()
	{
		String phrase="";
		
		for(int i =0;i<letters.length;i++)
		{
			phrase+=letters[i];
		}
		
		return phrase;
		
	}
	
	public char[] getLetters() {
		isFitnessChanged=true;
		return letters;
	}

	public int getFitness() {
		if(isFitnessChanged)
		{
			fitness=recalculateFitness();
			isFitnessChanged=false;
		}
		return fitness;
	}
	
}
