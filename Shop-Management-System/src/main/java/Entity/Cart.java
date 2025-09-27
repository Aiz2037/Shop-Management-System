package Entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import DTO.CartItemDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
//@Data - not recommended to put in entity, this annotation prohibit use of getter & setter annotation
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy="cart",cascade = CascadeType.ALL, orphanRemoval=true)					//parent
	//@JsonIgnore //if ignore here entity will not return cartitem
	private Set<CartItem> cartItem = new HashSet<>();
	
	private BigDecimal totalAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	


	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void addItem(CartItem cartItem) {
		this.cartItem.add(cartItem);
		cartItem.setCart(this);
	}
	
	public void removeItem(CartItem cartItem) {
		this.cartItem.remove(cartItem);
		cartItem.setCart(null);
	}
	
	public void setTotalAmount() {
		Set<BigDecimal> getItemTotalPrice=this.cartItem.stream().map(item->item.getTotalPrice()).collect(Collectors.toSet());
		this.totalAmount=getItemTotalPrice.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
	
	}

	public Set<CartItem> getCartItem() {
		return cartItem;
	}

	public void setCartItemDTOs(Set<CartItem> cartItem) {
		this.cartItem = cartItem;
	}
}
