
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package question;

import java.util.*;

class DnsNode {
	/**
	 * A map contains child nodes of this node.
	 */
	private Map<String, DnsNode> childNodeList;
	
	/**
	 * A boolean value shows whether this node has a valid domain name.
	 */
	private boolean validDomain;
	
	/**
	 * A set of ip addresses this node contains.
	 */
	private Set<String> ipAddresses;
	
	/**
	 * A helper deque to implement the Round Robin algorithm. Its elements are always the same with 
	 * elements of set ipAddresses
	 */
	private Deque<String> robinAddresses;
	
	
	/**
	 * Creates a default dns node not being a valid domain with no child nodes and ip addresses.
	 */
	public DnsNode() {
		validDomain = false;
		childNodeList = new HashMap<>();
		ipAddresses = new HashSet<>();
		robinAddresses = new LinkedList<String>();
	}
	
	/**
	 * Adds given ip address to ip addresses list (and robin addresses also). Sets this node as a valid domain.
	 * @param ipAdress
	 */
	public void addIpAdress(String ipAdress) {
		ipAddresses.add(ipAdress);
		if(!robinAddresses.contains(ipAdress)) {
			robinAddresses.add(ipAdress);
		}
		validDomain = true;
	}
	
	/**
	 * Clears all ip addresses and child nodes and makes this dns node not valid domain. From a different perspective
	 * change this dns node to its default.
	 */
	public void flush() {
		validDomain = false;
		ipAddresses.clear();
		robinAddresses.clear();
	}
	
	/**
	 * Returns whether this dns node contains given ip address.
	 * @param adress the ip address which is searched in this nodes ip addresses list
	 * @return whether this dns node contains given ip address
	 */
	public boolean hasIpAddress(String adress) {
		return ipAddresses.contains(adress);
	}
	
	/**
	 * Returns number of ip addresses this node has.
	 * @return number of ip addresses this node has
	 */
	public int IpAddressSize() {
		return ipAddresses.size();
	}
	
	/**
	 * Removes the given ip address from this node.
	 * @param address the ip addres is wanted to be removed
	 */
	public void removeIpAddress(String address) {
		ipAddresses.remove(address);
		robinAddresses.remove(address);
	}
	
	/**
	 * Gives an ip address from this node according to the Round Robin algorithm. If this node contains no ip addresses,
	 * returns null.
	 * @return null if this node has no ip addresses. Else, an ip address as a string
	 */
	public String query() {
		String tmp = robinAddresses.poll();
		robinAddresses.add(tmp);
		return tmp;
	}
	
	/**
	 * Returns child nodes this node has.
	 * @return child nodes as a Map<String, DnsNode>
	 */
	public Map<String, DnsNode> getChildNodeList() {
		return childNodeList;
	}

	/**
	 * Returns true if this node is a valid domain, false otherwise.
	 * @return true if this node is a valid domain, false otherwise
	 */
	public boolean isValidDomain() {
		return validDomain;
	}	
	
	/**
	 * Returns ipAddresses this node contains
	 * @return ipAddresses as a Set<String>
	 */
	public Set<String> getIpAddresses(){
		return ipAddresses;
	}
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

