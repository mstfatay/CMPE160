package question;

import question.Node;

/**
 * This class represents a block of a Blockchain object. a Block object contains a Merkle tree a referance to the previous Block object and
 * hash value of that object. Using this class's functions new transactions and blocks can be added. 
 * @author mustafa atay
 *
 */
class Block {
	/**
	 * this is the hash value of the previous block. If there is no previous block, then this is null.
	 */
	private String hashValue;
	
	/**
	 * the root node of the Merkle tree of this block
	 */
	Node root; 
	
	/**
	 * the previous block
	 */
	Block prev; 
	
	/**
	 * Creates an empty Block object with an empty Merkle tree
	 */
	Block() {
		Node n = new Node();
		prev = null;
		hashValue = null;
		this.root = n;
	}
	
	/**
	 * Adds a new block to list which contains this block and returns that new block. The new block is empty.
	 * @return the new created block
	 */
	Block addBlock() {
		Block b = new Block();
		b.hashValue = this.root.getData();
		b.prev = this;
		return b;
	}
	
	/**
	 * Adds a transaction by manipulating the Merkle tree this block has. If there is no empty space for a new
	 * transaction in the Merkle tree, then it doesn't do anything.
	 * @param t
	 */
	void addTransaction(Transaction t) {
		root.addTransaction(t);
	}
	
	/**
	 * Returns whether the Merkle tree of this block is full or have more space to have more transactions.
	 * @return True if a new transaction can be added. False, otherwise.
	 */
	boolean isFull() {
		return root.isFull();
	}
	
	/**
	 * Updates the hash values of the Merkle tree this block has.
	 */
	void calculateTreeHash() {
		root.calculateTreeHash();
	}
	
	/**
	 * Writes transactions this blockchain contains to console.
	 */
	public void print() {
		printRecursive();
		System.out.println("last block's hash value: " + root.data);
	}
	
	/**
	 * Writes hash values of all nodes of this blockchain to console.
	 */
	public void printDetailed() {
		printDetailedRecursive();
		System.out.println();
	}
	
	private void printRecursive() {
		if (prev==null) {
			System.out.println("hashValue is: " + hashValue);
			root.print();
			return;
		}
		prev.printRecursive();
		System.out.println("hashValue is: " + hashValue);
		root.print();
	}
	
	private void printDetailedRecursive() {
		if (prev==null) {
			root.printDetailed();
			return;
		}
		prev.printDetailedRecursive();
		System.out.println();
		root.printDetailed();
	}
	
	/**
	 * returns the hash value of the previous block
	 * @return the hash value of the previous block
	 */
	public String getHashValue() {
		return hashValue;
	}
}


