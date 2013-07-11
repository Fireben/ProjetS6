package educatus.server.businesslogic.seminarymanager;

import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.businesslogic.DynamicContentAdapter;
import educatus.server.businesslogic.SeminaryAdapter;
import educatus.server.persist.dao.seminary.Seminary;
import educatus.shared.dto.dynamiccontent.AbstractDynamicSection;
import educatus.shared.dto.seminary.SeminaryContent;
import educatus.shared.dto.seminary.SeminaryCoreContent;

@Singleton
public class SeminaryContentBuilder {

	@Inject
	private EntityManager entityManager;

	public SeminaryContent buildSeminaryContent(int seminaryId, String culture, String language) {

		Seminary seminary = entityManager.find(Seminary.class, seminaryId);
		SeminaryContent content = parseSeminary(seminary, culture, language);
		return content;
	}

	private SeminaryContent parseSeminary(Seminary seminary, String culture, String language) {

		SeminaryContent content = new SeminaryContent();
		SeminaryCoreContent coreContent = SeminaryAdapter.seminaryToSeminaryCoreContent(seminary, culture, language);

		List<AbstractDynamicSection> abstractDynamicSectionList = DynamicContentAdapter.getAbstractDynamicSectionFromDynamicContent(seminary.getDynamicContent(), culture, language);
		content.setDynamicSectionList(abstractDynamicSectionList);

		content.setCoreContent(coreContent);
		return content;
	}
}
