package com.example.maple.Service;
import com.example.maple.DTO.EquipmentDTO;
import com.example.maple.Entity.Equipment;
import com.example.maple.Repository.EquipmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EquipmentService {
    private EquipmentRepository equipmentRepository;
    public List<Equipment> index() {
        return equipmentRepository.findAll();
    }

    public Optional<Equipment> indexByType(String etype) {
        return equipmentRepository.findByEtype(etype);
    }

    public Optional<Equipment> indexById(Long eid) {
        return equipmentRepository.findByEid(eid);
    }

    public Equipment create(EquipmentDTO equipmentDTO) {
        Equipment equipment = equipmentDTO.toEquipmentEntity(equipmentDTO);
        return equipmentRepository.save(equipment);
    }

    public Equipment update(Long eid, EquipmentDTO equipmentDTO){
        Equipment equipment = equipmentDTO.toupdateEntity(equipmentDTO);
        Equipment target = equipmentRepository.findById(eid).orElse(null);
        target.patch(equipment);
        Equipment updated = equipmentRepository.save(target);
        return updated;
    }

    public Equipment delete(Long eid) {
        Equipment target = equipmentRepository.findById(eid).orElse(null);
        equipmentRepository.delete(target);
        return target;
    }
}
