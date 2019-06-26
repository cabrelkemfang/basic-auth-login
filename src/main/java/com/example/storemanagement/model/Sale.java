package com.example.storemanagement.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Sale {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column
  private String itermName;

  @Column
  private Integer quantity;

  @Column
  private Integer unitPrice;

  @Column
  private Integer totalAmount;

  @Column
  private String saleBy;

  @Column
  private Date saleDate;

  public Sale() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getItermName() {
    return itermName;
  }

  public void setItermName(String itermName) {
    this.itermName = itermName;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Integer getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(Integer unitPrice) {
    this.unitPrice = unitPrice;
  }

  public Integer getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Integer totalAmount) {
    this.totalAmount = totalAmount;
  }

  public String getSaleBy() {
    return saleBy;
  }

  public void setSaleBy(String saleBy) {
    this.saleBy = saleBy;
  }

  public Date getSaleDate() {
    return saleDate;
  }

  public void setSaleDate(Date saleDate) {
    this.saleDate = saleDate;
  }
}
