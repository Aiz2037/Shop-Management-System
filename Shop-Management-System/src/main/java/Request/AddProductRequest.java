package Request;

import java.math.BigDecimal;

import Entity.Category;
import lombok.Data;

@Data //auto generate getter and setter

public class AddProductRequest {
	private String name;
	private BigDecimal price;
	private Category category;
	private String brand;
	private Integer inventory;
}
