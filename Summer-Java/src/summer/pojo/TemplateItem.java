package summer.pojo;

/**
 * TemplateItem entity. @author MyEclipse Persistence Tools
 */

public class TemplateItem implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3725524734876338644L;
	private Long id;
	private String name;

	// Constructors

	/** default constructor */
	public TemplateItem() {
	}

	/** full constructor */
	public TemplateItem(String name) {
		this.name = name;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override public String toString() {
		return "TemplateItem [id=" + id + ", name=" + name + "]";
	}
}