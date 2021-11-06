package dede.ugurcan.bootcampblog.dto.request

import dede.ugurcan.bootcampblog.model.PostStatus
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.validation.constraints.NotBlank

data class UpdatePostRequest(
    @field:NotBlank
    val title: String,

    @field:NotBlank
    val body: String,

    @field:Enumerated(EnumType.STRING)
    val status: PostStatus = PostStatus.DRAFT,
)
