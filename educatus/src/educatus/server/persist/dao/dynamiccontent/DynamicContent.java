package educatus.server.persist.dao.dynamiccontent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = DynamicContent.FIND_ALL, query = "SELECT d FROM DynamicContent d") })
@Table(name = "dynamic_content.dynamiccontent")
public class DynamicContent implements Serializable {

	private static final long serialVersionUID = -8951511534581405033L;
	public static final String FIND_ALL = "DYNAMICCONTENT.FIND_ALL";

	@Id
	@SequenceGenerator(name = "dynamiccontent_dyco_id", sequenceName = "dynamic_content.dynamiccontent_dyco_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dynamiccontent_dyco_id")
	@Column(name = "dyco_id", unique = true, nullable = false)
	private Integer id;

	// bi-directional many-to-one association to DynamicSection
	@OneToMany(mappedBy = "dynamicContent")
	private List<DynamicSection> dynamicSectionList = new ArrayList<DynamicSection>();

	public DynamicContent() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<DynamicSection> getDynamicSectionList() {
		return this.dynamicSectionList;
	}

	public void setDynamicSectionList(List<DynamicSection> dynamicSectionList) {
		this.dynamicSectionList = dynamicSectionList;
	}
}