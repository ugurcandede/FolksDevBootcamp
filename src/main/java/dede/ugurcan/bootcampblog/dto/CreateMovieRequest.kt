package dede.ugurcan.bootcampblog.dto

import javax.validation.constraints.*

data class CreateMovieRequest(

    @field:NotEmpty
    val name: String,

    @field:NotNull
    @field:Min(1900)
    val year: Int,

    @field:DecimalMin("1.0")
    @field:DecimalMax("10.0")
    val imdbScore: Double?,

    val genre: List<String>?,
)