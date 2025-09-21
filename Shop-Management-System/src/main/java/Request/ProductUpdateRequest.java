package Request;

import Entity.Category;
import lombok.Data;

@Data
public class ProductUpdateRequest {
	private String name;
	private double price;
	private Category category;
	private String brand;
	private Integer inventory;
}
