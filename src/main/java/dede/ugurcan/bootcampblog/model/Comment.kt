package dede.ugurcan.bootcampblog.model

import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Comment @JvmOverloads constructor(

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val content: String,
    val date: LocalDateTime,

    @field:Enumerated(EnumType.STRING)
    val status: StatusType,

    @ManyToOne()
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    val author: User,

    @ManyToOne()
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    val post: Post,

    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Comment

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , content = $content , date = $date , status = $status , author = $author , post = $post )"
    }
}

enum class StatusType {
    PUBLISHED, DRAFT, DELETED
}