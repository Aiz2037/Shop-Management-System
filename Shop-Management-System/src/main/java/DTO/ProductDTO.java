package DTO;

import java.math.BigDecimal;

import Entity.Cart;
import Entity.Category;
import Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {
	private Long id;
	private String name;
	private BigDecimal price;
	private String brand;
	private int inventory;
	private Category category;
}
