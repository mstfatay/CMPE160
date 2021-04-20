package question;

import question.Block;
import question.Transaction;

import java.util.*;
import java.io.*;

/**
 * This class is used to create and use a simple block chain system. Transactions can be added and validation of
 * the block chain can be done. 
 * @author mustafa atay
 *
 */
public class Blockchain {
	/**
	 * The last added block to this Blockchain object. If there is no block added, it is null.
	 */
	Block recentBlock;
	
	/**
	 * Constructor of a new empty Blockchain object with a null recent block.
	 */
	public Blockchain() {
		recentBlock = null;
	}
	
	/**
	 * Adds multiple transactions to this Blockchain object. The parameter of this function is the name of a file.
	 * This file's every line should contain a transactionID. Transactions having these transactionID's are added
	 * to this Blockchain. If needed, new Blockchain Blocks are created. After adding transactions, hash of trees that 
	 * any transaction added to are updated.
	 * @param listOfTransactions the name of a file containing transactionIDs.
	 */
	public void addBatchTransactions(String listOfTransactions) {
		File f = new File(listOfTransactions);
		try {
			Scanner input = new Scanner(f);
			while(input.hasNextLine()) {
				String id = input.nextLine();
				addSingleTransaction(id);	
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a transaction with the given transactionID to this Blockchain object. If all blocks are full, then
	 * a new block is created. After the transaction is added, hash value of the tree the transaction added is 
	 * updated.
	 * @param transactionID The transactionID of the added transaction as a String
	 */
	public void addSingleTransaction(String transactionID) {
		Transaction t = new Transaction(transactionID);
		if(recentBlock == null) {
			// If there is no block (initially)
			Block b = new Block();
			recentBlock = b;
		}
		else if(recentBlock.isFull()) {
			// If recent block is full create a new block
			recentBlock = recentBlock.addBlock();
		}
		recentBlock.addTransaction(t);
		recentBlock.calculateTreeHash();
	}
	
	/**
	 * Detects whether all transactions are as they should be or there is a error. Returns errors.
	 * The parameter of this function is a name of a file. This file has the correct hash values of the Blockchain
	 * trees. Every different line of this file should contain a hash value. Hash values of a tree should be 
	 * ordered to this file in BFS order (for a full tree there will be 7 lines). The least recent block's tree's 
	 * hash values should come first in the file.
	 * For example if there is a block chain with 4 blocks full of transactions. the file should has 28 lines
	 * of hash values. first line of the file should be the hash value of the first added block. The second line is
	 * the hash value of left node of tree of that block.
	 * This function returns a ArrayList elements of which are Stack of Strings. When an error is found, its path
	 * on its tree is added to that ArrayList as a Stack. First the hash value of root of that tree is pushed to
	 * this stack, then hash value of its right or left child node depending on where the error is and goes on.
	 * Every stack means an error. Stacks are added to the ArrayList starting from the most recent Block to the least.
	 * If one block contains more than one error, the order stacks are random.
	 * 
	 * @param correctHashList the name of a file containing correct hash values in a specified order.
	 * @return 
	 */
	public ArrayList<Stack<String>> validate(String correctHashList){
		ArrayList<Stack<String>> result = new ArrayList<Stack<String>>();
		Stack<ArrayList<String>> hashValues = extractHashValues(correctHashList);
		Block currBlock = recentBlock;
		ArrayList<String> currValues = hashValues.pop();
		if(currValues.get(0).equals(currBlock.root.data)) {
		}
		else {
			result.addAll(currBlock.root.findError(currValues));
		}
		while(!hashValues.isEmpty()) {
			currValues = hashValues.pop();
			if(currValues.get(0).equals(currBlock.getHashValue())) {
			}
			else {
				result.addAll(currBlock.prev.root.findError(currValues));
			}
			currBlock = currBlock.prev;
		}
		
		return result;
	}
	
	// Creates a stack of arraylist of hash values from a given file name containing hash values for validate method.
	private Stack<ArrayList<String>> extractHashValues(String correctHashList){
		File f = new File(correctHashList);
		try {
			Scanner input = new Scanner(f);
			Stack<ArrayList<String>> hashValues = new Stack<ArrayList<String>>();
			while(input.hasNextLine()) {
				ArrayList<String> currHash = new ArrayList<String>();
				int i = 0;
				for(; i<7 && input.hasNextLine(); i++) {
					currHash.add(input.nextLine());
				}
				hashValues.add(currHash);
			}
			input.close();
			return hashValues;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Writes transactions this blockchain contains to console. The order of blocks is from the least recent block to the most. The order
	 * of the transactions are also from the least recent node to the most.
	 */
	public void print() {
		recentBlock.print();
	}
	
	/**
	 * Writes hash values of all nodes of this blockchain to console. The order of blocks is from the least recent block to the most. The
	 * hash values of nodes are in BFS order.
	 */
	public void printDetailed() {
		recentBlock.printDetailed();
	}
}
