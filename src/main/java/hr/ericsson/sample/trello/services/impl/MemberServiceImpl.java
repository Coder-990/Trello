package hr.ericsson.sample.trello.services.impl;

import hr.ericsson.sample.trello.repositories.MemberRepository;
import hr.ericsson.sample.trello.repositories.models.Member;
import hr.ericsson.sample.trello.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(Long id, Member member) {
        return getMemberById(id)
                .map(existingMember -> {
                    existingMember.setName(member.getName());
                    existingMember.setLastName(member.getLastName());
                    existingMember.setEmail(member.getEmail());
                    existingMember.setPhone(member.getPhone());
                    existingMember.setCards(member.getCards());
                    return memberRepository.save(existingMember);
                }).orElseThrow(() -> new RuntimeException("Member not found"));
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
