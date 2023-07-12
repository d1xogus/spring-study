package com.example.maple.Service;
import com.example.maple.DTO.EquipmentDTO;
import com.example.maple.Entity.Equipment;
import com.example.maple.Repository.EquipmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EquipmentService {
    private EquipmentRepository equipmentRepository;
    public List<Equipment> index() {
        return equipmentRepository.findAll();
    }

    public List<Equipment> indexByType(String e_Type) {
        return equipmentRepository.findByE_type(e_Type);
    }

    public List<Equipment> indexById(Long e_id) {
        return equipmentRepository.findByE_id(e_id);
    }

    public Equipment create(EquipmentDTO equipmentDTO) {
        Equipment equipment = equipmentDTO.toEquipmentEntity(equipmentDTO);
        return equipmentRepository.save(equipment);
    }

    public Equipment update(Long e_id, EquipmentDTO equipmentDTO){
        Equipment equipment = equipmentDTO.toupdateEntity(equipmentDTO);
        Equipment target = equipmentRepository.findById(e_id).orElse(null);
        target.patch(equipment);
        Equipment updated = equipmentRepository.save(target);
        return updated;
    }

    public Equipment delete(Long e_id) {
        Equipment target = equipmentRepository.findById(e_id).orElse(null);
        equipmentRepository.delete(target);
        return target;
    }
}
