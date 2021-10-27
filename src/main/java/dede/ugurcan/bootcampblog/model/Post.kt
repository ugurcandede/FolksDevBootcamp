package dede.ugurcan.bootcampblog.model

import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Post @JvmOverloads constructor(

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val title: String,
    val body: String,
    val createDate: LocalDateTime,

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: User,

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL])
    val comments: List<Comment>,

    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Post

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , title = $title , body = $body , createDate = $createDate , user = $user )"
    }
}

