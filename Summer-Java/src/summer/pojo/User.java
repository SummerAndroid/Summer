package summer.pojo;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8735037909061839490L;
	private Long id;
	private String name;
	private String password;
	private Integer type;
	private Integer permission;
	private String tellphone;
	private String address;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String name, String password, Integer type, Integer permission) {
		this.name = name;
		this.password = password;
		this.type = type;
		this.permission = permission;
	}

	/** full constructor */
	public User(String name, String password, Integer type, Integer permission,
			String tellphone, String address) {
		this.name = name;
		this.password = password;
		this.type = type;
		this.permission = permission;
		this.tellphone = tellphone;
		this.address = address;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPermission() {
		return this.permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public String getTellphone() {
		return this.tellphone;
	}

	public void setTellphone(String tellphone) {
		this.tellphone = tellphone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", type=" + type + ", permission=" + permission
				+ ", tellphone=" + tellphone + ", address=" + address + "]";
	}
}