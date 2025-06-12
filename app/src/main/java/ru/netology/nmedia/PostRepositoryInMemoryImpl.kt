//package ru.netology.nmedia
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import ru.netology.nmedia.dto.Post
//
//class PostRepositoryInMemoryImpl: PostRepository {
//    private var post = Post(
//        id = 1,
//        author = "Нетология. Университет интернет-профессий будущего",
//        published = "21 мая в 18:36",
//        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу." +
//                "Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и" +
//                " помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное" +
//                " остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше," +
//                " целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста" +
//                " и начать цепочку перемен → http://netolo.gy/fyb\""
//    )
//
//    private val data = MutableLiveData(post)
//
//    override fun get(): LiveData<Post> = data
//
//    override fun like() {
//        post = post.copy(likeByMe = !post.likeByMe)
//        post.likes += if (post.likeByMe) 1 else -1
//        data.value = post
//    }
//}