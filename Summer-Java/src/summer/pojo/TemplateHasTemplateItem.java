package summer.pojo;

/**
 * TemplateHasTemplateItem entity. @author MyEclipse Persistence Tools
 */

public class TemplateHasTemplateItem implements java.io.Serializable {

	// Fields

	private Long id;
	private Long templateId;
	private Long templateItemId;

	// Constructors

	/** default constructor */
	public TemplateHasTemplateItem() {
	}

	/** full constructor */
	public TemplateHasTemplateItem(Long id, Long templateId, Long templateItemId) {
		this.id = id;
		this.templateId = templateId;
		this.templateItemId = templateItemId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Long getTemplateItemId() {
		return this.templateItemId;
	}

	public void setTemplateItemId(Long templateItemId) {
		this.templateItemId = templateItemId;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override public String toString() {
		return "TemplateHasTemplateItem [id=" + id + ", templateId="
				+ templateId + ", templateItemId=" + templateItemId + "]";
	}

}