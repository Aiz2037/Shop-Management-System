package Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import Status.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders") //import jakarta persistance not the hibernate
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private LocalDate orderDate;
	private BigDecimal priceToPay;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@ManyToOne
	@JoinColumn(name="foreignUserID)")
	private User user;
	
	@OneToMany(mappedBy="order",cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<OrderItem> orderItems;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public BigDecimal getPriceToPay() {
		return priceToPay;
	}

	public void setPriceToPay(BigDecimal priceToPay) {
		this.priceToPay = priceToPay;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	
}
