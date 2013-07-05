package summer.pojo;

/**
 * TemplateHasTemplateItemId entity. @author MyEclipse Persistence Tools
 */

public class TemplateHasTemplateItemId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5737265598946516944L;
	private Long templateId;
	private Long templateItemId;

	// Constructors

	/** default constructor */
	public TemplateHasTemplateItemId() {
	}

	/** full constructor */
	public TemplateHasTemplateItemId(Long templateId, Long templateItemId) {
		this.templateId = templateId;
		this.templateItemId = templateItemId;
	}

	// Property accessors

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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TemplateHasTemplateItemId))
			return false;
		TemplateHasTemplateItemId castOther = (TemplateHasTemplateItemId) other;

		return ((this.getTemplateId() == castOther.getTemplateId()) || (this
				.getTemplateId() != null && castOther.getTemplateId() != null && this
				.getTemplateId().equals(castOther.getTemplateId())))
				&& ((this.getTemplateItemId() == castOther.getTemplateItemId()) || (this
						.getTemplateItemId() != null
						&& castOther.getTemplateItemId() != null && this
						.getTemplateItemId().equals(
								castOther.getTemplateItemId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getTemplateId() == null ? 0 : this.getTemplateId()
						.hashCode());
		result = 37
				* result
				+ (getTemplateItemId() == null ? 0 : this.getTemplateItemId()
						.hashCode());
		return result;
	}

}