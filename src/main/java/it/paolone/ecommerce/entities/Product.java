package it.paolone.ecommerce.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "barcode")
    private String productBarcode;
    @Column(name = "image_filename")
    private String imageFilename;
    @Column(name = "quantity")
    private int productQuantity;
    @Column(name = "price")
    private float productPrice;

    // getters and setters

    public float getproductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public String getBarcode() {
        return productBarcode;
    }

    public String getProductName() {
        return productName;
    }

    public void setBarcode(String barcode) {
        this.productBarcode = barcode;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return productQuantity;
    }

    public void setQuantity(short quantity) {
        this.productQuantity = quantity;
    }

    public String getImageFileName() {
        return this.imageFilename;
    }

    public void setImageFilename(String imageFileName) {
        this.imageFilename = imageFileName;
    }

}
