package com.vol.vtg.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the HotelRoom entity.
 */
public class HotelRoomDTO implements Serializable {

    private Long id;

    private Integer type;

    private String introduction;

    private Integer numOfAdult;

    private Integer numOfChild;

    private Double priceEst;

    private Double priceAct;

    private Integer quantity;

    private String filePath1;

    private String filePath2;

    private String filePath3;

    private String filePath4;

    private String filePath5;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getNumOfAdult() {
        return numOfAdult;
    }

    public void setNumOfAdult(Integer numOfAdult) {
        this.numOfAdult = numOfAdult;
    }

    public Integer getNumOfChild() {
        return numOfChild;
    }

    public void setNumOfChild(Integer numOfChild) {
        this.numOfChild = numOfChild;
    }

    public Double getPriceEst() {
        return priceEst;
    }

    public void setPriceEst(Double priceEst) {
        this.priceEst = priceEst;
    }

    public Double getPriceAct() {
        return priceAct;
    }

    public void setPriceAct(Double priceAct) {
        this.priceAct = priceAct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getFilePath1() {
        return filePath1;
    }

    public void setFilePath1(String filePath1) {
        this.filePath1 = filePath1;
    }

    public String getFilePath2() {
        return filePath2;
    }

    public void setFilePath2(String filePath2) {
        this.filePath2 = filePath2;
    }

    public String getFilePath3() {
        return filePath3;
    }

    public void setFilePath3(String filePath3) {
        this.filePath3 = filePath3;
    }

    public String getFilePath4() {
        return filePath4;
    }

    public void setFilePath4(String filePath4) {
        this.filePath4 = filePath4;
    }

    public String getFilePath5() {
        return filePath5;
    }

    public void setFilePath5(String filePath5) {
        this.filePath5 = filePath5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HotelRoomDTO hotelRoomDTO = (HotelRoomDTO) o;
        if (hotelRoomDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hotelRoomDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HotelRoomDTO{" +
            "id=" + getId() +
            ", type=" + getType() +
            ", introduction='" + getIntroduction() + "'" +
            ", numOfAdult=" + getNumOfAdult() +
            ", numOfChild=" + getNumOfChild() +
            ", priceEst=" + getPriceEst() +
            ", priceAct=" + getPriceAct() +
            ", quantity=" + getQuantity() +
            ", filePath1='" + getFilePath1() + "'" +
            ", filePath2='" + getFilePath2() + "'" +
            ", filePath3='" + getFilePath3() + "'" +
            ", filePath4='" + getFilePath4() + "'" +
            ", filePath5='" + getFilePath5() + "'" +
            "}";
    }
}
