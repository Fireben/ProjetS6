package educatus;

import javax.persistence.EntityManager;

import com.google.inject.Guice;
import com.google.inject.Injector;

import educatus.server.persist.JpaInitializer;
import educatus.server.persist.dao.DaoModule;
import educatus.server.persist.dao.InternationalizationDao;
import educatus.server.persist.dao.SeminaryDao;
import educatus.server.persist.dao.internationalization.ImageExternal;
import educatus.server.persist.dao.internationalization.TextContentTranslationEntry;
import educatus.server.persist.dao.seminary.Category;

public class GenerateCategories {

	private static String EN_LANG = "en";
	private static String CA_CULT = "CA";
		
	public static void main(String[] args) {
		
		InternationalizationDao internationalizationDao = null;
		SeminaryDao seminaryDao = null;
		EntityManager manager = null;
		
		Injector dbInjector = Guice.createInjector(new DaoModule("db-manager-localhost"));	 
		dbInjector.getInstance(JpaInitializer.class);
		internationalizationDao = dbInjector.getInstance(InternationalizationDao.class);
		seminaryDao = dbInjector.getInstance(SeminaryDao.class);
		manager = dbInjector.getInstance(EntityManager.class);
				
		try {
			
			// We should remove all categories
			
			// Computer engineering parent = null = top level categorys
			TextContentTranslationEntry computerEngineeringCategoryName = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "Computer Engineering");
			TextContentTranslationEntry computerEngineeringCategoryDesc = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "Computer Engineering");
			ImageExternal computerEngineeringCategoryIcon = internationalizationDao.insertImageExternal("computerEngineeringIcon");
			Category computerEngineeringCategory = seminaryDao.createNewCategory(computerEngineeringCategoryName.getTextcontententry().getId(), computerEngineeringCategoryDesc.getTextcontententry().getId(), computerEngineeringCategoryIcon.getId(), null);
			
			// Software parent = Computer Engineering
			TextContentTranslationEntry softwareCategoryName = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "Software");
			TextContentTranslationEntry softwareCategoryDesc = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "Software");
			ImageExternal softwareCategoryIcon = internationalizationDao.insertImageExternal("softwareIcon");
			Category softwareCategory = seminaryDao.createNewCategory(softwareCategoryName.getTextcontententry().getId(), softwareCategoryDesc.getTextcontententry().getId(), softwareCategoryIcon.getId(), computerEngineeringCategory.getId());
						
			// Language parent = Software
			TextContentTranslationEntry languageCategoryName = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "Language");
			TextContentTranslationEntry languageCategoryDesc = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "Language");
			ImageExternal languageCategoryIcon = internationalizationDao.insertImageExternal("languageIcon");
			Category languageCategory = seminaryDao.createNewCategory(languageCategoryName.getTextcontententry().getId(), languageCategoryDesc.getTextcontententry().getId(), languageCategoryIcon.getId(), softwareCategory.getId());
						
			// Javascript parent = Language
			TextContentTranslationEntry javascriptCategoryName = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "Javascript");
			TextContentTranslationEntry javascriptCategoryDesc = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "Javascript");
			ImageExternal javascriptCategoryIcon = internationalizationDao.insertImageExternal("javascriptIcon");
			Category javascriptCategory = seminaryDao.createNewCategory(javascriptCategoryName.getTextcontententry().getId(), javascriptCategoryDesc.getTextcontententry().getId(), javascriptCategoryIcon.getId(), languageCategory.getId());
			
			// Java parent = Language
			TextContentTranslationEntry javaCategoryName = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "Java");
			TextContentTranslationEntry javaCategoryDesc = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "Java");
			ImageExternal javaCategoryIcon = internationalizationDao.insertImageExternal("javaIcon");
			Category javaCategory = seminaryDao.createNewCategory(javaCategoryName.getTextcontententry().getId(), javaCategoryDesc.getTextcontententry().getId(), javaCategoryIcon.getId(), languageCategory.getId());
			
			// C parent = Language
			TextContentTranslationEntry cCategoryName = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "C");
			TextContentTranslationEntry cCategoryDesc = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "C");
			ImageExternal cCategoryIcon = internationalizationDao.insertImageExternal("cIcon");
			Category cCategory = seminaryDao.createNewCategory(cCategoryName.getTextcontententry().getId(), cCategoryDesc.getTextcontententry().getId(), cCategoryIcon.getId(), languageCategory.getId());
			
			// C++ parent = Language
			TextContentTranslationEntry cppCategoryName = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "C++");
			TextContentTranslationEntry cppCategoryDesc = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "C++");
			ImageExternal cppCategoryIcon = internationalizationDao.insertImageExternal("cppIcon");
			Category cppCategory = seminaryDao.createNewCategory(cppCategoryName.getTextcontententry().getId(), cppCategoryDesc.getTextcontententry().getId(), cppCategoryIcon.getId(), languageCategory.getId());
			
			// C# parent = Language
			TextContentTranslationEntry csharpCategoryName = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "C#");
			TextContentTranslationEntry csharpCategoryDesc = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "C#");
			ImageExternal csharpCategoryIcon = internationalizationDao.insertImageExternal("csharpIcon");
			Category csharpCategory = seminaryDao.createNewCategory(csharpCategoryName.getTextcontententry().getId(), csharpCategoryDesc.getTextcontententry().getId(), csharpCategoryIcon.getId(), languageCategory.getId());
			
			// Algorithm parent = Software
			TextContentTranslationEntry algorithmCategoryName = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "Algorithm");
			TextContentTranslationEntry algorithmCategoryDesc = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "Algorithm");
			ImageExternal algorithmCategoryIcon = internationalizationDao.insertImageExternal("algorithmIcon");
			Category algorithmCategory = seminaryDao.createNewCategory(algorithmCategoryName.getTextcontententry().getId(), algorithmCategoryDesc.getTextcontententry().getId(), algorithmCategoryIcon.getId(), softwareCategory.getId());
			
			// OOP parent = Software
			TextContentTranslationEntry oopCategoryName = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "OOP");
			TextContentTranslationEntry oopCategoryDesc = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "OOP");
			ImageExternal oopCategoryIcon = internationalizationDao.insertImageExternal("oopIcon");
			Category oopCategory = seminaryDao.createNewCategory(oopCategoryName.getTextcontententry().getId(), oopCategoryDesc.getTextcontententry().getId(), oopCategoryIcon.getId(), softwareCategory.getId());
			
			// Embedded parent = Software
			TextContentTranslationEntry embeddedCategoryName = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "Embedded");
			TextContentTranslationEntry embeddedCategoryDesc = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "Embedded");
			ImageExternal embeddedCategoryIcon = internationalizationDao.insertImageExternal("embeddedIcon");
			Category embeddedCategory = seminaryDao.createNewCategory(embeddedCategoryName.getTextcontententry().getId(), embeddedCategoryDesc.getTextcontententry().getId(), embeddedCategoryIcon.getId(), softwareCategory.getId());
			
			// Hardware parent = Computer Engineering
			TextContentTranslationEntry hardwareCategoryName = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "Hardware");
			TextContentTranslationEntry hardwareCategoryDesc = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, "Hardware");
			ImageExternal hardwareCategoryIcon = internationalizationDao.insertImageExternal("hardwareIcon");
			Category hardwareCategory = seminaryDao.createNewCategory(hardwareCategoryName.getTextcontententry().getId(), hardwareCategoryDesc.getTextcontententry().getId(), hardwareCategoryIcon.getId(), computerEngineeringCategory.getId());
						
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
