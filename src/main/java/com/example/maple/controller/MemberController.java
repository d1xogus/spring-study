package com.example.maple.Controller;

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
    @GetMapping("/members/{m_id}")
    public List<Member> index(@PathVariable String m_id){
        return memberService.index();
    }

    @PostMapping("/members/new")
    public ResponseEntity<Member> create(@RequestBody MemberDTO memberDTO){
        Member created = memberService.create(memberDTO);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/members/{m_id}")
    public ResponseEntity<Member> update(@PathVariable String m_id, @RequestBody MemberDTO memberDTO){
        Member updated = memberService.update(m_id, memberDTO);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/members/{m_id}")
    public ResponseEntity<Member> delete(@PathVariable String m_id){
        Member deleted = memberService.delete(m_id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).body(deleted) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

