package educatus;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;

import com.google.inject.Guice;
import com.google.inject.Injector;

import educatus.server.persist.JpaInitializer;
import educatus.server.persist.dao.DaoModule;
import educatus.server.persist.dao.DynamicContentDao;
import educatus.server.persist.dao.InternationalizationDao;
import educatus.server.persist.dao.SecurityDao;
import educatus.server.persist.dao.SeminaryDao;
import educatus.server.persist.dao.internationalization.ImageInternal;

public class DatabaseTest {

	public static void main(String[] args) {

		InternationalizationDao internationalizationDao = null;
		SeminaryDao seminaryDao = null;
		SecurityDao securityDao = null;
		DynamicContentDao dynamicContentDao = null;
		EntityManager manager = null;

		Injector dbInjector = Guice.createInjector(new DaoModule(
				"db-manager-localhost"));
		dbInjector.getInstance(JpaInitializer.class);
		internationalizationDao = dbInjector
				.getInstance(InternationalizationDao.class);
		seminaryDao = dbInjector.getInstance(SeminaryDao.class);
		securityDao = dbInjector.getInstance(SecurityDao.class);
		dynamicContentDao = dbInjector.getInstance(DynamicContentDao.class);
		manager = dbInjector.getInstance(EntityManager.class);
		
		try {
			ImageInternal internal = manager.find(ImageInternal.class, 101);
			
			byte[] byteArray = internal.getImageContent();
			String name = internal.getImageName();
			
			InputStream in = new ByteArrayInputStream(byteArray);
			BufferedImage bImageFromConvert = ImageIO.read(in);

			File fetchFile = new File("C:/Users/Marc/Desktop/test.png");
			ImageIO.write(bImageFromConvert, "png", fetchFile);
			
			File fnew = new File("C:/Users/Marc/Desktop/IMG_26052013_220321.png");
			
			BufferedImage originalImage = ImageIO.read(fnew);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "png", baos);

			byte[] imageInByte = baos.toByteArray();

			manager.getTransaction().begin();
			ImageInternal imageInternal = internationalizationDao.insertImageInternal("ImageDeTest", imageInByte);
			manager.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		// try {
		// TextContentTranslationEntry titleEntry =
		// internationalizationDao.insertTextContentTranslationEntry("fr", "CA",
		// "Paragraphe de Sauce");
		// TextContentTranslationEntry textEntry =
		// internationalizationDao.insertTextContentTranslationEntry("fr", "CA",
		// "Sauce nuage Sauce nuage");
		// DynamicSectionAlignment align =
		// manager.find(DynamicSectionAlignment.class, 1);
		//
		// manager.getTransaction().begin();
		// DynamicSectionText text = new DynamicSectionText();
		// text.setSequenceNumber(5);
		// text.setTitle(titleEntry.getTextcontententry());
		// text.setText(textEntry.getTextcontententry());
		// text.setDynamicSectionAlignment(align);
		//
		// DynamicContent content = new DynamicContent();
		// manager.merge(content);
		// text.setDynamicContent(content);
		//
		// manager.merge(text);
		//
		// manager.getTransaction().commit();
		//
		// List<DynamicContent> dynamicContents =
		// dynamicContentDao.findAllDynamicContent();
		//
		// for (DynamicContent dynamicContent : dynamicContents) {
		// List<DynamicSection> dynamicSectionList =
		// dynamicContent.getDynamicSectionList();
		//
		// for (DynamicSection dynamicSection : dynamicSectionList) {
		// System.out.println(dynamicSection.getId());
		// }
		// }
		//
		//
		// User marcUser = securityDao.findUserByCip("beam1711");
		//
		// marcUser.setFirstName("Marc-André");
		// marcUser.setLastName("Beaudry");
		//
		// securityDao.updateUser(marcUser);
		//
		//
		//
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		//
		// Category parentCat = null;
		// Category child1 = null;
		// Category child2 = null;
		//
		// try {
		//
		// TextContentTranslationEntry parentNameEn =
		// internationalizationDao.insertTextContentTranslationEntry(
		// "en",
		// "CA",
		// "Computer Engineering"
		// );
		//
		// TextContentTranslationEntry parentDescriptionEn =
		// internationalizationDao.insertTextContentTranslationEntry(
		// "en",
		// "CA",
		// "Computer Engineering"
		// );
		//
		// TextContentTranslationEntry child1NameEn =
		// internationalizationDao.insertTextContentTranslationEntry(
		// "en",
		// "CA",
		// "Software"
		// );
		//
		// TextContentTranslationEntry child1DescriptionEn =
		// internationalizationDao.insertTextContentTranslationEntry(
		// "en",
		// "CA",
		// "Software"
		// );
		//
		// TextContentTranslationEntry child2NameEn =
		// internationalizationDao.insertTextContentTranslationEntry(
		// "en",
		// "CA",
		// "Hardware"
		// );
		//
		// TextContentTranslationEntry child2DescriptionEn =
		// internationalizationDao.insertTextContentTranslationEntry(
		// "en",
		// "CA",
		// "Hardware"
		// );
		//
		// // Url des images
		// ImageExternal external1 =
		// internationalizationDao.insertImageExternal("sauceNuage1");
		// ImageExternal external2 =
		// internationalizationDao.insertImageExternal("sauceNuage2");
		// ImageExternal external3 =
		// internationalizationDao.insertImageExternal("sauceNuage3");
		//
		// parentCat = seminaryDao.createNewCategory(
		// parentNameEn.getTextcontententry().getId(),
		// parentDescriptionEn.getTextcontententry().getId(),
		// external1.getId(),
		// null
		// );
		//
		// child1 = seminaryDao.createNewCategory(
		// child1NameEn.getTextcontententry().getId(),
		// child1DescriptionEn.getTextcontententry().getId(),
		// external2.getId(),
		// parentCat.getId()
		// );
		//
		// child2 = seminaryDao.createNewCategory(
		// child2NameEn.getTextcontententry().getId(),
		// child2DescriptionEn.getTextcontententry().getId(),
		// external3.getId(),
		// parentCat.getId()
		// );
		//
		// } catch (Exception e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }

		// Category foundParentCat = manager.find(Category.class, 42);
		// List<Category> listCat =
		// seminaryDao.findChildrensCategories(foundParentCat.getId());

		// for (Category category : listCat) {
		// TextContentEntry entry = category.getName();
		// List<TextContentTranslationEntry> entries =
		// entry.getTextContentTranslationEntries();
		// for (TextContentTranslationEntry textContentTranslationEntry :
		// entries) {
		// System.out.println(textContentTranslationEntry.getTcteTranslation());
		// }
		// }

		// try {
		// Culture c = internationalizationDao.findCultureByCode("CA");
		// System.out.println(c.getId());
		// System.out.println(c.getCode());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		//
		// try {
		// List<Culture> allCulture = internationalizationDao.findAllCulture();
		// List<Image> allImage = internationalizationDao.findAllImage();
		// List<ImageContentEntry> allImageContentEntry =
		// internationalizationDao.findAllImageContentEntry();
		// List<ImageContentTranslationEntry> allImageContentTranslationEntry =
		// internationalizationDao.findAllImageContentTranslationEntry();
		// List<Language> allLanguage =
		// internationalizationDao.findAllLanguage();
		// List<TextContentEntry> allTextContentEntry =
		// internationalizationDao.findAllTextContentEntry();
		// List<TextContentTranslationEntry> allTextContentTranslationEntry =
		// internationalizationDao.findAllTextContentTranslationEntry();
		// List<Video> allVideo = internationalizationDao.findAllVideo();
		// List<VideoContentEntry> allVideoContentEntry =
		// internationalizationDao.findAllVideoContentEntry();
		// List<VideoContentTranslationEntry> allVideoContentTranslationEntry =
		// internationalizationDao.findAllVideoContentTranslationEntry();
		//
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}
}
