package com.example.maple.Repository;

import com.example.maple.Entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByE_type(String e_type);
    List<Equipment> findByE_id(Long e_id);
}
