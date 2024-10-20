/**
 * This class implements the Dictionary ADT using a hash table. The dictionary is implemented using a hash table with separate chaining.
 * The records are not stored directly in the table, each entry of the table has a reference to a list of the records mapped to the entry 
 * of the hash function.
 */
public class HashDictionary implements DictionaryADT {
	
	private ListNode[] table;
	private int count;
	
	 /**
     * Constructs an empty dictionary of the specified size.
     *
     * @param size the size of the hash table
     */
	public HashDictionary(int size) { 
		table = new ListNode[size];
		count = 0;
	}
	
	/**
     * Adds a record to the dictionary. Throws a DictionaryException if record.getConfiguration() 
     * is already in the dictionary. Returns 1 if adding a record to the table produces a collision, 
     * and returns 0 otherwise.
     *
     * @param record the Data record to add
     * @return 1 if a collision occurs, 0 otherwise
     * @throws DictionaryException if the record is already in the dictionary
     */
	public int put (Data record) throws DictionaryException{
		if (this.get(record.getConfiguration()) != -1) {
			throw new DictionaryException();
		}
		
		int index = hashFunction(record.getConfiguration());
		if (table[index] == null) {
			ListNode head = new ListNode(record);
			table[index] = head;
			count++;
			return 0;
		}else {
			ListNode current = table[index];
			ListNode newElement = new ListNode(record);
			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(newElement);
			count++;
			return 1;
		}
	}
	
	  /**
     * Removes the record with the given configuration from the dictionary. Throws a DictionaryException 
     * if no record in the hash table stores the given configuration.
     *
     * @param config the configuration string of the record to remove
     * @throws DictionaryException if the record is not found in the dictionary
     */
	public void remove (String config) throws DictionaryException{
		if (this.get(config) == -1) throw new DictionaryException();
		
		int index = hashFunction(config); //get the location in the table that we want to remove from 
		ListNode previous = null;
		ListNode current = table[index];
		
		
		while (current != null) {
			if (current.getElement().getConfiguration().equals(config)) {
				if (previous != null) {
					previous.setNext(current.getNext());
				}else {
					table[index] = current.getNext();
				}
				count--;
				return;
			}
			previous = current;
			current = current.getNext();
	
		}	
	}
	
	 /**
     * Returns the number of records in the dictionary.
     *
     * @return the number of records
     */
	public int numRecords() {
		return count;
	}
	
	/**
     * Computes the hash value for a given configuration string.
     *
     * @param config the configuration string
     * @return the hash value
     */
	private int hashFunction(String config) {
		int hash = 0;
		for (int i = 0; i < config.length(); i++) {
			hash = (config.charAt(i) * 41 + hash*11)%this.table.length;

		}
		return hash;
		
	}
	
	/**
     * Retrieves the score associated with the given configuration string.
     *
     * @param config the configuration string
     * @return the score, or -1 if the configuration is not found
     */
	public int get(String config) {
		int index = hashFunction(config);
		ListNode current = table[index];
		while (current != null) {
			Data currData = current.getElement();
			String currConfig = currData.getConfiguration();
			int currScore = currData.getScore();
			//System.out.println("here " + currConfig);
			if (currConfig.equals(config)) {
				return currScore;
			}
			//if (current.getElement().getConfiguration().equals(config)) return current.getElement().getScore();
			//System.out.println("Current is: " + current.getElement().getConfiguration() + " and is score is: " + current.getElement().getScore());
			current = current.getNext();
		}
		
		return -1;
	}
	
}

/**
 * Represents a node in a linked list used in the HashDictionary.
 */
class ListNode {
	private ListNode next;
	private Data data;
	
	/**
     * Constructs a new ListNode with the given Data.
     *
     * @param d the Data object to store in the node
     */
	public ListNode(Data d) {
		this.next = null;
		this.data = d;
	}
	
	/**
     * Returns the Data element stored in this node.
     *
     * @return the Data element
     */
	public Data getElement() {
		return this.data;
	}
	
	 /**
     * Returns the next node in the linked list.
     *
     * @return the next node
     */
	public ListNode getNext() {
		return this.next;
	}
	
	/**
     * Sets the next node in the linked list.
     *
     * @param nextNode the node to set as the next node
     */
	public void setNext(ListNode nextNode) {
		this.next = nextNode;
	}
	
	 /**
     * Returns a string representation of the linked list starting from this node.
     *
     * @return a string representation of the linked list
     */
	
	public String toString() {
    	String s = "";
    	ListNode curr = this;
    	while (curr != null)   	{
    		s += "(" + ((Data)curr.getElement()).getScore() + "," + ((Data)curr.getElement()).getConfiguration() + ")"+ "->";
    		curr = curr.getNext();
    	}
    	return s;
	}
}

