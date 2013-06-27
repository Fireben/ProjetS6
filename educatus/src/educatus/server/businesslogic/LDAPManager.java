package educatus.server.businesslogic;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class LDAPManager {
	private static final String url = "ldaps://ldap.usherbrooke.ca:636";
	private static final String initial = "com.sun.jndi.ldap.LdapCtxFactory";
	private static LDAPManager instance = new LDAPManager();

	public static LDAPManager getInstance() {
		return instance;
	}

	private LdapContext ldapContext = null;

	private LDAPManager() {

	}

	public boolean authenticate(String cip, String password) {
		try {
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, initial);
			env.put(Context.PROVIDER_URL, url);
			env.put(Context.SECURITY_PRINCIPAL, cip);
			env.put(Context.SECURITY_CREDENTIALS, password);
			ldapContext = new InitialLdapContext(env, null);
			return true;
		} catch (NamingException e) {
			e.printStackTrace();
			return false;
		}
	}

	public LDAPUser findUser(String cip) throws Exception {
		if (ldapContext == null)
			throw new Exception("Utilisation de LDAP sans authentification");

		Attributes answer;
		try {
			answer = ldapContext.getAttributes("uid=" + cip + ",ou=personnes,dc=USherbrooke,dc=ca");
			LDAPUser u = new LDAPUser();
			u.setCip(cip);
			for (NamingEnumeration<?> ne2 = answer.getAll(); ne2.hasMore();) {
				Attribute attribute = (Attribute) ne2.next();
				for (NamingEnumeration<?> e = attribute.getAll(); e.hasMore();) {
					String value = (String) e.next();
					if (attribute.getID().equals("sn")) {
						u.setLastName(value);
						break;
					}
					if (attribute.getID().equals("mail")) {
						u.setCourriel(value);
						break;
					}
					if (attribute.getID().equals("facsimileTelephoneNumber")) {
						u.setFax(value);
						break;
					}
					if (attribute.getID().equals("cn")) {
						u.setFullName(value);
						break;
					}
					if (attribute.getID().equals("givenName")) {
						u.setFirstName(value);
						break;
					}
					if (attribute.getID().equals("roomNumber")) {
						u.setRoom(value);
						break;
					}
					if (attribute.getID().equals("title")) {
						u.setStatus(value);
						break;
					}
					if (attribute.getID().equals("telephoneNumber")) {
						u.setPhone(value);
						break;
					}
					if (attribute.getID().equals("personalTitle")) {
						u.setTitre(value);
						break;
					}
					if (attribute.getID().equals("ou")) {
						u.setUnity(value);
						break;
					}
				}
			}
			return u;
		} catch (NamingException e) {
			throw e;
		}
	}
}
