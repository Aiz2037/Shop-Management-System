package Request;

import java.math.BigDecimal;

import Entity.Category;
import lombok.Data;

@Data
public class ProductUpdateRequest {
	private String name;
	private BigDecimal price;
	private Category category;
	private String brand;
	private Integer inventory;
}
