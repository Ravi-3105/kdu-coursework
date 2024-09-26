package com.kdu.smarthome.dao;

import com.kdu.smarthome.entities.HouseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends CrudRepository<HouseEntity, Integer> {

}
