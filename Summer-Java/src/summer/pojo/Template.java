package summer.pojo;


/**
 * Template entity. @author MyEclipse Persistence Tools
 */

public class Template implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7394050824657215742L;
	private Long id;
	private String name;
	private Long createTime;

	// Constructors

	/** default constructor */
	public Template() {
	}

	/** full constructor */
	public Template(String name, Long createTime) {
		this.name = name;
		this.createTime = createTime;
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

	public Long getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

}