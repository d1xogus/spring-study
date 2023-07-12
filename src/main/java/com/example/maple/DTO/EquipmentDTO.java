package com.example.maple.DTO;
import com.example.maple.Entity.Equipment;
import com.example.maple.Entity.Member;
import lombok.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class EquipmentDTO {
    private Long e_id;
    private Member member;
    // 물품 이름
    private String e_name;
    //판매 구매 여부
    private String e_method;
    // 물품 종류
    private String e_type;
    // 물품 가격
    private Long e_price;
    // 물품 정보
    private String e_info;

    public static Equipment toEquipmentEntity(EquipmentDTO equipmentDTO) {
        Equipment equipment = new Equipment();
        equipment.setE_id(equipmentDTO.getE_id());
        equipment.setMember(equipmentDTO.getMember());
        equipment.setE_name(equipmentDTO.getE_name());
        equipment.setE_price(equipmentDTO.getE_price());
        equipment.setE_method(equipmentDTO.getE_method());
        equipment.setE_info(equipmentDTO.getE_info());
        equipment.setE_type(equipmentDTO.getE_type());
        return equipment;
    }

    public static Equipment toupdateEntity(EquipmentDTO equipmentDTO) {
        Equipment equipment = new Equipment();
        equipment.setMember(equipmentDTO.getMember());
        equipment.setE_name(equipmentDTO.getE_name());
        equipment.setE_price(equipmentDTO.getE_price());
        equipment.setE_method(equipmentDTO.getE_method());
        equipment.setE_info(equipmentDTO.getE_info());
        equipment.setE_type(equipmentDTO.getE_type());
        return equipment;
    }
}
