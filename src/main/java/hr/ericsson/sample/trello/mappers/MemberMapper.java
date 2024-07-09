package hr.ericsson.sample.trello.mappers;

import hr.ericsson.sample.trello.controllers.request.AddMemberRequest;
import hr.ericsson.sample.trello.controllers.request.ModifyMemberRequest;
import hr.ericsson.sample.trello.controllers.response.MemberResponse;
import hr.ericsson.sample.trello.repositories.models.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MemberMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cards", ignore = true)
    Member toAddMember(AddMemberRequest addMemberRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cards", ignore = true)
    Member toModifyMember(ModifyMemberRequest modifyMemberRequest);

    MemberResponse toMemberResponse(Member member);

    List<MemberResponse> toMemberListResponse(List<Member> members);
}
