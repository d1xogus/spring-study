package com.example.maple.controller;

import com.example.maple.DTO.MemberDTO;
import com.example.maple.Entity.Member;
import com.example.maple.Service.MemberService;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private final MemberService memberService;

    //사용자 정보 조회
    @ApiOperation(value = "사용자 정보 조회")
    @GetMapping("/{mid}")
    public Optional<Member> index(@PathVariable Long mid){
        return memberService.index(mid);
    }

    @ApiOperation(value = "사용자 생성")
    @PostMapping("/new")
    public ResponseEntity<Member> create(@RequestBody MemberDTO memberDTO){
        Member created = memberService.create(memberDTO);
        return (created != null) ?
                ResponseEntity.status(201).body(created) :
                ResponseEntity.status(400).build();
    }

    @ApiOperation(value = "사용자 정보 수정")
    @PatchMapping("/{mid}")
    public ResponseEntity<Member> update(@PathVariable Long mid, @RequestBody MemberDTO memberDTO){
        Member updated = memberService.update(mid, memberDTO);
        return (updated != null) ?
                ResponseEntity.status(200).body(updated) :
                ResponseEntity.status(400).build();
    }

    @ApiOperation(value = "사용자 삭제")
    @DeleteMapping("/{mid}")
    public ResponseEntity<Member> delete(@PathVariable Long mid){
        Member deleted = memberService.delete(mid);
        return (deleted != null) ?
                ResponseEntity.status(200).body(deleted) :
                ResponseEntity.status(404).build();
    }
}

