package com.keyin.service;

import com.keyin.entity.Member;
import com.keyin.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    public List<Member> searchByName(String name) {
        return memberRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Member> searchByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber);
    }

    public List<Member> searchMembers(String name, String phoneNumber) {
        if (name != null && phoneNumber != null) {
            return memberRepository.findByNameContainingIgnoreCaseAndPhoneNumber(name, phoneNumber);
        } else if (name != null) {
            return memberRepository.findByNameContainingIgnoreCase(name);
        } else if (phoneNumber != null) {
            return memberRepository.findByPhoneNumber(phoneNumber);
        }
        return new ArrayList<>();
    }
}
