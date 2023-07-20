package com.example.maple.Repository;

import com.example.maple.Entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    @Override
    ArrayList<Equipment> findAll();
    Optional<Equipment> findByEtype(String etype);
}
