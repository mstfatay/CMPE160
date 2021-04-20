package question;

/**
 * This class represents a transaction of a blockchain. It contains information about the transaction (transactionID).
 * @author mustafa atay
 *
 */
public class Transaction {
	/**
	 * the id of the transactions
	 */
	private String transactionID;
	
	/**
	 * Creates a transaction with the given transactionID.
	 * @param transactionID the transactionID of the new transaction.
	 */
	public Transaction(String transactionID) {
		this.transactionID = transactionID;
	}
	
	@Override
	public String toString() {
		return transactionID;
	}
	
	/**
	 * returns transactionID of this object.
	 * @return transactionID as a String object
	 */
	public String getTransactionID() {
		return transactionID;
	}
}
