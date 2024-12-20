package com.webcuoiky.softwareshop.model;

import jakarta.persistence.*;

import java.text.DecimalFormat;
import java.util.Collection;

@Entity
@Table (name="software")
public class Software {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private Float sale_price;
    private Integer quantity;
    private String category;
    private String image;

    @OneToMany(mappedBy = "software", fetch = FetchType.LAZY)
    private Collection<Order_items> orderItems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getSale_price() {
        return sale_price;
    }

    public void setSale_price(Float sale_price) {
        this.sale_price = sale_price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Collection<Order_items> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Collection<Order_items> orderItems) {
        this.orderItems = orderItems;
    }

    public String getFormattedPrice() {
        DecimalFormat df = new DecimalFormat("0.000");
        return df.format(price);
    }
}
