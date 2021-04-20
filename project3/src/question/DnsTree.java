
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package question;

import java.util.*;

public class DnsTree {
	/**
	 * The root node of this tree.
	 */
	private DnsNode root;
	
	/**
	 * Creates an empty dns tree.
	 */
	public DnsTree() {
		this.root = new DnsNode();
	}
	
	/**
	 * Adds a record with the given domain name and ip address to this tree. If there is a node with the given
	 * domain name, this method adds only the given ip address to this node. If there is not such a node, this method
	 * creats a new node at the appropriate location of this tree.
	 * @param domainName domain name
	 * @param ipAddress ip address
	 */
	public void insertRecord(String domainName, String ipAddress) {
		Stack<String> domainParts = splitDomainName(domainName);
		DnsNode curr = root;
		while (!domainParts.isEmpty()) {
			String part = domainParts.pop();
			DnsNode newn = curr.getChildNodeList().get(part);
			if(newn == null) {
				//A new dns node should be created and add to tree because it doesn't already exists.
				newn = new DnsNode();
				curr.getChildNodeList().put(part, newn);
			}
			curr = newn;
		}
		curr.addIpAdress(ipAddress);
	}

	/**
	 * Removes the record with the given domain name from this tree. If the given domain name doesn't exist in the tree
	 * returns false. Else, this method works as expected and return true. If the record is an intermediate node, just
	 * flushes it. If the record is a leaf node, then removes that node from tree.
	 * @param domainName the domain name which is wanted to delete.
	 * @return true if the record removed. False otherwise.
	 */
	public boolean removeRecord(String domainName) {
		Stack<String> domainParts = splitDomainName(domainName);
		DnsNode curr = root;
		
		while(true) {
			String part = domainParts.pop();
			DnsNode newn = curr.getChildNodeList().get(part);
			
			if(newn == null) {
				return false;
			}
			
			if(domainParts.isEmpty()) {
				// here newn is the node that is wanted to be deleted
				if(newn.getChildNodeList().isEmpty()) {
					// there is no child nodes so delete newn
					curr.getChildNodeList().remove(part);
				}
				else {
					// there are children nodes so flush newn
					if(newn.isValidDomain()) {
						newn.flush();
					}
					else {
						return false;
					}
				}
				return true;
			}
			
			curr = newn;
		}
	}
	
	/**
	 * Removes a the given domain name's given ip address from this tree. If there is no such ip address, returns false.
	 * If the node having this ip adress doesn't have any other ip address and it is a leaf node, then this method 
	 * removes that node from the tree. Otherwise, this method only deletes the given ip address. If after deletion
	 * there is no ip addresses in the node, that node is updated as not a valid domain.
	 * @param domainName
	 * @param ipAddress
	 * @return
	 */
	public boolean removeRecord(String domainName, String ipAddress) {
		Stack<String> domainParts = splitDomainName(domainName);
		DnsNode curr = root;
		
		while(true) {
			String part = domainParts.pop();
			DnsNode newn = curr.getChildNodeList().get(part);
			
			if(newn == null) {
				return false;
			}
			
			if(domainParts.isEmpty()) {
				// here newn is the node that is wanted to be deleted
				if(!newn.hasIpAddress(ipAddress)) {
					return false;
				}
				if(newn.IpAddressSize() == 1) {
					//If there is only one ip address, then it acts as removeRecord(String).
					if(newn.getChildNodeList().isEmpty()) {
						// there is no child nodes so delete newn
						curr.getChildNodeList().remove(part);
					}
					else {
						// there are children nodes so flush newn
						newn.flush();
					}
					return true;
				}
				else {
					// If there are more than 1 addresses, then remove the needed one.
					newn.removeIpAddress(ipAddress);
					return true;
				}	
			}			
			curr = newn;
		}
	}
	
	/**
	 * Returns an ip address of the given domain according to the Round Robin algorithm. If this domain name is not found,
	 * returns null.
	 * @param domainName domain name
	 * @return ip address if the given domain name exists. null else.
	 */
	public String queryDomain(String domainName) {
		Stack<String> domainParts = splitDomainName(domainName);
		DnsNode curr = root;
		while (!domainParts.isEmpty()) {
			String part = domainParts.pop();
			DnsNode newn = curr.getChildNodeList().get(part);
			if(newn == null) {
				return null;
			}
			curr = newn;
		}
		return curr.query();
	}
	
	/**
	 * Finds and returns all valid domain names and their ip addresses as a map. Keys of this map are domain names as strings.
	 * Values of this map are ip addresses of each domain name as sets.
	 * @return a map containing valid domain names and their ip addresses as Map<String, Set<String>>
	 */
	public Map<String, Set<String>> getAllRecords(){
		return recursiveGetAllRecords(root, "");
	}
	
	private Map<String, Set<String>> recursiveGetAllRecords(DnsNode n, String domain){
		Map<String, Set<String>> result = new HashMap<>();
		
		if(n.isValidDomain()) {
			result.put(domain, n.getIpAddresses());
		}
		Set<String> keys = n.getChildNodeList().keySet();
		
		for(Object key: keys) {
			String key2 = (String) key;
			String domain2 = "";
			if(domain.length()!=0) {
				domain2 = "." + domain;
			}
			Map<String, Set<String>> m = recursiveGetAllRecords(n.getChildNodeList().get(key2), key2+domain2);
			if(m!=null) {
				result.putAll(m);
			}
		}
		return result;
	}

	
	// Removes dots and splits the given domain name from dots. Returns a stack as follows
	// boun.edu.tr -> [tr, edu, boun] (tr will go out first)
	private Stack<String> splitDomainName(String domainName) {
		StringTokenizer tokens = new StringTokenizer(domainName, ".", false);
		Stack<String> elements = new Stack<String>();
		while(tokens.hasMoreTokens()) {
			elements.add(tokens.nextToken());
		}
		return elements;
	}
	
	// delete this
	public void write() {
		Queue<DnsNode> q = new LinkedList<>();
		q.add(root);
		System.out.println("root");
		while(!q.isEmpty()) {
			DnsNode curr = q.poll();
			Map<String, DnsNode> m = curr.getChildNodeList();
			for(Object node: m.values()) {
				q.add((DnsNode)node);
			}
			
			for(String key: m.keySet()) {
				System.out.print(key.toString());
				System.out.print(" (");
				System.out.print(m.get(key).isValidDomain());
				System.out.print(") - ");
				
				System.out.println(m.get(key).getChildNodeList().keySet());
			}
		}
		
	}
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

