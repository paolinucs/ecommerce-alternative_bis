package it.paolone.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sold_products")
@Data
public class SoldProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sold_product_id")
    private Long soldProductId;
    // @Column(name = "product_id")
    // private Long productId;
    // @Column(name = "order_id")
    // private Long orderId;

    // @ManyToMany(fetch = FetchType.LAZY)
    // @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    // private Product joinedProduct;

    // @ManyToMany(fetch = FetchType.LAZY)
    // @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    // private Order joinedOrder;
}
