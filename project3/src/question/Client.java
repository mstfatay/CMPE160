
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package question;

/**
 * 
 * @author mustafa atay
 *
 */

public class Client{
	/**
	 * a tree that ipp addresses stored according to domain names
	 */
	private DnsTree root;
	
	/**
	 * the ip address of this client
	 */
	private String ipAddress;
	
	/**
	 * A cache list that requested domain names and their associated ip addresses are stored.
	 * It can store up to 10 domain names and their associated addresses.
	 */
	private CachedContent[] cacheList;
	
	/**
	 * A wrapper for the cache list. It contains a domain name, an ip address and a number to track how many times
	 * that domain name requested before.
	 */
	private class CachedContent{
		String domainName;
		String ipAddress;
		int hitNo = 1;
	}
	
	/**
	 * Creats a client with a given ip address and dns tree.
	 * @param ipAddress ip adrress of this client
	 * @param root dns tree this client has
	 */
	public Client(String ipAddress, DnsTree root) {
		this.ipAddress = ipAddress;
		this.root = root;
		cacheList = new CachedContent[10];
	}
	
	/**
	 * Sends a request to get ip address of given domain name. If no such domain name exists, returns null.
	 * Firstly looks to cache to find the ip address. If it is not in cache, looks to dns tree.
	 * @param domainName the domain name the ip address of which is searched
	 * @return ip address as string if that domain name exist. Else null.
	 */
	public String sendRequest(String domainName) {
		for(int i = 0; i < 10 && cacheList[i] != null; i++) {
			if(cacheList[i].domainName.equals(domainName)) {
				cacheList[i].hitNo ++;
				return cacheList[i].ipAddress;
			}
		}
		String result = root.queryDomain(domainName);
		if(result!=null) {
			addToCache(domainName, result);
		}
		return result;
	}
	
	/**
	 * Adds the given domain name and ip address to cache as an CachedContent object.
	 * @param domainName the domain name which is wanted to be added to the cache
	 * @param ipAddress the ip address which is wanted to be added to the cache
	 */
	public void addToCache(String domainName, String ipAddress) {
		int iter = 0;
		int value = Integer.MAX_VALUE;
		for(int i=0; i<10; i++) {
			if(cacheList[i]!=null) {
				if(cacheList[i].hitNo < value) {
					value = cacheList[i].hitNo;
					iter = i;
				}
			}
			else {
				cacheList[i] = new CachedContent();
				cacheList[i].domainName = domainName;
				cacheList[i].ipAddress = ipAddress;
				return;
			}	
		}
		cacheList[iter].hitNo = 1;
		cacheList[iter].domainName = domainName;
		cacheList[iter].ipAddress = ipAddress;
	}
	
	
	//delete it
	public void write() {
		System.out.printf("client (%s)\n", ipAddress);
		for(int i=0; i<10 && cacheList[i]!= null; i++) {
			System.out.printf("%d. %s - %s (%d)\n", i, cacheList[i].domainName, cacheList[i].ipAddress, cacheList[i].hitNo);
		}
		System.out.println();
	}
	
	
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

