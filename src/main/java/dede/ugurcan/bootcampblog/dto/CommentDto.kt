package dede.ugurcan.bootcampblog.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

data class CommentDto @JvmOverloads constructor(

    val id: String?,
    val body: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val author: UserDto? = null,
)
