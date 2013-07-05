package summer.pojo;

/**
 * TemplateItemArg entity. @author MyEclipse Persistence Tools
 */

public class TemplateItemArg implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8655942830833856451L;
	private Long id;
	private Long templateItemId;
	private String name;
	private String value;

	// Constructors

	/** default constructor */
	public TemplateItemArg() {
	}

	/** full constructor */
	public TemplateItemArg(Long templateItemId, String name, String value) {
		this.templateItemId = templateItemId;
		this.name = name;
		this.value = value;
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

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}