package educatus.server.businesslogic;

public enum PermissionConstant {
	internationalization_culture_create(1), 
	internationalization_culture_modify(2),
	internationalization_culture_delete(3),
	internationalization_language_create(4),
	internationalization_language_modify(5),
	internationalization_language_delete(6),
	internationalization_available_language_culture_create(7),
	internationalization_available_language_culture_delete(8),
	internationalization_translation_modify(9),
	security_users_create(51),
	security_users_modify(52),
	security_users_modify_own(53), 
	security_users_delete(54),
	security_users_delete_own(55), 
	security_users_access(56),
	security_users_access_own(57), 
	security_users_access_other(58),
	security_role_create(59),
	security_role_modify(60),
	security_role_delete(61),
	security_groups_create(62),
	security_groups_modify(63),
	security_groups_delete(64),
	security_users_permission_add(65),
	security_users_permission_remove(66),
	security_role_permission_add(67),
	security_role_permission_remove(68),
	security_groups_permission_add(69),
	security_groups_permission_remove(70),
	security_groups_role_add(71),
	security_groups_role_remove(72),
	seminary_category_create(101),
	seminary_category_modify(102),
	seminary_category_delete(103),
	seminary_competence_create(104),
	seminary_competence_modify(105),
	seminary_competence_delete(106),
	seminary_difficulty_create(107),
	seminary_difficulty_modify(108),
	seminary_difficulty_delete(109),
	seminary_seminary_create(110),
	seminary_seminary_modify(111),
	seminary_seminary_modify_own(112),
	seminary_seminary_modify_other(113),
	seminary_seminary_delete(114),
	seminary_seminary_delete_own(115),
	seminary_seminary_delete_other(116),
	seminary_seminary_access(117),
	exercice_exercice_create(151), 
	exercice_exerciceQuestion_create(152),
	exercice_anwser_create(153),
	exercice_exercice_modify(154), 
	exercice_exercice_modify_own(155),
	exercice_exercice_modify_other(156),
	exercice_exerciceQuestion_modify(157),
	exercice_anwser_modify(158),
	exercice_exercice_delete(159), 
	exercice_exerciceQuestion_delete(160),
	exercice_anwser_delete(161),
	exercice_exercice_access(162),
	achievement_achievement_create(201),
	achievement_achievement_modify(202),
	achievement_achievement_delete(203),
	certificate_certificate_create(251),
	certificate_certificate_modify(252),
	certificate_certificate_delete(253);

	/* Members */
	private final int id;

	/* Constructors */
	private PermissionConstant(int id) {
		this.id = id;
	}
	
	/* Getters/Setters */
	public int getId() {
		return id;
	}
}
