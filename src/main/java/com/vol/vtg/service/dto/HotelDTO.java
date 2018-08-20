package com.vol.vtg.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.vol.vtg.domain.enumeration.HotelType;

/**
 * A DTO for the Hotel entity.
 */
public class HotelDTO implements Serializable {

    private Long id;

    private String name;

    private Integer starRank;

    private String slogan;

    private String address;

    private Double priceFrom;

    private Double priceTo;

    private Integer rank;

    private Double latitude;

    private Double longitude;

    private Integer provinceId;

    private HotelType hotelType;

    private String filePath1;

    private String filePath2;

    private String filePath3;

    private String filePath4;

    private String filePath5;

    private Integer placeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStarRank() {
        return starRank;
    }

    public void setStarRank(Integer starRank) {
        this.starRank = starRank;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Double priceTo) {
        this.priceTo = priceTo;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public HotelType getHotelType() {
        return hotelType;
    }

    public void setHotelType(HotelType hotelType) {
        this.hotelType = hotelType;
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

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HotelDTO hotelDTO = (HotelDTO) o;
        if (hotelDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hotelDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HotelDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", starRank=" + getStarRank() +
            ", slogan='" + getSlogan() + "'" +
            ", address='" + getAddress() + "'" +
            ", priceFrom=" + getPriceFrom() +
            ", priceTo=" + getPriceTo() +
            ", rank=" + getRank() +
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
