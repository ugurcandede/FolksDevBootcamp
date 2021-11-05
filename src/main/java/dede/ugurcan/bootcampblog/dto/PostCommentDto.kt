package dede.ugurcan.bootcampblog.dto

import java.time.LocalDateTime

data class PostCommentDto(
    val id: String,
    val body: String,
    val creationDate: LocalDateTime,
)
