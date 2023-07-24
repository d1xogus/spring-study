package com.example.maple.DTO;
import com.example.maple.Entity.Equipment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class EquipmentDTO {

    // 물품 이름
    private Long mem;
    private String ename;
    //판매 구매 여부
    private String emethod;
    // 물품 종류
    private String etype;
    // 물품 가격
    private Long eprice;
    // 물품 정보
    private String einfo;

    public static Equipment toEquipmentEntity(EquipmentDTO equipmentDTO) {
//        Equipment equipment = new Equipment();
//        equipment.setMem(equipmentDTO.getMem());
//        equipment.setEname(equipmentDTO.getEname());
//        equipment.setEprice(equipmentDTO.getEprice());
//        equipment.setEmethod(equipmentDTO.getEmethod());
//        equipment.setEinfo(equipmentDTO.getEinfo());
//        equipment.setEtype(equipmentDTO.getEtype());
//        return equipment;
        Equipment newEquipment = Equipment.builder()
                .einfo(equipmentDTO.getEinfo())
                .emethod(equipmentDTO.getEmethod())
                .ename(equipmentDTO.getEname())
                .eprice(equipmentDTO.getEprice())
                .etype(equipmentDTO.getEtype())
                .mem(equipmentDTO.getMem())
                .build();
        return newEquipment;
    }

    public static Equipment toupdateEntity(EquipmentDTO equipmentDTO) {
//        Equipment equipment = new Equipment();
//        equipment.setEprice(equipmentDTO.getEprice());
//        equipment.setEmethod(equipmentDTO.getEmethod());
//        equipment.setEinfo(equipmentDTO.getEinfo());
//        return equipment;
        Equipment equipment = Equipment.builder()
                .einfo(equipmentDTO.getEinfo())
                .eprice(equipmentDTO.getEprice())
                .emethod(equipmentDTO.getEmethod())
                .build();
        return equipment;
    }
}
