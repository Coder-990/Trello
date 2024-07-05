package hr.ericsson.sample.trello.repositories;

import hr.ericsson.sample.trello.repositories.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
