package summer.pojo;

/**
 * Tasklet entity. @author MyEclipse Persistence Tools
 */

public class Tasklet implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4486977756968133823L;
	private Long id;
	private Long userId;
	private String name;
	private Long cycle;
	private Integer account;
	private Long last_time;

	// Constructors

	/** default constructor */
	public Tasklet() {
	}

	/** full constructor */
	public Tasklet(Long userId, String name, Long cycle) {
		this.userId = userId;
		this.name = name;
		this.cycle = cycle;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCycle() {
		return this.cycle;
	}

	public void setCycle(Long cycle) {
		this.cycle = cycle;
	}

	public Long getLast_time() {
		return last_time;
	}

	public void setLast_time(Long last_time) {
		this.last_time = last_time;
	}

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override public String toString() {
		return "Tasklet [id=" + id + ", userId=" + userId + ", name=" + name
				+ ", cycle=" + cycle + ", account=" + account + ", last_time="
				+ last_time + "]";
	}
}