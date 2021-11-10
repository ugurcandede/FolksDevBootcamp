package dede.ugurcan.bootcampblog.dto.request

import javax.validation.constraints.NotBlank

data class CreateCommentRequest(

    @field:NotBlank
    val userId: String,

    @field:NotBlank
    val postId: String,

    @field:NotBlank
    val body: String,
)
