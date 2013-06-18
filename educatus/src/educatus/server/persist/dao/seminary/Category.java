package educatus.server.persist.dao.seminary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import educatus.server.persist.dao.internationalization.Image;
import educatus.server.persist.dao.internationalization.TextContentEntry;

@Entity
@NamedQueries({
	@NamedQuery(name = Category.FIND_ALL, query = "SELECT c FROM Category c"),
	@NamedQuery(name = Category.FIND_ALL_CHILDREN, query = "SELECT c FROM Category c WHERE c.parentCategory=:parentCategory"),
	@NamedQuery(name = Category.FIND_ALL_TOP_LEVEL, query = "SELECT c FROM Category c WHERE c.parentCategory IS NULL")})
@Table(name = "seminary.category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "CATEGORY.FIND_ALL";
	public static final String FIND_ALL_CHILDREN = "CATEGORY.FIND_ALL_CHILDREN";
	public static final String FIND_ALL_CHILDREN_PARAM_NAME = "parentCategory";
	public static final String FIND_ALL_TOP_LEVEL = "CATEGORY.FIND_ALL_TOP_LEVEL";
	
	@Id
	@SequenceGenerator(name = "CATEGORY_CATE_ID_GENERATOR", sequenceName = "seminary.category_cate_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_CATE_ID_GENERATOR")
	@Column(name = "cate_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "cate_deleteflag", nullable = false)
	private Boolean deleteFlag;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_name", nullable = false, insertable = true, updatable = true)
	private TextContentEntry name;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_description", nullable = false, insertable = true, updatable = true)
	private TextContentEntry description;

	// bi-directional many-to-one association to Image
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "imag_icon", nullable = false, insertable = true, updatable = true)
	private Image image;

	// bi-directional many-to-one association to Category
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cate_parent")
	private Category parentCategory;

	// bi-directional many-to-one association to Category
	@OneToMany(mappedBy = "parentCategory")
	private List<Category> childCategories = new ArrayList<Category>();

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

	public Category getParentCategory() {
		return this.parentCategory;
	}

	public void setParentCategory(Category category) {
		this.parentCategory = category;
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}