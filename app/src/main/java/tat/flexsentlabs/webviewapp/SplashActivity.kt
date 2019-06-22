package tat.flexsentlabs.webviewapp

import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_splash.pic1
import kotlinx.android.synthetic.main.activity_splash.pic2
import kotlinx.android.synthetic.main.activity_splash.pic3
import kotlinx.android.synthetic.main.activity_splash.pic4

class SplashActivity : FullScreenActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        1. Приложение отправило запрос на сервер запрос с get параметром языка.
//        2.а Сервер обработал запрос. Если сервер вернул ссылку, то мы запускаем webView по этой ссылке.
//        2.b Сервер не вернул ссылку. Открыли игру/картинку.

//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://api.github.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val service: Api = retrofit.create(Api::class.java)
//
//        val call = service.getLink()
//
//        call.enqueue(object : Callback<User> {
//            override fun onResponse(call: Call<User>, response: Response<User>) {
//                WebViewActivity.start(this@SplashActivity, "linkk")
//                finish()
//            }
//
//            override fun onFailure(call: Call<User>, t: Throwable) {
//                val a = 5
//            }
//        })
        setupClick(pic1, 1)
        setupClick(pic2, 2)
        setupClick(pic3, 3)
        setupClick(pic4, 4)
    }

    private fun setupClick(image: ImageView, pictureNumber: Int) {
        image.setOnClickListener {
            PictureActivity.start(this, pictureNumber)
        }
    }
}