package com.kdu.smarthome.dao;

import com.kdu.smarthome.entities.HouseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseUserRepository extends JpaRepository<HouseUser,Integer> {

    @Query("SELECT hu FROM HouseUser hu WHERE hu.user.username = ?1")
    List<HouseUser> findAllByUserUsername(String username);

    @Query("SELECT hu FROM HouseUser hu WHERE hu.user.username = :username AND hu.house.id = :houseID")
    HouseUser findAllByUserUsernameAndHouseId(@Param("username") String username, @Param("houseID") Integer houseID);

    @Query("SELECT hu FROM HouseUser hu WHERE hu.house.id = :houseID")
    List<HouseUser> findAllByHouseId( @Param("houseID") Integer houseID);

}
