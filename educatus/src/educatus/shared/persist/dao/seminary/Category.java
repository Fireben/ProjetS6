package educatus.shared.persist.dao.seminary;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@Table(name="seminary.category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cate_id", unique=true, nullable=false)
	private Integer cateId;

	@Column(name="cate_deleteflag", nullable=false)
	private Boolean cateDeleteflag;

	@Column(name="tece_name", nullable=false)
	private Integer teceName;

	@Column(name="tex_tece_id", nullable=false)
	private Integer texTeceId;

	//bi-directional many-to-one association to Category
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cate_parent")
	private Category category;

	//bi-directional many-to-one association to Category
	@OneToMany(mappedBy="category")
	private List<Category> categories;

	//bi-directional many-to-many association to Seminary
	@ManyToMany(mappedBy="categories")
	private List<Seminary> seminaries;

    public Category() {
    }

	public Integer getCateId() {
		return this.cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	public Boolean getCateDeleteflag() {
		return this.cateDeleteflag;
	}

	public void setCateDeleteflag(Boolean cateDeleteflag) {
		this.cateDeleteflag = cateDeleteflag;
	}

	public Integer getTeceName() {
		return this.teceName;
	}

	public void setTeceName(Integer teceName) {
		this.teceName = teceName;
	}

	public Integer getTexTeceId() {
		return this.texTeceId;
	}

	public void setTexTeceId(Integer texTeceId) {
		this.texTeceId = texTeceId;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public List<Seminary> getSeminaries() {
		return this.seminaries;
	}

	public void setSeminaries(List<Seminary> seminaries) {
		this.seminaries = seminaries;
	}
	
}