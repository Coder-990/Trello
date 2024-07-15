package hr.ericsson.sample.trello.controllers.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record MemberResponse(
        @Size(min = 3, max = 25) @NotBlank @NotNull String name,
        @Size(min = 3, max = 25) @NotBlank @NotNull String lastName,
        @Size(max = 130) @NotBlank @NotNull String email,
        @NotBlank @NotNull String phone
) {
}
