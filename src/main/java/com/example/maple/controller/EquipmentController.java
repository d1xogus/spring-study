package com.example.maple.controller;

import com.example.maple.DTO.EquipmentDTO;
import com.example.maple.Service.EquipmentService;
import com.example.maple.Entity.Equipment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/equipments")
    public List<Equipment> index(){
        return equipmentService.index();
    }

    @GetMapping("/equipments/{etype}")
    public Optional<Equipment> indexByType(@PathVariable String etype){
        return equipmentService.indexByType(etype);
    }

    @GetMapping("/equipments/{eid}")
    public Optional<Equipment> indexById(@PathVariable Long eid){
        return equipmentService.indexById(eid);
    }

    @PostMapping("/equipments/new")
    public ResponseEntity<Equipment> create(@RequestBody EquipmentDTO equipmentDTO){
        Equipment created = equipmentService.create(equipmentDTO);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/equipments/{eid}")
    public ResponseEntity<Equipment> update(@PathVariable Long eid, @RequestBody EquipmentDTO equipmentDTO){
        Equipment updated = equipmentService.update(eid, equipmentDTO);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/equipments/{eid}")
    public ResponseEntity<Equipment> delete(@PathVariable Long eid){
        Equipment deleted = equipmentService.delete(eid);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).body(deleted) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
