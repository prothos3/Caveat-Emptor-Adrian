package dto;

import java.io.Serializable;

public class ItemDTO implements Serializable {

	private static final long serialVersionUID = -9132327261704187679L;

	private Long id;

	private String name;

	private String description;

	private Long openingDate;

	private Long closingDate;

	private CategoryDTO categoryDTO;

	private UserDTO userDTO;

	private Double initialPrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}

	public Double getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(Double initialPrice) {
		this.initialPrice = initialPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Long openingDate) {
		this.openingDate = openingDate;
	}

	public Long getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Long closingDate) {
		this.closingDate = closingDate;
	}

}
