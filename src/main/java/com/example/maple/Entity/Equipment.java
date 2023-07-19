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
    private Long eid;

    // 판매/구매 사용자
  //  @ManyToOne // JPA(Java Persistence API)에서 엔티티 간의 관계를 로딩하는 방식을 지정하는 데 사용되는 옵션 LAZY가 기본
  //  @JoinColumn(name = "mid") //일대다 단방향을 @JoinColumn필수
//    private Member member;
    private Long mem; // 토큰으로 해야함
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

    public void patch(Equipment equipment) {
        if(equipment.ename != null){
            this.ename = equipment.ename;
        }
        if(equipment.mem != null){
            this.mem = equipment.mem;
        }
        if(equipment.eprice != null){
            this.eprice = equipment.eprice
            ;
        }
        if (equipment.einfo != null){
            this.einfo = equipment.einfo;
        }
        if (equipment.emethod != null){
            this.emethod = equipment.emethod;
        }
        if (equipment.etype != null){
            this.etype = equipment.etype;
        }
    }
}
