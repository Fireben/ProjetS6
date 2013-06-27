package educatus;

import educatus.server.businesslogic.LDAPManager;
import educatus.server.businesslogic.LDAPUser;

public class LDAPTest {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean test = LDAPManager.getInstance().authenticate("uid=geee9001", "Min0t0r3$");
		
		try {
			LDAPUser user = LDAPManager.getInstance().findUser("graj2308");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
