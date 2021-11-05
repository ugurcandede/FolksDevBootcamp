package dede.ugurcan.bootcampblog.dto

import java.time.LocalDateTime

data class CommentDto(
    val id: String,
    val body: String,
    val creationDate: LocalDateTime,
    val author: CommentUserDto,
)
