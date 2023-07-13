package com.example.maple.DTO;
import com.example.maple.Entity.Equipment;
import com.example.maple.Entity.Member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EquipmentDTO {
    private Long eid;
    private Member member;
    // 물품 이름
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
        Equipment equipment = new Equipment();
        equipment.setEid(equipmentDTO.getEid());
        equipment.setMember(equipmentDTO.getMember());
        equipment.setEname(equipmentDTO.getEname());
        equipment.setEprice(equipmentDTO.getEprice());
        equipment.setEmethod(equipmentDTO.getEmethod());
        equipment.setEinfo(equipmentDTO.getEinfo());
        equipment.setEtype(equipmentDTO.getEtype());
        return equipment;
    }

    public static Equipment toupdateEntity(EquipmentDTO equipmentDTO) {
        Equipment equipment = new Equipment();
        equipment.setMember(equipmentDTO.getMember());
        equipment.setEname(equipmentDTO.getEname());
        equipment.setEprice(equipmentDTO.getEprice());
        equipment.setEmethod(equipmentDTO.getEmethod());
        equipment.setEinfo(equipmentDTO.getEinfo());
        equipment.setEtype(equipmentDTO.getEtype());
        return equipment;
    }
}
