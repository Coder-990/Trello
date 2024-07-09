package hr.ericsson.sample.trello.services;

import hr.ericsson.sample.trello.repositories.models.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    List<Member> getAllMembers();

    Optional<Member> getMemberById(Long id);

    Member createMember(Member member);

    Member updateMember(Long id, Member member);

    void deleteMember(Long id);
}
