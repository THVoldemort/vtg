package com.vol.vtg.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import com.vol.vtg.domain.enumeration.HotelType;

/**
 * A Hotel.
 */
@Entity
@Table(name = "hotel")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "star_rank")
    private Integer starRank;

    @Column(name = "slogan")
    private String slogan;

    @Column(name = "address")
    private String address;

    @Column(name = "price_from")
    private Double priceFrom;

    @Column(name = "price_to")
    private Double priceTo;

    @Column(name = "view_count")
    private Integer viewCount;

    @Column(name = "convenient")
    private Integer convenient;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "confirm_booking_type")
    private Integer confirmBookingType;

    @Column(name = "rating_id")
    private Integer ratingId;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "province_id")
    private Integer provinceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "hotel_type")
    private HotelType hotelType;

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

    @Column(name = "place_id")
    private Integer placeId;

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

    public Hotel name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStarRank() {
        return starRank;
    }

    public Hotel starRank(Integer starRank) {
        this.starRank = starRank;
        return this;
    }

    public void setStarRank(Integer starRank) {
        this.starRank = starRank;
    }

    public String getSlogan() {
        return slogan;
    }

    public Hotel slogan(String slogan) {
        this.slogan = slogan;
        return this;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getAddress() {
        return address;
    }

    public Hotel address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPriceFrom() {
        return priceFrom;
    }

    public Hotel priceFrom(Double priceFrom) {
        this.priceFrom = priceFrom;
        return this;
    }

    public void setPriceFrom(Double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Double getPriceTo() {
        return priceTo;
    }

    public Hotel priceTo(Double priceTo) {
        this.priceTo = priceTo;
        return this;
    }

    public void setPriceTo(Double priceTo) {
        this.priceTo = priceTo;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public Hotel viewCount(Integer viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getConvenient() {
        return convenient;
    }

    public Hotel convenient(Integer convenient) {
        this.convenient = convenient;
        return this;
    }

    public void setConvenient(Integer convenient) {
        this.convenient = convenient;
    }

    public String getIntroduction() {
        return introduction;
    }

    public Hotel introduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getConfirmBookingType() {
        return confirmBookingType;
    }

    public Hotel confirmBookingType(Integer confirmBookingType) {
        this.confirmBookingType = confirmBookingType;
        return this;
    }

    public void setConfirmBookingType(Integer confirmBookingType) {
        this.confirmBookingType = confirmBookingType;
    }

    public Integer getRatingId() {
        return ratingId;
    }

    public Hotel ratingId(Integer ratingId) {
        this.ratingId = ratingId;
        return this;
    }

    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Hotel latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Hotel longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public Hotel provinceId(Integer provinceId) {
        this.provinceId = provinceId;
        return this;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public HotelType getHotelType() {
        return hotelType;
    }

    public Hotel hotelType(HotelType hotelType) {
        this.hotelType = hotelType;
        return this;
    }

    public void setHotelType(HotelType hotelType) {
        this.hotelType = hotelType;
    }

    public String getFilePath1() {
        return filePath1;
    }

    public Hotel filePath1(String filePath1) {
        this.filePath1 = filePath1;
        return this;
    }

    public void setFilePath1(String filePath1) {
        this.filePath1 = filePath1;
    }

    public String getFilePath2() {
        return filePath2;
    }

    public Hotel filePath2(String filePath2) {
        this.filePath2 = filePath2;
        return this;
    }

    public void setFilePath2(String filePath2) {
        this.filePath2 = filePath2;
    }

    public String getFilePath3() {
        return filePath3;
    }

    public Hotel filePath3(String filePath3) {
        this.filePath3 = filePath3;
        return this;
    }

    public void setFilePath3(String filePath3) {
        this.filePath3 = filePath3;
    }

    public String getFilePath4() {
        return filePath4;
    }

    public Hotel filePath4(String filePath4) {
        this.filePath4 = filePath4;
        return this;
    }

    public void setFilePath4(String filePath4) {
        this.filePath4 = filePath4;
    }

    public String getFilePath5() {
        return filePath5;
    }

    public Hotel filePath5(String filePath5) {
        this.filePath5 = filePath5;
        return this;
    }

    public void setFilePath5(String filePath5) {
        this.filePath5 = filePath5;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public Hotel placeId(Integer placeId) {
        this.placeId = placeId;
        return this;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
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
        Hotel hotel = (Hotel) o;
        if (hotel.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hotel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Hotel{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", starRank=" + getStarRank() +
            ", slogan='" + getSlogan() + "'" +
            ", address='" + getAddress() + "'" +
            ", priceFrom=" + getPriceFrom() +
            ", priceTo=" + getPriceTo() +
            ", viewCount=" + getViewCount() +
            ", convenient=" + getConvenient() +
            ", introduction='" + getIntroduction() + "'" +
            ", confirmBookingType=" + getConfirmBookingType() +
            ", ratingId=" + getRatingId() +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", provinceId=" + getProvinceId() +
            ", hotelType='" + getHotelType() + "'" +
            ", filePath1='" + getFilePath1() + "'" +
            ", filePath2='" + getFilePath2() + "'" +
            ", filePath3='" + getFilePath3() + "'" +
            ", filePath4='" + getFilePath4() + "'" +
            ", filePath5='" + getFilePath5() + "'" +
            ", placeId=" + getPlaceId() +
            "}";
    }
}
