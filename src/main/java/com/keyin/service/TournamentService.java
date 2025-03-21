package com.keyin.service;

import com.keyin.entity.Member;
import com.keyin.entity.Tournament;
import com.keyin.repository.MemberRepository;
import com.keyin.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Tournament addTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Optional<Tournament> getTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }

    public void deleteTournament(Long id) {
        tournamentRepository.deleteById(id);
    }

    public List<Tournament> searchByStartDate(LocalDate startDate) {
        return tournamentRepository.findByStartDate(startDate);
    }

    public List<Tournament> searchByLocation(String location) {
        return tournamentRepository.findByLocationContainingIgnoreCase(location);
    }

    public Tournament addMemberToTournament(Long tournamentId, Long memberId) {
        Optional<Tournament> tournamentOptional = tournamentRepository.findById(tournamentId);
        Optional<Member> memberOptional = memberRepository.findById(memberId);

        if (tournamentOptional.isPresent() && memberOptional.isPresent()) {
            Tournament tournament = tournamentOptional.get();
            Member member = memberOptional.get();

            tournament.getMembers().add(member);
            return tournamentRepository.save(tournament);
        } else {
            throw new RuntimeException("Tournament or Member not found");
        }
    }
}
