package educatus.server.businesslogic.uibuilder;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.persist.dao.InternationalizationDao;
import educatus.server.persist.dao.internationalization.ImageExternal;
import educatus.server.persist.dao.internationalization.TextContentTranslationEntry;
import educatus.shared.dto.HomePageContent;
import educatus.shared.dto.HomePageContent.HomePageSection;

/* http://code.google.com/p/google-web-toolkit/source/browse/trunk/samples/showcase/src/com/google/gwt/sample/showcase/client/ShowcaseShell.java?r=9045
 * ligne 322 */

@Singleton
public class HomePageContentBuilder {
	/* Text */
	private static final int HOME_TITLE = -10000;
	private static final int WELCOME_TITLE = -10001;
	private static final int WELCOME_DESCRIPTION_1 = -10002;
	private static final int WELCOME_DESCRIPTION_2 = -10003;
	private static final int WELCOME_DESCRIPTION_3 = -10004;
	private static final int WELCOME_BUTTON_TEXT = -10005;

	private static final int SEMINAR_SECTION_TITLE = -10006;
	private static final int SEMINAR_SECTION_TEXT = -10007;
	private static final int SEMINAR_SECTION_DESCRIPTION = -10008;
	private static final int SEMINAR_SECTION_LINK_TEXT = -10009;
	
	private static final int PROBLEM_SECTION_TITLE = -10010;
	private static final int PROBLEM_SECTION_TEXT = -10011;
	private static final int PROBLEM_SECTION_DESCRIPTION = -10012;
	private static final int PROBLEM_SECTION_LINK_TEXT = -10013;
	
	private static final int STATS_SECTION_TITLE = -10014;
	private static final int STATS_SECTION_TEXT = -10015;
	private static final int STATS_SECTION_DESCRIPTION = -10016;
	private static final int STATS_SECTION_LINK_TEXT = -10017;
	
	private static final int FORUM_SECTION_TITLE = -10018;
	private static final int FORUM_SECTION_TEXT = -10019;
	private static final int FORUM_SECTION_DESCRIPTION = -10020;
	private static final int FORUM_SECTION_LINK_TEXT = -10021;
	
	/* Images */
	private static final int WELCOME_IMAGE = -10000;
	private static final int SEMINAR_SECTION_IMAGE = -10001;
	private static final int PROBLEM_SECTION_IMAGE = -10002;
	private static final int STATS_SECTION_IMAGE = -10003;
	private static final int FORUM_SECTION_IMAGE = -10004;
	
	@Inject
	private InternationalizationDao interDao;

	public HomePageContent buildHomePageContent(String culture, String language) {
		int cultureId;
		int languageId;
		try {
			cultureId = interDao.findCultureByCode(culture).getId();
			languageId = interDao.findLanguageByCode(language).getId();
	
		} catch (Exception e) {
			// TODO Manage Exceptions
			e.printStackTrace();
			return null;
		}

		HomePageContent content = new HomePageContent();

		TextContentTranslationEntry textContentTranslationEntry = null;
		ImageExternal imageExternal = null;

		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, HOME_TITLE);
		content.setTitle(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());
		imageExternal = interDao.findImageById(WELCOME_IMAGE);
		content.setWelcomeImage(imageExternal == null ? "" : imageExternal.getUrl());
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, WELCOME_TITLE);
		content.setWelcomeTitle(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, WELCOME_DESCRIPTION_1);
		content.setWelcomeDescription1(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, WELCOME_DESCRIPTION_2);
		content.setWelcomeDescription2(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, WELCOME_DESCRIPTION_3);
		content.setWelcomeDescription3(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, WELCOME_BUTTON_TEXT);
		content.setWelcomeButtonText(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());

		HomePageSection seminarsSection = new HomePageSection();

		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, SEMINAR_SECTION_TITLE);
		seminarsSection.setSectionTitle(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, SEMINAR_SECTION_TEXT);
		seminarsSection.setSectionText(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());
		imageExternal = interDao.findImageById(SEMINAR_SECTION_IMAGE);
		seminarsSection.setSectionImg(imageExternal == null ? "" : imageExternal.getUrl());
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, SEMINAR_SECTION_DESCRIPTION);
		seminarsSection.setSectionDescription(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, SEMINAR_SECTION_LINK_TEXT);
		seminarsSection.setSectionLinkText(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());
		content.setSeminarsSection(seminarsSection);
		
		HomePageSection problemsSection = new HomePageSection();
		
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, PROBLEM_SECTION_TITLE);
		problemsSection.setSectionTitle(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, PROBLEM_SECTION_TEXT);
		problemsSection.setSectionText(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());
		imageExternal = interDao.findImageById(PROBLEM_SECTION_IMAGE);
		problemsSection.setSectionImg(imageExternal == null ? "" : imageExternal.getUrl());
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, PROBLEM_SECTION_DESCRIPTION);
		problemsSection.setSectionDescription(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, PROBLEM_SECTION_LINK_TEXT);
		problemsSection.setSectionLinkText(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());
		content.setProblemsSection(problemsSection);
		
		HomePageSection statisticsSection = new HomePageSection();
		
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, STATS_SECTION_TITLE);
		statisticsSection.setSectionTitle(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, STATS_SECTION_TEXT);
		statisticsSection.setSectionText(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());
		imageExternal = interDao.findImageById(STATS_SECTION_IMAGE);
		statisticsSection.setSectionImg(imageExternal == null ? "" : imageExternal.getUrl());
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, STATS_SECTION_DESCRIPTION);
		statisticsSection.setSectionDescription(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, STATS_SECTION_LINK_TEXT);
		statisticsSection.setSectionLinkText(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());
		content.setStatisticsSection(statisticsSection);
		
		HomePageSection forumsSection = new HomePageSection();
		
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, FORUM_SECTION_TITLE);
		forumsSection.setSectionTitle(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, FORUM_SECTION_TEXT);
		forumsSection.setSectionText(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());
		imageExternal = interDao.findImageById(FORUM_SECTION_IMAGE);
		forumsSection.setSectionImg(imageExternal == null ? "" : imageExternal.getUrl());
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, FORUM_SECTION_DESCRIPTION);
		forumsSection.setSectionDescription(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, FORUM_SECTION_LINK_TEXT);
		forumsSection.setSectionLinkText(textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation());
		content.setForumsSection(forumsSection);
			
		return content;
	}
}
