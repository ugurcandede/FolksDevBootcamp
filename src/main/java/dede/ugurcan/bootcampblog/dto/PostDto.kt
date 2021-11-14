package dede.ugurcan.bootcampblog.dto

import com.fasterxml.jackson.annotation.JsonInclude
import dede.ugurcan.bootcampblog.model.PostStatus
import java.time.LocalDateTime

data class PostDto @JvmOverloads constructor(

    val id: String?,
    val title: String,
    val body: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val status: PostStatus,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val user: UserDto? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val comments: List<CommentDto>? = null,
)
