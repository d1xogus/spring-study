package com.example.maple.controller;

import com.example.maple.DTO.EquipmentDTO;
import com.example.maple.Service.EquipmentService;
import com.example.maple.Entity.Equipment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping ("/equipments")
@Api(tags = {"equipment"}, description = "장비 관련 서비스")
public class EquipmentController {
    private final EquipmentService equipmentService;

    @ApiOperation(value = "모든 장비 정보 조회", notes = "모든 장비 정보 조회합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 요청"),
            @ApiResponse(code = 404, message = "찾을 수 없음"),
            @ApiResponse(code = 500, message = "내부 서버 오류"),
    })
    @GetMapping
    public List<Equipment> index(){
        return equipmentService.index();
    }

    @ApiOperation(value = "장비(종류) 정보 조회", notes = "종류로 장비를 검색합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 요청"),
            @ApiResponse(code = 404, message = "찾을 수 없음"),
            @ApiResponse(code = 500, message = "내부 서버 오류"),
    })
    @GetMapping("/type/{etype}")
    public List<Equipment> indexByType(@PathVariable String etype){
        return equipmentService.indexByType(etype);
    }

    @ApiOperation(value = "장비 정보 조회", notes = "아이디로 장비를 검색합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 요청"),
            @ApiResponse(code = 404, message = "찾을 수 없음"),
            @ApiResponse(code = 500, message = "내부 서버 오류"),
    })
    @GetMapping("/id/{eid}")
    public Optional<Equipment> indexById(@PathVariable Long eid){
        return equipmentService.indexById(eid);
    }

    @ApiOperation(value = "장비 등록", notes = "새로운 장비를 등록합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 요청"),
            @ApiResponse(code = 404, message = "찾을 수 없음"),
            @ApiResponse(code = 500, message = "내부 서버 오류"),
    })
    @PostMapping
    public ResponseEntity<Equipment> create(@RequestBody EquipmentDTO equipmentDTO){
        Equipment created = equipmentService.create(equipmentDTO);
        return (created != null) ?
                ResponseEntity.status(201).body(created) :
                ResponseEntity.status(400).build();
    }

    @ApiOperation(value = "장비 정보 수정", notes = "장비 정보를 수정합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 요청"),
            @ApiResponse(code = 404, message = "찾을 수 없음"),
            @ApiResponse(code = 500, message = "내부 서버 오류"),
    })
    @PatchMapping("/{eid}")
    public ResponseEntity<Equipment> update(@PathVariable Long eid, @RequestBody EquipmentDTO equipmentDTO){
        Equipment updated = equipmentService.update(eid, equipmentDTO);
        return (updated != null) ?
                ResponseEntity.status(200).body(updated) :
                ResponseEntity.status(400).build();
    }

    @ApiOperation(value = "장비 삭제", notes = "장비를 삭제합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 요청"),
            @ApiResponse(code = 404, message = "찾을 수 없음"),
            @ApiResponse(code = 500, message = "내부 서버 오류"),
    })
    @DeleteMapping("/{eid}")
    public ResponseEntity<Equipment> delete(@PathVariable Long eid){
        Equipment deleted = equipmentService.delete(eid);
        return (deleted != null) ?
                ResponseEntity.status(200).body(deleted) :
                ResponseEntity.status(404).build();
    }
}
