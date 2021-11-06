package dede.ugurcan.bootcampblog.dto.request

import javax.validation.constraints.NotBlank

data class UpdateCommentRequest(

    @field:NotBlank
    val body: String,
)
