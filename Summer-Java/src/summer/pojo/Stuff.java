package summer.pojo;

import java.util.List;

/**
 * Stuff entity. @author MyEclipse Persistence Tools
 * 
 * @author zhenzxie
 */
public class Stuff implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 937395784510231284L;
	private Long id;
	private Long stuffCategoryId;
	private String code;
	private Double price;
	private Integer life;
	private String address;
	private String factory;
	private String zxing;
	private Long startTime;

	// zhenzxie add some field at here...
	private String categoryName;
	private List<StuffArg> argList;

	// Constructors

	/** default constructor */
	public Stuff() {
	}

	/** full constructor */
	public Stuff(Long stuffCategoryId, String code, Double price, Integer life,
			String address, String factory, String zxing, Long startTime) {
		this.stuffCategoryId = stuffCategoryId;
		this.code = code;
		this.price = price;
		this.life = life;
		this.address = address;
		this.factory = factory;
		this.zxing = zxing;
		this.startTime = startTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStuffCategoryId() {
		return this.stuffCategoryId;
	}

	public void setStuffCategoryId(Long stuffCategoryId) {
		this.stuffCategoryId = stuffCategoryId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getLife() {
		return this.life;
	}

	public void setLife(Integer life) {
		this.life = life;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFactory() {
		return this.factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getZxing() {
		return this.zxing;
	}

	public void setZxing(String zxing) {
		this.zxing = zxing;
	}

	public Long getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<StuffArg> getArgList() {
		return argList;
	}

	public void setArgList(List<StuffArg> argList) {
		this.argList = argList;
	}

	@Override public String toString() {
		return "Stuff [id=" + id + ", stuffCategoryId=" + stuffCategoryId
				+ ", code=" + code + ", price=" + price + ", life=" + life
				+ ", address=" + address + ", factory=" + factory + ", zxing="
				+ zxing + ", startTime=" + startTime + ", categoryName="
				+ categoryName + ", argList=" + argList + "]";
	}
}