package question;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import utility.HashGenerator;
import java.util.*;

/**
 * This class represents a node of a Merkle tree. New transactions can be added, hash values can be calculated and errors can be found
 * by this class's methods. Adding new transactions automatically expands the tree. Maximum 4 transactions can be added
 * to a tree. This class's some methods (addTransaction and findError) should only be used by root of a Merkle tree.
 * @author mustafa atay
 *
 */
class Node {
	/**
	 * The hash value of this node
	 */
	String data;
	
	/**
	 * the left and right child nodes of this node. If there is no left or right child node, It is null.
	 */
	Node left, right;
	
	/**
	 * Creates an empty Node object.
	 */
	Node() {
		data = "";
	}
	
	/**
	 * Adds the given transaction to this tree. If tree is full and so a new transaction can't be added, returns false. Otherwise, 
	 * returns true. This method should only be called by a root node.
	 * @param t the transaction which is wanted to add
	 * @return true if transaction is added. False, otherwise.
	 */
	boolean addTransaction(Transaction t) {
		if(left == null) {
			left = new LeafNode(t);
			return true;
		}
		else if(right == null) {
			right = new LeafNode(t);
			return true;
		}
		else if(left instanceof LeafNode){
			Node n = new Node();
			n.left = left;
			n.right = right;
			left = n;
			right = new Node();
			right.left = new LeafNode(t);
			right.right = null;
			return true;
		}
		else {
			if (right.right == null) {
				right.right = new LeafNode(t);
				return true;
			}
		}	
		return false;
	}
	
	
	/**
	 * Updates hash values of the nodes of this tree.
	 */
	void calculateTreeHash()  {
			left.calculateTreeHash();
		try {
			if(right!=null) {
				right.calculateTreeHash();
				data = HashGenerator.generateHash(left.data + right.data);
			}
			else {
				data = HashGenerator.generateHash(left.data + "");
			}
		} catch ( NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * returns wheter this tree is full. The maximum number of transactions a tree can have is 4. If a tree is full, no more transactions
	 * can be added.
	 * @return true if this tree is full, false, otherwise
	 */
	boolean isFull() {
		return countTransactions() == 4;
	}
	
	/**
	 * Returns the number of transactions this tree has.
	 * @return The number of transactions this tree has
	 */
	protected int countTransactions() {
		// it functions recursively
		if (right == null) {
			return -1;
		}
		return left.countTransactions() + right.countTransactions();
	}
	
	
	/**
	 * Compares this Merkle tree's nodes' hash values with the given list of hash values. Given list of hash values are valid and finds 
	 * the errors of this Merkle tree using it. This method is used by BlockChain class's validate method and the explanation of that method
	 * is eligible for this method. The only difference is this method returns errors of only one Merkle tree and the input of this function
	 * is valid hash values of only that Merkle tree. returns an empty ArrayList if there is no error. This function shouldn't called from
	 * a node which is not root of a Merkle tree.
	 * @param values as an ArrayList of valid hash values
	 * @return errors of this Merkle tree as a ArrayList of Stacks of hash values which aren't valid
	 */
	ArrayList<Stack<String>> findError(ArrayList<String> values){
		ArrayList<Stack<String>> result = new ArrayList<Stack<String>>(); // This is the object that this function returns
		Stack<String> tmpValues = new Stack<String>(); // a helper stack
		if(data.equals(values.get(0))) {
			// if hash value of root of the tree is valid then there is no error.
			return result;
		}
		tmpValues.push(data); // now we are on depth = 0
		if (!left.data.equals(values.get(1))) {
			// if there is an error in the left child of the root
			tmpValues.push(left.data); // now we are on depth = 0
			if(left.left == null) {
				// if there is no child of the left child of the root add founded values to the result.
				Stack<String> addedValues = new Stack<String>();
				addedValues.addAll(tmpValues);
				result.add(addedValues);
			}
			else {
				// there is no possibility that left child of root has only one child.
				// if there are children of the left child then look them to find which one is invalid (or both) 
				ArrayList<String> tmp = left.findDeepError(values.get(3), values.get(4));
				for(int i = 0; i<tmp.size(); i++) {
					// adds errors to result.
					tmpValues.push(tmp.get(i)); // now we are on depth = 2
					Stack<String> addedValues = new Stack<String>();
					addedValues.addAll(tmpValues);
					result.add(addedValues);
					tmpValues.pop();
				}
			}
			tmpValues.pop(); // now we are on depth = 1 again
		}
		if(right == null) {
			// if there is no right child of root then the job is done, return found errors.
			return result;
		}
		if (!right.data.equals(values.get(2))) {
			// if there is an error in the right child of root
			tmpValues.push(right.data); // now we are on depth = 2
			if(right.left == null) {
				// This means there is no children of right child of root.
				Stack<String> addedValues = new Stack<String>();
				addedValues.addAll(tmpValues);
				result.add(addedValues);
			}
			else if(right.right == null) {
				// This means the error is because of the transaction of root.right.left
				tmpValues.push(right.left.data);
				Stack<String> addedValues = new Stack<String>();
				addedValues.addAll(tmpValues);
				result.add(addedValues);
				tmpValues.pop(); // now we are on depth = 1
			}
			else {
				// This means root.right has left and right children. So we need to check them both.
				ArrayList<String> tmp = right.findDeepError(values.get(5), values.get(6));
				for(int i = 0; i<tmp.size(); i++) {
					tmpValues.push(tmp.get(i));
					Stack<String> addedValues = new Stack<String>();
					addedValues.addAll(tmpValues);
					result.add(addedValues);
					tmpValues.pop(); // now we are on depth = 1
				}
			}
			tmpValues.pop(); // now we are on depth = 0
		}
		return result;
	}
	
	// a helper function for findError function
	private ArrayList<String> findDeepError(String value1, String value2){
		ArrayList<String> result = new ArrayList<String>(); 
		if(!left.data.equals(value1)) {
			result.add(left.data);
		}
		if(!right.data.equals(value2)) {
			result.add(right.data);
		}
		return result;
	}
	
	/**
	 * Writes hash values of all nodes of this tree to console.
	 */
	public void printDetailed() {
		Queue<Node> q = new LinkedList<Node>();
		q.add(left);
		q.add(right);
		
		System.out.println(data);
		while(!q.isEmpty()) {
			Node curr = q.poll();
			if(curr != null) {
				System.out.println(curr.data);
				if(curr.left != null) {
					q.add(curr.left);
				}
				if(curr.right != null) {
					q.add(curr.right);
				}
			}	
		}
	}
	
	/**
	 * Writes transactions this tree contains to console.
	 */
	public void print() {
		if (left==null) {
			System.out.println("sol bos");
			return;
		}
		left.print();
		if(right==null) {
			System.out.println("sag bos");
			return;
		}
		right.print();
	}
	
	/**
	 * Returns the hash value of this node.
	 * @return the hash value of this node
	 */
	public String getData() {
		return data;
	}
	
}

