import org.jetbrains.annotations.Nullable

data class Post(
    val id: Int,
    val authorId: Int,
    val authorName: String,
    var likes: Likes? = null,
    val attachment: Array<Attachment>
)

data class Likes(
    var count: Int = 0,
    var canPost: Boolean = true
)

object WallService {
    private var posts = emptyArray<Post>()
    private var lastId = 0

    fun add(post: Post): Post {
        posts += post.copy(id = ++lastId, likes = post.likes?.copy())
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, fromArrPost) in posts.withIndex()) {
            if (fromArrPost.id == post.id) {
                posts[index] = post.copy(likes = post.likes?.copy())
                return true
            }
        }
        return false
    }

    fun getPosts(): Array<Post> {
        return posts
    }

    fun clear() {
        posts = emptyArray()
        lastId = 0
    }

    fun printPosts() {
        for (post in posts) {
            print(post)
            print(" ")
        }
        println()
    }
}

// Интерфейс Attachment
interface Attachment {
    val type: String
}

data class Audio(
    val id: Int,
    val duration: Int,
)

data class AudioAttachment(val audio: Audio) : Attachment {
    override val type: String = "audio"
    override fun toString(): String {
        return "Audio(type='$type', audio ='$audio')"
    }
}

data class Video(
    val duration: Int,
    val title: String
)

class VideoAttachment(val video: Video) : Attachment {
    override val type: String = "video"
    override fun toString(): String {
        return "Video(type='$type', video = '$video')"
    }
}


fun main() {

    val array = arrayOf(
        AudioAttachment(Audio(1, 2)),
        VideoAttachment(Video(1, "title"))
    )

//    for (attachment in array) {
//        print(a)
//        println(" ")
//    }
//    println()

    array.forEach { println(it) }

}