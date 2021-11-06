package dede.ugurcan.bootcampblog.dto.request

import javax.validation.constraints.NotEmpty

data class UpdateUserRequest(

    @field:NotEmpty
    val displayName: String
)
