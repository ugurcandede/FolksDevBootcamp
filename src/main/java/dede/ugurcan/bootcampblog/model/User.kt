package dede.ugurcan.bootcampblog.model

import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "users")
data class User @JvmOverloads constructor(

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val username: String,
    val email: String,
    val displayName: String,
    val isActive: Boolean = false,

    @OneToMany(mappedBy = "user")
    val posts: List<Post>

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as User

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , username = $username , email = $email , displayName = $displayName , isActive = $isActive )"
    }
}