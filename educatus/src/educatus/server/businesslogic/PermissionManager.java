package educatus.server.businesslogic;

import java.util.HashMap;
import java.util.HashSet;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.persist.dao.SecurityDao;
import educatus.server.persist.dao.security.Group;
import educatus.server.persist.dao.security.Permission;
import educatus.server.persist.dao.security.User;

@Singleton
public class PermissionManager {

	@Inject
	private EntityManager entityManager;

	private HashMap<Integer, HashSet<Integer>> mapSet = null;

	public boolean AddUserToGroup(User user, Group group) {
		boolean toReturnValue = false;

		entityManager.getTransaction().begin();
		if (!user.getAssociatedGroupList().contains(group)) {
			toReturnValue= user.getAssociatedGroupList().add(group);			
		}
		entityManager.merge(user);
		entityManager.getTransaction().commit();

		this.UpdatePermissionSet(user);

		return toReturnValue;
	}
	public boolean RemoveUserFromGroup(User user, Group group) {
		boolean toReturnValue = false;

		entityManager.getTransaction().begin();
		toReturnValue = user.getAssociatedGroupList().remove(group);			
		entityManager.merge(user);
		entityManager.getTransaction().commit();

		this.UpdatePermissionSet(user);

		return toReturnValue;
	}

	public boolean UserAddPermission(User user, Permission permission) {
		boolean toReturnValue = false;

		entityManager.getTransaction().begin();
		toReturnValue = this.InnerUserAddPermission(user, permission);
		entityManager.merge(user);
		entityManager.getTransaction().commit();

		this.UpdatePermissionSet(user);

		return toReturnValue;
	}
	public boolean UserAddPermission(User user, PermissionConstant permission) {
		return this.UserAddPermission(user, entityManager.find(Permission.class, permission.getId()));
	}
	public boolean UserRemovePermission(User user, Permission permission) {
		boolean toReturnValue = false;

		entityManager.getTransaction().begin();
		toReturnValue = this.InnerUserRemovePermission(user, permission);
		entityManager.merge(user);
		entityManager.getTransaction().commit();

		this.UpdatePermissionSet(user);

		return toReturnValue;
	}
	public boolean UserRemovePermission(User user, PermissionConstant permission) {
		return this.UserRemovePermission(user, entityManager.find(Permission.class, permission.getId()));
	}
	public boolean UserAddPermission(User user, Permission[] permissions) {
		boolean toReturnValue = false;

		entityManager.getTransaction().begin();
		for (Permission permission : permissions) {
			toReturnValue = toReturnValue
					|| this.InnerUserAddPermission(user, permission);
		}
		entityManager.merge(user);
		entityManager.getTransaction().commit();

		this.UpdatePermissionSet(user);

		return toReturnValue;
	}
	public boolean UserRemovePermission(User user, Permission[] permissions) {
		boolean toReturnValue = false;

		entityManager.getTransaction().begin();
		for (Permission permission : permissions) {
			toReturnValue = toReturnValue
					|| this.InnerUserRemovePermission(user, permission);
		}
		entityManager.merge(user);
		entityManager.getTransaction().commit();

		this.UpdatePermissionSet(user);

		return toReturnValue;
	}

	private boolean InnerUserAddPermission(User user, Permission permission) {
		if (!user.getAssociatedPermissionList().contains(permission)) {
			return user.getAssociatedPermissionList().add(permission);
		}
		return false;
	}
	private boolean InnerUserRemovePermission(User user, Permission permission) {
		return user.getAssociatedPermissionList().remove(permission);
	}

	private HashSet<Integer> GetPermissionSet(User user) {
		if (this.mapSet == null) {
			this.mapSet = new HashMap<Integer, HashSet<Integer>>();
		}

		if (!this.mapSet.containsKey(user.getId())) {
			this.mapSet.put(user.getId(), this.GeneratePermissionSet(user));
		}
		return (HashSet<Integer>) this.mapSet.get(user.getId());
	}

	private void UpdatePermissionSet(User user) {
		if (this.mapSet == null) {
			this.mapSet = new HashMap<Integer, HashSet<Integer>>();
		}
		this.mapSet.put(user.getId(), this.GeneratePermissionSet(user));
	}

	private HashSet<Integer> GeneratePermissionSet(User user) {
		HashSet<Integer> permissionSet = new HashSet<Integer>();

		for (Permission permission : user.getAssociatedPermissionList()) {
			if (!permissionSet.contains(permission.getId())) {
				permissionSet.add(permission.getId());
			}
		}

		for (Group group : user.getAssociatedGroupList()) {
			for (Permission permission : group.getAssociatedPermissionList()) {
				if (!permissionSet.contains(permission.getId())) {
					permissionSet.add(permission.getId());
				}
			}
		}

		return permissionSet;
	}

	public boolean HavePermission(User user, PermissionConstant permissionCode) {
		HashSet<Integer> userPermissionSet = this.GetPermissionSet(user);

		return userPermissionSet.contains(permissionCode.getId());
	}
}
