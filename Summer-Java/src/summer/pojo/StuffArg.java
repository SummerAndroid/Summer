package summer.pojo;

/**
 * StuffArg entity. @author MyEclipse Persistence Tools
 */

public class StuffArg implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4153617802877054791L;
	private Long id;
	private Long stuffId;
	private String name;
	private String value;
	private String comment;

	// Constructors

	/** default constructor */
	public StuffArg() {
	}

	/** minimal constructor */
	public StuffArg(Long stuffId, String name, String value) {
		this.stuffId = stuffId;
		this.name = name;
		this.value = value;
	}

	/** full constructor */
	public StuffArg(Long stuffId, String name, String value, String comment) {
		this.stuffId = stuffId;
		this.name = name;
		this.value = value;
		this.comment = comment;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStuffId() {
		return this.stuffId;
	}

	public void setStuffId(Long stuffId) {
		this.stuffId = stuffId;
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

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}