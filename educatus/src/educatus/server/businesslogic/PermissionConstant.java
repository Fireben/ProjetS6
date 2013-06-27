package educatus.server.businesslogic;

public enum PermissionConstant {
	internationalization_culture_create(1,PermissionType.internationalization), 
	internationalization_culture_modify(2,PermissionType.internationalization),
	internationalization_culture_delete(3,PermissionType.internationalization),
	internationalization_language_create(4,PermissionType.internationalization),
	internationalization_language_modify(5,PermissionType.internationalization),
	internationalization_language_delete(6,PermissionType.internationalization),
	internationalization_available_language_culture_create(7,PermissionType.internationalization),
	internationalization_available_language_culture_delete(8,PermissionType.internationalization),
	internationalization_translation_modify(9,PermissionType.internationalization),
	security_users_create(51,PermissionType.security),
	security_users_modify(52,PermissionType.security),
	security_users_modify_own(53,PermissionType.security), 
	security_users_delete(54,PermissionType.security),
	security_users_delete_own(55,PermissionType.security), 
	security_users_access(56,PermissionType.security),
	security_users_access_own(57,PermissionType.security), 
	security_users_access_other(58,PermissionType.security),
	security_role_create(59,PermissionType.security),
	security_role_modify(60,PermissionType.security),
	security_role_delete(61,PermissionType.security),
	security_groups_create(62,PermissionType.security),
	security_groups_modify(63,PermissionType.security),
	security_groups_delete(64,PermissionType.security),
	security_users_permission_add(65,PermissionType.security),
	security_users_permission_remove(66,PermissionType.security),
	security_role_permission_add(67,PermissionType.security),
	security_role_permission_remove(68,PermissionType.security),
	security_groups_permission_add(69,PermissionType.security),
	security_groups_permission_remove(70,PermissionType.security),
	security_groups_role_add(71,PermissionType.security),
	security_groups_role_remove(72,PermissionType.security),
	seminary_category_create(101,PermissionType.seminary),
	seminary_category_modify(102,PermissionType.seminary),
	seminary_category_delete(103,PermissionType.seminary),
	seminary_competence_create(104,PermissionType.seminary),
	seminary_competence_modify(105,PermissionType.seminary),
	seminary_competence_delete(106,PermissionType.seminary),
	seminary_difficulty_create(107,PermissionType.seminary),
	seminary_difficulty_modify(108,PermissionType.seminary),
	seminary_difficulty_delete(109,PermissionType.seminary),
	seminary_seminary_create(110,PermissionType.seminary),
	seminary_seminary_modify(111,PermissionType.seminary),
	seminary_seminary_modify_own(112,PermissionType.seminary),
	seminary_seminary_modify_other(113,PermissionType.seminary),
	seminary_seminary_delete(114,PermissionType.seminary),
	seminary_seminary_delete_own(115,PermissionType.seminary),
	seminary_seminary_delete_other(116,PermissionType.seminary),
	seminary_seminary_access(117,PermissionType.seminary),
	seminary_statistic_access(118,PermissionType.seminary),
	seminary_seminary_save_progress(119,PermissionType.seminary),
	exercice_exercice_create(151,PermissionType.exercice), 
	exercice_exerciceQuestion_create(152,PermissionType.exercice),
	exercice_anwser_create(153,PermissionType.exercice),
	exercice_exercice_modify(154,PermissionType.exercice), 
	exercice_exercice_modify_own(155,PermissionType.exercice),
	exercice_exercice_modify_other(156,PermissionType.exercice),
	exercice_exerciceQuestion_modify(157,PermissionType.exercice),
	exercice_anwser_modify(158,PermissionType.exercice),
	exercice_exercice_delete(159,PermissionType.exercice), 
	exercice_exerciceQuestion_delete(160,PermissionType.exercice),
	exercice_anwser_delete(161,PermissionType.exercice),
	exercice_exercice_access(162,PermissionType.exercice),
	exercice_exercice_save_progress(163,PermissionType.exercice),
	exercice_exercice_delete_own(164,PermissionType.exercice),
	exercice_exercice_delete_other(165,PermissionType.exercice),
	achievement_achievement_create(201,PermissionType.achievement),
	achievement_achievement_modify(202,PermissionType.achievement),
	achievement_achievement_delete(203,PermissionType.achievement),
	certificate_certificate_create(251,PermissionType.certificate),
	certificate_certificate_modify(252,PermissionType.certificate),
	certificate_certificate_delete(253,PermissionType.certificate);

	/* Members */
	private final int id;
	private final PermissionType type;

	/* Constructors */
	private PermissionConstant(int id, PermissionType type) {
		this.id = id;
		this.type = type;
	}
	
	/* Getters/Setters */
	public int getId() {
		return id;
	}
	public PermissionType getType(){
		return type;
	}
}
