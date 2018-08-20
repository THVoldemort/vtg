package com.vol.vtg.repository;

import com.vol.vtg.domain.HotelRoom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the HotelRoom entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HotelRoomRepository extends JpaRepository<HotelRoom, Long> {

}
