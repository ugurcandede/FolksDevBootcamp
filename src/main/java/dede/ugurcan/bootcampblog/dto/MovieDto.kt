package dede.ugurcan.bootcampblog.dto

data class MovieDto(
    val id:Long,
    val name:String,
    val year:Int,
    val imdbScore:Double?,
    val genre:List<String>?,
)