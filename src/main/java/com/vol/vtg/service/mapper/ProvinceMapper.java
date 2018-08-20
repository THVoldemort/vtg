package com.vol.vtg.service.mapper;

import com.vol.vtg.domain.*;
import com.vol.vtg.service.dto.ProvinceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Province and its DTO ProvinceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProvinceMapper extends EntityMapper<ProvinceDTO, Province> {



    default Province fromId(Long id) {
        if (id == null) {
            return null;
        }
        Province province = new Province();
        province.setId(id);
        return province;
    }
}
