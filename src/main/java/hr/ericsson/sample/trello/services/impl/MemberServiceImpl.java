package hr.ericsson.sample.trello.services.impl;

import hr.ericsson.sample.trello.repositories.MemberRepository;
import hr.ericsson.sample.trello.repositories.models.Member;
import hr.ericsson.sample.trello.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(Long id) {
        return memberRepository.getReferenceById(id);
    }

    @Override
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(Long id, Member member) {
        var existingCard = getMemberById(id);
        existingCard.setName(member.getName());
        existingCard.setLastName(member.getLastName());
        existingCard.setEmail(member.getEmail());
        existingCard.setPhone(member.getPhone());
        existingCard.setCard(member.getCard());
        return memberRepository.saveAndFlush(existingCard);
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
