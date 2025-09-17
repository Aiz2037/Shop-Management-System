package Request;

import java.util.List;

import Entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //auto generate getter and setter

public class AddProductRequest {
	private Long id;
	private String name;
	private double price;
	private Category category;
	private String brand;
}
