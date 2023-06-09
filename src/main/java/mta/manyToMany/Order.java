package mta.manyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import mta.oneToMany.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date orderDate;

    @Column(nullable = false)
    private BigDecimal totalPrice;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;

    public Date getOrderDate() {
        return orderDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Product> getProducts() {
        return products;
    }


    public void setProducts(Set<Product> products) {
        this.products = products;
    }
    public void addProduct(Product product) {
        products.add(product);
        setTotalPrice();
    }
    public void removeProduct(Product product) {
        products.remove(product);
        setTotalPrice();
    }

    public void setTotalPrice() {
        if (products != null && !products.isEmpty()) {
            totalPrice = BigDecimal.ZERO;
            for (Product product : products) {
                if (product.getPrice() != null) {
                    totalPrice = totalPrice.add(product.getPrice());
                }
            }
        } else {
            totalPrice = BigDecimal.ZERO;
        }
    }


}
