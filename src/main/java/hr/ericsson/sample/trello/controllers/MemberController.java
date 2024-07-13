package hr.ericsson.sample.trello.controllers;

import hr.ericsson.sample.trello.controllers.request.AddMemberRequest;
import hr.ericsson.sample.trello.controllers.request.ModifyMemberRequest;
import hr.ericsson.sample.trello.controllers.response.MemberResponse;
import hr.ericsson.sample.trello.controllers.response.MembersResponse;
import hr.ericsson.sample.trello.exceptions.NotFoundException;
import hr.ericsson.sample.trello.mappers.MemberMapper;
import hr.ericsson.sample.trello.services.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/members")
@CrossOrigin(origins = "http://localhost:4200")
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public MembersResponse fetchAllMembers() {
        log.info("Fetching all members from sample trello ...");
        var members = memberService.getAllMembers();
        var membersResponses = memberMapper.toMembersResponse(members);
        log.info("Fetched all members with body {}...", membersResponses);
        return membersResponses;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponse fetchMemberById(@PathVariable Long id) {
        log.info("Fetching member with id {}... ", id);
        var member = memberService.getMemberById(id);
        var memberResponse = member.map(memberMapper::toMemberResponse)
                .orElseThrow(() -> new NotFoundException(("Could not find member by this id %s").formatted(id)));
        log.info("Fetched member for id {} with body {}", id, memberResponse);
        return memberResponse;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponse postMember(@Validated @RequestBody AddMemberRequest addMemberRequest) {
        log.info("Creating member with body {}...", addMemberRequest);
        var member = memberMapper.toAddMember(addMemberRequest);
        var savedMember = memberService.createMember(member);
        var memberResponse = memberMapper.toMemberResponse(savedMember);
        log.info("Created member for id: {} with body {}  ...", savedMember.getId(), memberResponse);
        return memberResponse;
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public MemberResponse putMember(@PathVariable Long id, @Validated @RequestBody
    ModifyMemberRequest modifyMemberRequest) {
        log.info("Modifying member for id: {} with body {}...", id, modifyMemberRequest);
        var member = memberMapper.toModifyMember(modifyMemberRequest);
        var updatedMember = memberService.updateMember(id, member);
        var memberResponse = memberMapper.toMemberResponse(updatedMember);
        log.info("Modified member for id: {} with body {}...", id, memberResponse);
        return memberResponse;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMember(@PathVariable Long id) {
        log.info("Removing member with id: {}...", id);
        memberService.deleteMember(id);
        log.info("Removed member for id: {}...", id);
    }
}
