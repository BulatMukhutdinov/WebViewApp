package tat.flexsentlabs.webviewapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_picture.back
import kotlinx.android.synthetic.main.activity_picture.picture

class PictureActivity : FullScreenActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)

        when (intent.getIntExtra(PICTURE_NUMBER_EXTRA, 0)) {
            1 -> picture.background = ContextCompat.getDrawable(this, R.drawable.pic1)
            2 -> picture.background = ContextCompat.getDrawable(this, R.drawable.pic2)
            3 -> picture.background = ContextCompat.getDrawable(this, R.drawable.pic3)
            4 -> picture.background = ContextCompat.getDrawable(this, R.drawable.pic4)
        }

        back.setOnClickListener { onBackPressed() }
    }

    companion object {
        private const val PICTURE_NUMBER_EXTRA = "extra_link"

        fun start(caller: AppCompatActivity, pictureNumber: Int) {
            val intent = Intent(caller, PictureActivity::class.java).apply { putExtra(PICTURE_NUMBER_EXTRA, pictureNumber) }
            caller.startActivity(intent)
        }
    }
}