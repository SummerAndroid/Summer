package summer.pojo;

import java.sql.Timestamp;

/**
 * Stuff entity. @author MyEclipse Persistence Tools
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
	private Timestamp startTime;

	// Constructors

	/** default constructor */
	public Stuff() {
	}

	/** full constructor */
	public Stuff(Long stuffCategoryId, String code, Double price, Integer life,
			String address, String factory, String zxing, Timestamp startTime) {
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

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

}