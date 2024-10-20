/**
 * The Data class depicts a data structure that holds a configuration string and a score. 
 */
public class Data {
	String config;
	int score; 
	
	/**
     * Constructs a new Data object with a specific configuration and score.
     *
     * @param config the configuration string
     * @param score the score value
     */
	public Data(String config, int score) {
		this.config = config;
		this.score = score;
	}
	
	/**
     * Returns the configuration string of this Data object.
     *
     * @return the configuration string
     */
	public String getConfiguration() {
		return config;
	}
	
	/**
     * Returns the score of this Data object.
     *
     * @return the score
     */
	public int getScore() {
		return score; 
	}
}
