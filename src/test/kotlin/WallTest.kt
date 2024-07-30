import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class WallTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addPostTest() {
        val array = arrayOf(
            AudioAttachment(Audio(1, 2)),
            VideoAttachment(Video(1, "title"))
        )
        val post = Post(
            1,
            2,
            "author",
            null,
            array
        )
        WallService.add(post)

        assertEquals(1, WallService.getPosts().size)

    }


    @Test
    fun noUpdateTestId() {
        val array = arrayOf(
            AudioAttachment(Audio(1, 2)),
            VideoAttachment(Video(1, "title"))
        )
        val post = Post(1, 1, "author", null, array)
        val postTest = Post(2, 1, "author", null, array)

        WallService.add(post)
        val result = WallService.update(postTest)
        assertEquals(false, result)

    }

    @Test
    fun UpdateTestId() {
        val array = arrayOf(
            AudioAttachment(Audio(1, 2)),
            VideoAttachment(Video(1, "title"))
        )
        val post = Post(1, 1, "author", null, array)
        val postTest = Post(1, 1, "author", null, array)

        WallService.add(post)
        val result = WallService.update(postTest)
        assertEquals(true, result)

    }

}