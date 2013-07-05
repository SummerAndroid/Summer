package summer.pojo;

/**
 * TemplateHasTemplateItem entity. @author MyEclipse Persistence Tools
 */

public class TemplateHasTemplateItem implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6966862825902030831L;
	private TemplateHasTemplateItemId id;

	// Constructors

	/** default constructor */
	public TemplateHasTemplateItem() {
	}

	/** full constructor */
	public TemplateHasTemplateItem(TemplateHasTemplateItemId id) {
		this.id = id;
	}

	// Property accessors

	public TemplateHasTemplateItemId getId() {
		return this.id;
	}

	public void setId(TemplateHasTemplateItemId id) {
		this.id = id;
	}

}