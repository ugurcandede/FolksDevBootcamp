package dede.ugurcan.bootcampblog.dto.request

import javax.validation.constraints.NotEmpty

data class CreateCommentRequest(

    @field:NotEmpty
    val userId: String,

    @field:NotEmpty
    val postId: String,

    @field:NotEmpty
    val body: String,
)
