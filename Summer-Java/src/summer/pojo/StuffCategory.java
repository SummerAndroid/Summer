package summer.pojo;

/**
 * StuffCategory entity. @author MyEclipse Persistence Tools
 */

public class StuffCategory implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6464919471661421614L;
	private Long id;
	private Long templateItemId;
	private String name;

	// Constructors

	/** default constructor */
	public StuffCategory() {
	}

	/** full constructor */
	public StuffCategory(Long templateItemId, String name) {
		this.templateItemId = templateItemId;
		this.name = name;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTemplateItemId() {
		return this.templateItemId;
	}

	public void setTemplateItemId(Long templateItemId) {
		this.templateItemId = templateItemId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}