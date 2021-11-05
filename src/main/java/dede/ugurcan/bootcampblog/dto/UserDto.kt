package dede.ugurcan.bootcampblog.dto

data class UserDto(
    val id: String,
    val username: String,
    val email: String,
    val displayName: String,
    val isActive: Boolean = false,
    val posts: List<UserPostDto>,
    val comments: List<UserCommentDto>
)
