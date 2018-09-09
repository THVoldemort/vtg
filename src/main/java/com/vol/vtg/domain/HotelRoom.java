package com.vol.vtg.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A HotelRoom.
 */
@Entity
@Table(name = "hotel_room")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HotelRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_type")
    private Integer type;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "convenient")
    private Integer convenient;

    @Column(name = "cancel_fee_day_1")
    private Integer cancelFeeDay1;

    @Column(name = "cancel_fee_day_2")
    private Integer cancelFeeDay2;

    @Column(name = "cancel_fee_time_1")
    private String cancelFeeTime1;

    @Column(name = "cancel_fee_time_2")
    private String cancelFeeTime2;

    @Column(name = "rating_id")
    private Integer ratingId;

    @Column(name = "num_of_adult")
    private Integer numOfAdult;

    @Column(name = "num_of_child")
    private Integer numOfChild;

    @Column(name = "price_est")
    private Double priceEst;

    @Column(name = "price_act")
    private Double priceAct;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "file_path_1")
    private String filePath1;

    @Column(name = "file_path_2")
    private String filePath2;

    @Column(name = "file_path_3")
    private String filePath3;

    @Column(name = "file_path_4")
    private String filePath4;

    @Column(name = "file_path_5")
    private String filePath5;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public HotelRoom type(Integer type) {
        this.type = type;
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIntroduction() {
        return introduction;
    }

    public HotelRoom introduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getConvenient() {
        return convenient;
    }

    public HotelRoom convenient(Integer convenient) {
        this.convenient = convenient;
        return this;
    }

    public void setConvenient(Integer convenient) {
        this.convenient = convenient;
    }

    public Integer getCancelFeeDay1() {
        return cancelFeeDay1;
    }

    public HotelRoom cancelFeeDay1(Integer cancelFeeDay1) {
        this.cancelFeeDay1 = cancelFeeDay1;
        return this;
    }

    public void setCancelFeeDay1(Integer cancelFeeDay1) {
        this.cancelFeeDay1 = cancelFeeDay1;
    }

    public Integer getCancelFeeDay2() {
        return cancelFeeDay2;
    }

    public HotelRoom cancelFeeDay2(Integer cancelFeeDay2) {
        this.cancelFeeDay2 = cancelFeeDay2;
        return this;
    }

    public void setCancelFeeDay2(Integer cancelFeeDay2) {
        this.cancelFeeDay2 = cancelFeeDay2;
    }

    public String getCancelFeeTime1() {
        return cancelFeeTime1;
    }

    public HotelRoom cancelFeeTime1(String cancelFeeTime1) {
        this.cancelFeeTime1 = cancelFeeTime1;
        return this;
    }

    public void setCancelFeeTime1(String cancelFeeTime1) {
        this.cancelFeeTime1 = cancelFeeTime1;
    }

    public String getCancelFeeTime2() {
        return cancelFeeTime2;
    }

    public HotelRoom cancelFeeTime2(String cancelFeeTime2) {
        this.cancelFeeTime2 = cancelFeeTime2;
        return this;
    }

    public void setCancelFeeTime2(String cancelFeeTime2) {
        this.cancelFeeTime2 = cancelFeeTime2;
    }

    public Integer getRatingId() {
        return ratingId;
    }

    public HotelRoom ratingId(Integer ratingId) {
        this.ratingId = ratingId;
        return this;
    }

    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    public Integer getNumOfAdult() {
        return numOfAdult;
    }

    public HotelRoom numOfAdult(Integer numOfAdult) {
        this.numOfAdult = numOfAdult;
        return this;
    }

    public void setNumOfAdult(Integer numOfAdult) {
        this.numOfAdult = numOfAdult;
    }

    public Integer getNumOfChild() {
        return numOfChild;
    }

    public HotelRoom numOfChild(Integer numOfChild) {
        this.numOfChild = numOfChild;
        return this;
    }

    public void setNumOfChild(Integer numOfChild) {
        this.numOfChild = numOfChild;
    }

    public Double getPriceEst() {
        return priceEst;
    }

    public HotelRoom priceEst(Double priceEst) {
        this.priceEst = priceEst;
        return this;
    }

    public void setPriceEst(Double priceEst) {
        this.priceEst = priceEst;
    }

    public Double getPriceAct() {
        return priceAct;
    }

    public HotelRoom priceAct(Double priceAct) {
        this.priceAct = priceAct;
        return this;
    }

    public void setPriceAct(Double priceAct) {
        this.priceAct = priceAct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public HotelRoom quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getFilePath1() {
        return filePath1;
    }

    public HotelRoom filePath1(String filePath1) {
        this.filePath1 = filePath1;
        return this;
    }

    public void setFilePath1(String filePath1) {
        this.filePath1 = filePath1;
    }

    public String getFilePath2() {
        return filePath2;
    }

    public HotelRoom filePath2(String filePath2) {
        this.filePath2 = filePath2;
        return this;
    }

    public void setFilePath2(String filePath2) {
        this.filePath2 = filePath2;
    }

    public String getFilePath3() {
        return filePath3;
    }

    public HotelRoom filePath3(String filePath3) {
        this.filePath3 = filePath3;
        return this;
    }

    public void setFilePath3(String filePath3) {
        this.filePath3 = filePath3;
    }

    public String getFilePath4() {
        return filePath4;
    }

    public HotelRoom filePath4(String filePath4) {
        this.filePath4 = filePath4;
        return this;
    }

    public void setFilePath4(String filePath4) {
        this.filePath4 = filePath4;
    }

    public String getFilePath5() {
        return filePath5;
    }

    public HotelRoom filePath5(String filePath5) {
        this.filePath5 = filePath5;
        return this;
    }

    public void setFilePath5(String filePath5) {
        this.filePath5 = filePath5;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HotelRoom hotelRoom = (HotelRoom) o;
        if (hotelRoom.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hotelRoom.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HotelRoom{" +
            "id=" + getId() +
            ", type=" + getType() +
            ", introduction='" + getIntroduction() + "'" +
            ", convenient=" + getConvenient() +
            ", cancelFeeDay1=" + getCancelFeeDay1() +
            ", cancelFeeDay2=" + getCancelFeeDay2() +
            ", cancelFeeTime1='" + getCancelFeeTime1() + "'" +
            ", cancelFeeTime2='" + getCancelFeeTime2() + "'" +
            ", ratingId=" + getRatingId() +
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
