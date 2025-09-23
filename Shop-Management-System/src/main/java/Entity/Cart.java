package Entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	private Set<CartItem> cartItems = new HashSet<>();
	
	private BigDecimal totalAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	///
	///
	///
	///
	///
	
	public void addItem(CartItem cartItem) {
		this.cartItems.add(cartItem);
	cartItem.setCart(this);
	}
	
	public void removeItem(CartItem cartItem) {
		this.cartItems.remove(cartItem);
		cartItem.setCart(null);
	}
	
	public void setTotalAmount() {
		Set<BigDecimal> getItemTotalPrice=this.cartItems.stream().map(item->item.getTotalPrice()).collect(Collectors.toSet());
		this.totalAmount=getItemTotalPrice.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
	
	}
}
