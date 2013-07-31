package educatus.server.businesslogic.exercicemanager;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.businesslogic.FileUploadCacheManager;
import educatus.server.persist.dao.DynamicContentDao;
import educatus.server.persist.dao.ExerciceDao;
import educatus.server.persist.dao.InternationalizationDao;
import educatus.server.persist.dao.SecurityDao;
import educatus.server.persist.dao.SeminaryDao;
import educatus.server.persist.dao.dynamiccontent.DynamicContent;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionAlignment;
import educatus.server.persist.dao.exercice.Exercice;
import educatus.server.persist.dao.internationalization.ImageContentTranslationEntry;
import educatus.server.persist.dao.internationalization.ImageInternal;
import educatus.server.persist.dao.internationalization.TextContentTranslationEntry;
import educatus.server.persist.dao.security.User;
import educatus.server.persist.dao.seminary.Difficulty;
import educatus.shared.dto.dynamiccontent.AbstractDynamicSection;
import educatus.shared.dto.dynamiccontent.AbstractDynamicSection.DynamicSectionType;
import educatus.shared.dto.dynamiccontent.DynamicSectionImageContent;
import educatus.shared.dto.dynamiccontent.DynamicSectionPDFContent;
import educatus.shared.dto.dynamiccontent.DynamicSectionTextContent;
import educatus.shared.dto.exercice.AnswerChoiceContent;
import educatus.shared.dto.exercice.AnswerNumericContent;
import educatus.shared.dto.exercice.AnswerTextContent;
import educatus.shared.dto.exercice.ExerciceContent;
import educatus.shared.dto.exercice.ExerciceQuestionContent;

@Singleton
public class ExerciceAdministrationManager {

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
	@Inject
	ExerciceDao exerciceDao;

	public void insertExercice(ExerciceContent exerciceContent) throws Exception {

		manager.getTransaction().begin();

		User user = securityDao.findUserByCip("beam1711");
		Difficulty difficulty = seminaryDao.findDifficultyByLevel(exerciceContent.getCoreContent().getDifficultyValue());

		TextContentTranslationEntry exerciceTitleTcte = internationalizationDao.insertTextContentTranslationEntry(
				EN_LANG, CA_CULT, exerciceContent.getCoreContent().getTitle());
		TextContentTranslationEntry exerciceDescriptionTcte = internationalizationDao.insertTextContentTranslationEntry(
				EN_LANG, CA_CULT, exerciceContent.getCoreContent().getDescription());

		Exercice exercice = exerciceDao.insertExercice(
				exerciceTitleTcte.getTextcontententry().getId(),
				exerciceDescriptionTcte.getTextcontententry().getId(),
				user.getId(),
				difficulty.getDifficultyValue()
				);

		int exerciceQuestionContentSequence = 0;
		for (ExerciceQuestionContent exerciceQuestionContent : exerciceContent.getQuestionList()) {

			// TODO, duplication du code de seminary administration manager
			DynamicContent dycoContent = dynamicContentDao.createDynamicContent();
			DynamicSectionAlignment centerAlignment = manager.find(DynamicSectionAlignment.class, 1);

			int dycoSequence = 0;
			for (AbstractDynamicSection dynamicSection : exerciceQuestionContent.getQuestionContext()) {

				if (dynamicSection.getSectionType() == DynamicSectionType.TEXT_SECTION) {
					DynamicSectionTextContent textContent = (DynamicSectionTextContent) dynamicSection;

					TextContentTranslationEntry textSectionTitle = internationalizationDao.insertTextContentTranslationEntry(
							EN_LANG, CA_CULT, textContent.getTitle());
					TextContentTranslationEntry textSectionText = internationalizationDao.insertTextContentTranslationEntry(
							EN_LANG, CA_CULT, textContent.getText());

					dynamicContentDao.addDynamicSectionText(
							dycoContent.getId(),
							textSectionTitle.getTextcontententry().getId(),
							textSectionText.getTextcontententry().getId(),
							centerAlignment.getId(),
							dycoSequence
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
							dycoContent.getId(),
							imageDescription.getTextcontententry().getId(),
							imageEntry.getImagecontententry().getId(),
							centerAlignment.getId(),
							dycoSequence
							);

				} else if (dynamicSection.getSectionType() == DynamicSectionType.VIDEO_SECTION) {

				} else if (dynamicSection.getSectionType() == DynamicSectionType.FORMULA_SECTION) {

				} else if (dynamicSection.getSectionType() == DynamicSectionType.PDF_SECTION) {

					DynamicSectionPDFContent pdfContent = (DynamicSectionPDFContent) dynamicSection;

					byte[] pdfRawData = FileUploadCacheManager.getInstance().removePdf(pdfContent.getPDFUrl());

					dynamicContentDao.addDynamicSectionPDF(
							dycoContent.getId(),
							pdfRawData,
							centerAlignment.getId(),
							dycoSequence
							);
				}

				dycoSequence++;
			}		
			
			TextContentTranslationEntry questionEntry = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, exerciceQuestionContent.getQuestion());		

			switch (exerciceQuestionContent.getQuestionType())
			{
			case ANSWER_TEXT:
				
				AnswerTextContent answerTextContent = (AnswerTextContent) exerciceQuestionContent.getAnswer();
				TextContentTranslationEntry responseEntry = internationalizationDao.insertTextContentTranslationEntry(EN_LANG, CA_CULT, answerTextContent.getTextAnswer());	
				
				exerciceDao.addTextExerciceQuestionToExercice(
						exercice.getId(),
						dycoContent.getId(),
						questionEntry.getTextcontententry().getId(),
						exerciceQuestionContent.getScore(),
						responseEntry.getTextcontententry().getId(),
						exerciceQuestionContentSequence
				);
				break;
			case ANSWER_NUMERIC:
				
				AnswerNumericContent answerNumericContent = (AnswerNumericContent) exerciceQuestionContent.getAnswer();
				int answer = answerNumericContent.getNumericAnswer();
				exerciceDao.addNumericExerciceQuestionToExercice(
						exercice.getId(),
						dycoContent.getId(),
						questionEntry.getTextcontententry().getId(),
						exerciceQuestionContent.getScore(),
						answer,
						exerciceQuestionContentSequence
				);
				break;
			case ANSWER_CHOICE:
				AnswerChoiceContent answerChoiceContent = (AnswerChoiceContent) exerciceQuestionContent.getAnswer();
				List<String> answerList = answerChoiceContent.getAnswerList();
				List<String> answerChoiceList = answerChoiceContent.getAvailableChoiceList();
				
				exerciceDao.addAnswerChoiceExerciceQuestionToExercice(
						exercice.getId(),
						dycoContent.getId(),
						questionEntry.getTextcontententry().getId(),
						exerciceQuestionContent.getScore(),
						answerChoiceList,
						answerList,
						exerciceQuestionContentSequence
				);
				break;
			default:
				break;

			}

			exerciceQuestionContentSequence++;
		}

		manager.getTransaction().commit();
	}

}
