package educatus.shared.persist.dao.seminary;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import educatus.shared.persist.dao.internationalization.TextContentEntry;

@Entity
@Table(name = "seminary.category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cate_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "cate_deleteflag", nullable = false)
	private Boolean deleteFlag;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_name", nullable = false, insertable = false, updatable = false)
	private TextContentEntry name;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tex_tece_id", nullable = false, insertable = false, updatable = false)
	private TextContentEntry description;

	// bi-directional many-to-one association to Category
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cate_parent")
	private Category category;

	// bi-directional many-to-one association to Category
	@OneToMany(mappedBy = "category")
	private List<Category> childCategories;

	// bi-directional many-to-many association to Seminary
	@ManyToMany(mappedBy = "categories")
	private List<Seminary> seminaries;

	public Category() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getDeleteflag() {
		return this.deleteFlag;
	}

	public void setDeleteflag(Boolean deleteflag) {
		this.deleteFlag = deleteflag;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getChildCategories() {
		return this.childCategories;
	}

	public void setChildCategories(List<Category> childCategories) {
		this.childCategories = childCategories;
	}

	public List<Seminary> getSeminaries() {
		return this.seminaries;
	}

	public void setSeminaries(List<Seminary> seminaries) {
		this.seminaries = seminaries;
	}

	public TextContentEntry getName() {
		return name;
	}

	public void setName(TextContentEntry name) {
		this.name = name;
	}

	public TextContentEntry getDescription() {
		return description;
	}

	public void setDescription(TextContentEntry description) {
		this.description = description;
	}
}