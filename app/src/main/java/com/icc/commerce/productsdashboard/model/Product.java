package com.icc.commerce.productsdashboard.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class Product implements Comparable<Product> {
    @SerializedName("backgroundImage")
    private String productImageUrl;
    private String topDescription;
    private String title;
    private String promoMessage;
    private String bottomDescription;
    private List<Item> content;

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public String getTopDescription() {
        return topDescription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPromoMessage() {
        return promoMessage;
    }

    public void setPromoMessage(String promoMessage) {
        this.promoMessage = promoMessage;
    }

    public String getBottomDescription() {
        return bottomDescription;
    }

    public void setBottomDescription(String bottomDescription) {
        this.bottomDescription = bottomDescription;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public void setTopDescription(String topDescription) {
        this.topDescription = topDescription;
    }

    public List<Item> getContent() {
        return content;
    }

    public void setContent(List<Item> content) {
        this.content = content;
    }

    @Override
    public int compareTo(Product o) {
        return this.title.compareTo(o.title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(getProductImageUrl(), product.getProductImageUrl()) &&
                Objects.equals(getTopDescription(), product.getTopDescription()) &&
                Objects.equals(getTitle(), product.getTitle()) &&
                Objects.equals(getPromoMessage(), product.getPromoMessage()) &&
                Objects.equals(getBottomDescription(), product.getBottomDescription()) &&
                Objects.equals(getContent(), product.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductImageUrl(), getTopDescription(), getTitle(), getPromoMessage(), getBottomDescription(), getContent());
    }

    @Override
    public String toString() {
        return "Product{" +
                "productImageUrl='" + productImageUrl + '\'' +
                ", topDescription='" + topDescription + '\'' +
                ", title='" + title + '\'' +
                ", bottomDescription='" + bottomDescription + '\'' +
                '}';
    }
}
