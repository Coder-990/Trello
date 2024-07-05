package hr.ericsson.sample.trello.services;

import hr.ericsson.sample.trello.repositories.models.Member;

import java.util.List;

public interface MemberService {
    List<Member> getAllMembers();

    Member getMemberById(Long id);

    Member createMember(Member member);

    Member updateMember(Long id, Member member);

    void deleteMember(Long id);
}
