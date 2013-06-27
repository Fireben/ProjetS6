package educatus.server.businesslogic.seminarymanager;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.businesslogic.FileUploadCacheManager;
import educatus.server.persist.dao.DynamicContentDao;
import educatus.server.persist.dao.InternationalizationDao;
import educatus.server.persist.dao.SecurityDao;
import educatus.server.persist.dao.SeminaryDao;
import educatus.server.persist.dao.dynamiccontent.DynamicContent;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionAlignment;
import educatus.server.persist.dao.internationalization.ImageContentTranslationEntry;
import educatus.server.persist.dao.internationalization.ImageInternal;
import educatus.server.persist.dao.internationalization.TextContentTranslationEntry;
import educatus.server.persist.dao.security.User;
import educatus.server.persist.dao.seminary.Difficulty;
import educatus.server.persist.dao.seminary.Seminary;
import educatus.shared.dto.dynamiccontent.AbstractDynamicSection;
import educatus.shared.dto.dynamiccontent.AbstractDynamicSection.DynamicSectionType;
import educatus.shared.dto.dynamiccontent.DynamicSectionImageContent;
import educatus.shared.dto.dynamiccontent.DynamicSectionTextContent;
import educatus.shared.dto.seminary.SeminaryContent;

@Singleton
public class SeminaryAdministrationManager {

	private static String EN_LANG = "en";
	private static String CA_CULT = "CA";

	@Inject
	SeminaryDao seminaryDao;
	@Inject
	DynamicContentDao dynamicContentDao;
	@Inject
	InternationalizationDao internationalizationDao;
	@Inject
	SecurityDao securityDao;
	@Inject
	EntityManager manager;

	public void insertSeminary(SeminaryContent content) throws Exception {

		manager.getTransaction().begin();

		DynamicContent seminaryDynamicContent = dynamicContentDao.createDynamicContent();
		DynamicSectionAlignment centerAlignment = manager.find(DynamicSectionAlignment.class, 1);

		int sequenceId = 0;
		for (AbstractDynamicSection dynamicSection : content.getDynamicSectionList()) {

			if (dynamicSection.getSectionType() == DynamicSectionType.TEXT_SECTION) {
				DynamicSectionTextContent textContent = (DynamicSectionTextContent) dynamicSection;

				TextContentTranslationEntry textSectionTitle = internationalizationDao.insertTextContentTranslationEntry(
						EN_LANG, CA_CULT, textContent.getTitle());
				TextContentTranslationEntry textSectionText = internationalizationDao.insertTextContentTranslationEntry(
						EN_LANG, CA_CULT, textContent.getText());

				dynamicContentDao.addDynamicSectionText(
						seminaryDynamicContent.getId(),
						textSectionTitle.getTextcontententry().getId(),
						textSectionText.getTextcontententry().getId(),
						centerAlignment.getId(),
						sequenceId
						);

			} else if (dynamicSection.getSectionType() == DynamicSectionType.IMAGE_SECTION) {
				DynamicSectionImageContent imageContent = (DynamicSectionImageContent) dynamicSection;

				String description = "";
				if (imageContent.getImageDescription() != null) {
					description = imageContent.getImageDescription();
				}
				
				TextContentTranslationEntry imageDescription = internationalizationDao.insertTextContentTranslationEntry(
						EN_LANG, CA_CULT, description);

				BufferedImage bufferedImage = FileUploadCacheManager.getInstance().removeImage(imageContent.getImageUrl());
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
				byteArrayOutputStream.flush();
				byte[] imageByteArray = byteArrayOutputStream.toByteArray();
				byteArrayOutputStream.close();

				ImageInternal imageInternal = internationalizationDao.insertImageInternal(description, imageByteArray);
				ImageContentTranslationEntry imageEntry = internationalizationDao.insertImageTranslationEntry(EN_LANG, CA_CULT, imageInternal.getId());

				dynamicContentDao.addDynamicSectionImage(
						seminaryDynamicContent.getId(),
						imageDescription.getTextcontententry().getId(),
						imageEntry.getImagecontententry().getId(),
						centerAlignment.getId(),
						sequenceId
						);

			} else if (dynamicSection.getSectionType() == DynamicSectionType.VIDEO_SECTION) {

			} else if (dynamicSection.getSectionType() == DynamicSectionType.FORMULA_SECTION) {

			}

			sequenceId++;
		}

		User user = securityDao.findUserByCip("beam1711");
		Difficulty difficulty = seminaryDao.findDifficultyByLevel(1);

		TextContentTranslationEntry seminaryTitle = internationalizationDao.insertTextContentTranslationEntry(
				EN_LANG, CA_CULT, content.getCoreContent().getTitle());
		TextContentTranslationEntry seminaryDesc = internationalizationDao.insertTextContentTranslationEntry(
				EN_LANG, CA_CULT, content.getCoreContent().getDescription());

		Seminary seminary = seminaryDao.insertSeminary(
				seminaryDynamicContent.getId(),
				seminaryTitle.getTextcontententry().getId(),
				seminaryDesc.getTextcontententry().getId(),
				user.getId(),
				difficulty.getDifficultyValue()
				);

		seminary = seminaryDao.addCategoryToSeminary(seminary.getSemiId(), 47);

		manager.getTransaction().commit();
		manager.getEntityManagerFactory().getCache().evictAll();
	}
}
