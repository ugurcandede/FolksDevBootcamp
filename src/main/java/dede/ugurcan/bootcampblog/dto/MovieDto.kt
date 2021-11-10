package dede.ugurcan.bootcampblog.dto

import com.fasterxml.jackson.annotation.JsonInclude

data class MovieDto(

    val id: Long,
    val name: String,
    val year: Int,

    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    val imdbScore: Double?,

    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    val genre: List<String>?,
)
