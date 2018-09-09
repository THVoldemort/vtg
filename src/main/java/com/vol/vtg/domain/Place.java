package com.vol.vtg.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Place.
 */
@Entity
@Table(name = "place")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Place implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "slogan")
    private String slogan;

    @Column(name = "address")
    private String address;

    @Column(name = "rating_id")
    private Integer ratingId;

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

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "price_from")
    private Double priceFrom;

    @Column(name = "price_to")
    private Double priceTo;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "province_id")
    private Integer provinceId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Place name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlogan() {
        return slogan;
    }

    public Place slogan(String slogan) {
        this.slogan = slogan;
        return this;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getAddress() {
        return address;
    }

    public Place address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRatingId() {
        return ratingId;
    }

    public Place ratingId(Integer ratingId) {
        this.ratingId = ratingId;
        return this;
    }

    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    public String getFilePath1() {
        return filePath1;
    }

    public Place filePath1(String filePath1) {
        this.filePath1 = filePath1;
        return this;
    }

    public void setFilePath1(String filePath1) {
        this.filePath1 = filePath1;
    }

    public String getFilePath2() {
        return filePath2;
    }

    public Place filePath2(String filePath2) {
        this.filePath2 = filePath2;
        return this;
    }

    public void setFilePath2(String filePath2) {
        this.filePath2 = filePath2;
    }

    public String getFilePath3() {
        return filePath3;
    }

    public Place filePath3(String filePath3) {
        this.filePath3 = filePath3;
        return this;
    }

    public void setFilePath3(String filePath3) {
        this.filePath3 = filePath3;
    }

    public String getFilePath4() {
        return filePath4;
    }

    public Place filePath4(String filePath4) {
        this.filePath4 = filePath4;
        return this;
    }

    public void setFilePath4(String filePath4) {
        this.filePath4 = filePath4;
    }

    public String getFilePath5() {
        return filePath5;
    }

    public Place filePath5(String filePath5) {
        this.filePath5 = filePath5;
        return this;
    }

    public void setFilePath5(String filePath5) {
        this.filePath5 = filePath5;
    }

    public String getIntroduction() {
        return introduction;
    }

    public Place introduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Double getPriceFrom() {
        return priceFrom;
    }

    public Place priceFrom(Double priceFrom) {
        this.priceFrom = priceFrom;
        return this;
    }

    public void setPriceFrom(Double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Double getPriceTo() {
        return priceTo;
    }

    public Place priceTo(Double priceTo) {
        this.priceTo = priceTo;
        return this;
    }

    public void setPriceTo(Double priceTo) {
        this.priceTo = priceTo;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Place latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Place longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public Place provinceId(Integer provinceId) {
        this.provinceId = provinceId;
        return this;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
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
        Place place = (Place) o;
        if (place.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), place.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Place{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", slogan='" + getSlogan() + "'" +
            ", address='" + getAddress() + "'" +
            ", ratingId=" + getRatingId() +
            ", filePath1='" + getFilePath1() + "'" +
            ", filePath2='" + getFilePath2() + "'" +
            ", filePath3='" + getFilePath3() + "'" +
            ", filePath4='" + getFilePath4() + "'" +
            ", filePath5='" + getFilePath5() + "'" +
            ", introduction='" + getIntroduction() + "'" +
            ", priceFrom=" + getPriceFrom() +
            ", priceTo=" + getPriceTo() +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", provinceId=" + getProvinceId() +
            "}";
    }
}
