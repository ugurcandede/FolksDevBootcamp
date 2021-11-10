package dede.ugurcan.bootcampblog.dto.request

import javax.validation.constraints.NotBlank

data class UpdateUserRequest(

    @field:NotBlank
    val displayName: String
)
