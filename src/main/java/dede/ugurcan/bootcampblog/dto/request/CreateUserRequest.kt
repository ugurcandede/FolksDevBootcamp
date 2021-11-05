package dede.ugurcan.bootcampblog.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class CreateUserRequest(

    @field:NotEmpty
    val username: String,

    @field:Email
    val email: String,

    @field:NotEmpty
    val displayName: String
)
