package server.mayfill.controller.review;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.mayfill.common.dto.ApiResponse;
import server.mayfill.config.resolver.LoginUserId;
import server.mayfill.service.review.dto.request.AddReviewRequestDto;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ReviewController {

//    @PostMapping("/v1/store/review")
//    public ApiResponse<> addReview(@Valid @RequestBody AddReviewRequestDto request, @ApiIgnore @LoginUserId Long userId) {
//
//    }

}
