package summer.pojo;

import java.util.List;

/**
 * TaskletItem entity. @author MyEclipse Persistence Tools
 */

public class TaskletItem implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6014459074606141363L;
	private Long id;
	private Long taskletId;
	private Long stuffId;
	private String name;

	private List<TaskletItemArg> argList;

	// Constructors

	/** default constructor */
	public TaskletItem() {
	}

	/** full constructor */
	public TaskletItem(Long taskletId, Long stuffId, String name) {
		this.taskletId = taskletId;
		this.stuffId = stuffId;
		this.name = name;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTaskletId() {
		return this.taskletId;
	}

	public void setTaskletId(Long taskletId) {
		this.taskletId = taskletId;
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

	public List<TaskletItemArg> getArgList() {
		return argList;
	}

	public void setArgList(List<TaskletItemArg> argList) {
		this.argList = argList;
	}

	@Override public String toString() {
		return "TaskletItem [id=" + id + ", taskletId=" + taskletId
				+ ", stuffId=" + stuffId + ", name=" + name + ", argList="
				+ argList + "]";
	}
}