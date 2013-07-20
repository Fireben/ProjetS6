package educatus.server.businesslogic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.persist.dao.SecurityDao;
import educatus.server.persist.dao.internationalization.Language;

@Singleton
public class SearchManager
{
	private static String PREPARE_SEARCH_STATEMENT_REGEX = " ";

	private static String QUERY_SEARCH_SEMINARY_PLAIN_TEXT = "SELECT DISTINCT SEMI_Id, ts_rank_cd(to_tsvector(TCTE_Translation), query) rank FROM seminary.vSeminaryTextIndex, plainto_tsquery('%s','%s') query WHERE LANG_Code = '%s' AND to_tsvector('%s',TCTE_Translation) @@ query ORDER BY rank desc";
	private static String QUERY_SEARCH_SEMINARY_PREPARE_TEXT = "SELECT DISTINCT SEMI_Id, ts_rank_cd(to_tsvector(TCTE_Translation), query) rank FROM seminary.vSeminaryTextIndex, to_tsquery('%s','%s') query WHERE LANG_Code = '%s' AND to_tsvector('%s',TCTE_Translation) @@ query ORDER BY rank desc";
	private static String QUERY_SEARCH_EXERCICE_PLAIN_TEXT = "SELECT DISTINCT EXER_Id, ts_rank_cd(to_tsvector(TCTE_Translation), query) rank FROM exercice.vExerciceTextIndex, plainto_tsquery('%s','%s') query WHERE LANG_Code = '%s' AND to_tsvector('%s',TCTE_Translation) @@ query ORDER BY rank desc";
	private static String QUERY_SEARCH_EXERCICE_PREPARE_TEXT = "SELECT DISTINCT EXER_Id, ts_rank_cd(to_tsvector(TCTE_Translation), query) rank FROM exercice.vExerciceTextIndex, to_tsquery('%s','%s') query WHERE LANG_Code = '%s' AND to_tsvector('%s',TCTE_Translation) @@ query ORDER BY rank desc";

	@Inject
	private EntityManager entityManager;
	@Inject
	private SecurityDao securityDao;

	private String PrepareSearchStatement(String searchEntry)
	{
		String[] toReturnStringArray = searchEntry
				.split(SearchManager.PREPARE_SEARCH_STATEMENT_REGEX);

		String toReturnString = "";

		for (String strEntry : toReturnStringArray)
		{
			toReturnString += strEntry + " & ";
		}
		// Remove the lonely last "&" at the end.
		int lastAnd = toReturnString.lastIndexOf("&");
		toReturnString = toReturnString.substring(0, lastAnd).trim();

		return toReturnString;
	}

	public List<Integer> SearchInSeminary(String searchEntry,
			boolean prepareSearchStatement, Language language) throws Exception
	{
		Query query = null;
		List<Integer> toReturnIdList = new ArrayList<Integer>();
		if (prepareSearchStatement)
		{
			String prequery = String.format(
					SearchManager.QUERY_SEARCH_SEMINARY_PREPARE_TEXT,
					language.getFullname(),
					this.PrepareSearchStatement(searchEntry),
					language.getCode(),
					language.getFullname());
			query = entityManager.createNativeQuery(prequery);
		} else
		{
			String prequery = String.format(
					SearchManager.QUERY_SEARCH_SEMINARY_PLAIN_TEXT,
					language.getFullname(), searchEntry, language.getCode(), language.getFullname());
			query = entityManager.createNativeQuery(prequery);
		}

		// Calling the query.
		List results = query.getResultList();

		// Parsing the result to get the Id.
		Object[] entry = null;
		int id = -1;
		for (Object obj : results)
		{
			entry = (Object[]) obj;
			toReturnIdList.add((int) entry[0]);
		}

		return toReturnIdList;
	}

	public List<Integer> SearchInExercice(String searchEntry,
			boolean prepareSearchStatement, Language language) throws Exception
	{
		Query query = null;
		List<Integer> toReturnIdList = new ArrayList<Integer>();
		if (prepareSearchStatement)
		{
			String prequery = String.format(
					SearchManager.QUERY_SEARCH_EXERCICE_PREPARE_TEXT,
					language.getFullname(),
					this.PrepareSearchStatement(searchEntry),
					language.getCode(), 
					language.getFullname());
			query = entityManager.createNativeQuery(prequery);
		} else
		{
			String prequery = String.format(
					SearchManager.QUERY_SEARCH_EXERCICE_PLAIN_TEXT,
					language.getFullname(), searchEntry, language.getCode(), language.getFullname());
			query = entityManager.createNativeQuery(prequery);
		}

		// Calling the query.
		List results = query.getResultList();

		// Parsing the result to get the Id.
		Object[] entry = null;
		int id = -1;
		for (Object obj : results)
		{
			entry = (Object[]) obj;
			toReturnIdList.add((int) entry[0]);
		}

		return toReturnIdList;
	}
}