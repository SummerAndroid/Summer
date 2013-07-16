package summer.pojo;

/**
 * TemplateItem entity. @author MyEclipse Persistence Tools
 */

public class TemplateItem implements java.io.Serializable {

	// Fields

	private Long id;
	private Long stuffCategoryId;
	private String name;

	// Constructors

	/** default constructor */
	public TemplateItem() {
	}

	/** full constructor */
	public TemplateItem(Long stuffCategoryId, String name) {
		this.stuffCategoryId = stuffCategoryId;
		this.name = name;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStuffCategoryId() {
		return this.stuffCategoryId;
	}

	public void setStuffCategoryId(Long stuffCategoryId) {
		this.stuffCategoryId = stuffCategoryId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}