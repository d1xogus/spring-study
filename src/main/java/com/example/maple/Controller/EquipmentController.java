package com.example.maple.Controller;

import com.example.maple.DTO.EquipmentDTO;
import com.example.maple.Service.EquipmentService;
import com.example.maple.Entity.Equipment;
import com.example.maple.Repository.EquipmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/equipments")
    public List<Equipment> index(){
        return equipmentService.index();
    }

    @GetMapping("/equipments/{e_type}")
    public List<Equipment> indexByType(@PathVariable String e_type){
        return equipmentService.indexByType(e_type);
    }

    @GetMapping("/equipments/{e_id}")
    public List<Equipment> indexById(@PathVariable Long e_id){
        return equipmentService.indexById(e_id);
    }

    @PostMapping("/equipments/new")
    public ResponseEntity<Equipment> create(@RequestBody EquipmentDTO equipmentDTO){
        Equipment created = equipmentService.create(equipmentDTO);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/equipments/{e_id}")
    public ResponseEntity<Equipment> update(@PathVariable Long e_id, @RequestBody EquipmentDTO equipmentDTO){
        Equipment updated = equipmentService.update(e_id, equipmentDTO);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/equipments/e_id}")
    public ResponseEntity<Equipment> delete(@PathVariable Long e_id){
        Equipment deleted = equipmentService.delete(e_id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).body(deleted) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
