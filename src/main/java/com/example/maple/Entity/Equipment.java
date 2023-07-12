package com.example.maple.Entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "equipment_tb")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equipment extends Time{

    // 물품 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 생성
    private Long e_id;

    // 판매/구매 사용자
    @ManyToOne // JPA(Java Persistence API)에서 엔티티 간의 관계를 로딩하는 방식을 지정하는 데 사용되는 옵션 LAZY가 기본
    @JoinColumn(name = "m_id")
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

    public void patch(Equipment equipment) {
        if(equipment.e_name != null){
            this.e_name = equipment.e_name;
        }
        if(equipment.e_price != null){
            this.e_name = equipment.e_name;
        }
        if (equipment.e_info != null){
            this.e_info = equipment.e_info;
        }
        if (equipment.e_method != null){
            this.e_method = equipment.e_method;
        }
        if (equipment.e_type != null){
            this.e_type = equipment.e_type;
        }
    }
}
