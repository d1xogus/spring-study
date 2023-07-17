package com.example.maple.controller;

import com.example.maple.DTO.MemberDTO;
import com.example.maple.Entity.Member;
import com.example.maple.Service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    //사용자 정보 조회
    @GetMapping("/members/{mid}")
    public List<Member> index(@PathVariable String mid){
        return memberService.index();
    }

    @PostMapping("/members/new")
    public ResponseEntity<Member> create(@RequestBody MemberDTO memberDTO){
        Member created = memberService.create(memberDTO);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/members/{mid}")
    public ResponseEntity<Member> update(@PathVariable String mid, @RequestBody MemberDTO memberDTO){
        Member updated = memberService.update(mid, memberDTO);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/members/{mid}")
    public ResponseEntity<Member> delete(@PathVariable String mid){
        Member deleted = memberService.delete(mid);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).body(deleted) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

