package dede.ugurcan.bootcampblog.dto

import com.fasterxml.jackson.annotation.JsonInclude

data class UserDto @JvmOverloads constructor(

    val id: String?,
    val username: String,
    val email: String,
    val displayName: String,
    val isActive: Boolean = false,

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val posts: List<PostDto>? = ArrayList(),

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val comments: List<CommentDto>? = ArrayList()
)
