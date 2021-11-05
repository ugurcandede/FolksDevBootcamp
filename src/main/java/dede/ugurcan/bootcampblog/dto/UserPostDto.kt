package dede.ugurcan.bootcampblog.dto

import dede.ugurcan.bootcampblog.model.PostStatus
import java.time.LocalDateTime

data class UserPostDto(
    val id: String,
    val title: String,
    val body: String,
    val creationDate: LocalDateTime,
    val status: PostStatus,
)
