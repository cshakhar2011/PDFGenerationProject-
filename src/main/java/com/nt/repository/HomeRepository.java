package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entities.HomeEntity;

@Repository
public interface HomeRepository extends JpaRepository<HomeEntity, Integer>{

}
