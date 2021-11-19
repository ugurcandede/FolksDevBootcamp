package dede.ugurcan.bootcampblog.dto

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel

data class UserDto @JvmOverloads constructor(

    val id: String?,
    val username: String,
    val email: String,
    val displayName: String,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val isActive: Boolean? = null,

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val posts: List<PostDto>? = ArrayList(),

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val comments: List<CommentDto>? = ArrayList()
) : RepresentationModel<UserDto>()
