package com.vol.vtg.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Hotel entity.
 */
public class HotelDTO implements Serializable {

    private Long id;

    private String code;

    private String name;

    private Integer numOfRoom;

    private Double latitde;

    private Double longitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumOfRoom() {
        return numOfRoom;
    }

    public void setNumOfRoom(Integer numOfRoom) {
        this.numOfRoom = numOfRoom;
    }

    public Double getLatitde() {
        return latitde;
    }

    public void setLatitde(Double latitde) {
        this.latitde = latitde;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", numOfRoom=" + getNumOfRoom() +
            ", latitde=" + getLatitde() +
            ", longitude=" + getLongitude() +
            "}";
    }
}
