package question;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import question.Transaction;
import utility.HashGenerator;

/**
 * This class represents a leaf node of a Merkle tree. It extends Node class. Different from Node class, this class contains a transaction.
 * @author mustafa atay
 *
 */
class LeafNode extends Node {
	/**
	 * The transaction this node has
	 */
	Transaction t;
	
	/**
	 * Creates a new LeafNode object with the given transaction.
	 * @param t
	 */
	LeafNode(Transaction t) {
		left = right = null;
		this.t = t;
	}
	
	
	@Override
	public void printDetailed() {
		if(t== null) {
			System.out.println("transaction eklenememis");
			return;
		}
		System.out.println("transaction's Id: " + t.toString());
		System.out.println(data);
	}
	
	@Override
	public void print() {
		if(t== null) {
			System.out.println("transaction's Id: empty");
			return;
		}
		System.out.println("transaction's Id: " + t.toString());
	}
	
	@Override
	void calculateTreeHash() {
		if (t == null) {
			data = "";
			return;
		}
		try {
			data = HashGenerator.generateHash(t.getTransactionID());
		} catch ( NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	@Override 
	protected int countTransactions() {
		return 1;
	}
}


