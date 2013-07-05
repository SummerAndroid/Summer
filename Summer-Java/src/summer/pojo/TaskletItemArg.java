package summer.pojo;

/**
 * TaskletItemArg entity. @author MyEclipse Persistence Tools
 */

public class TaskletItemArg implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4031834915930544126L;
	private Long id;
	private Long taskletItemId;
	private String name;
	private String value;
	private String comment;

	// Constructors

	/** default constructor */
	public TaskletItemArg() {
	}

	/** minimal constructor */
	public TaskletItemArg(Long taskletItemId, String name, String value) {
		this.taskletItemId = taskletItemId;
		this.name = name;
		this.value = value;
	}

	/** full constructor */
	public TaskletItemArg(Long taskletItemId, String name, String value,
			String comment) {
		this.taskletItemId = taskletItemId;
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

	public Long getTaskletItemId() {
		return this.taskletItemId;
	}

	public void setTaskletItemId(Long taskletItemId) {
		this.taskletItemId = taskletItemId;
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