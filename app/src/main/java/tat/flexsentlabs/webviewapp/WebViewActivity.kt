package tat.flexsentlabs.webviewapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import bolts.AppLinks
import com.facebook.applinks.AppLinkData
import kotlinx.android.synthetic.main.activity_web_view.content

class WebViewActivity : FullScreenActivity() {

    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        preferences = getPreferences(Context.MODE_PRIVATE)
        editor = preferences.edit()

        setupWebView()

//        val telephonyManager = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//        val link = intent?.extras?.getString(LINK_EXTRA)
//        processLinks(link, telephonyManager.networkCountryIso)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        content.settings.javaScriptEnabled = true

        content.setOnTouchListener(this)

        content.webViewClient = object : WebViewClient() {

        }

        content.webChromeClient = object : WebChromeClient() {

        }
    }

    private fun processLinks(link: String, origin: String) {
        AppLinkData.fetchDeferredAppLinkData(this@WebViewActivity) { appLinkData ->

            try {
                saveQueryParam(appLinkData, "source")

                saveQueryParam(appLinkData, "campaign")

                saveQueryParam(appLinkData, "ad")

                saveQueryParam(appLinkData, "pid")

                saveQueryParam(appLinkData, "sub1")

                saveQueryParam(appLinkData, "sub2")

                saveQueryParam(appLinkData, "sub3")

                saveQueryParam(appLinkData, "sub4")

                saveQueryParam(appLinkData, "sub5")
            } catch (e: Exception) {
                e.printStackTrace()
            }

            try {
                saveTargetUrl("source", "organic")

                saveTargetUrl("campaign", "")

                saveTargetUrl("ad", "")

                saveTargetUrl("pid", "1")

                saveTargetUrl("sub1", "")

                saveTargetUrl("sub2", "")

                saveTargetUrl("sub3", "")

                saveTargetUrl("sub4", "")

                saveTargetUrl("sub5", "")
            } catch (e: Exception) {
                e.printStackTrace()
            }

            runOnUiThread {
                //                if (!Receiver.referrer.isEmpty()) {
//                    editor.putString("referrer", Receiver.referrer.replaceAll("-", "&").replaceAll("%3D", "="))
//                    editor.apply()
//                }
                if (!preferences.getString("referrer", "").isNullOrEmpty()) {
                    content.loadUrl("http://newmobads.com/www/app/slot/template/index.html&os=android&bundle=" + packageName + "&" + preferences.getString("referrer", ""))
                } else {
                    content.loadUrl("http://newmobads.com/www/app/slot/template/index.html?os=android&bundle=" + packageName + "&source=" + preferences.getString("source", "organic") + "&campaign=" + preferences.getString("campaign", "") + "&ad=" + preferences.getString("ad", "") + "&pid=" + preferences.getString("pid", "1") + "&sub1=" + preferences.getString("sub1", "") + "&sub2=" + preferences.getString("sub2", "") + "&sub3=" + preferences.getString("sub3", "") + "&sub4=" + preferences.getString("sub4", "") + "&sub5=" + preferences.getString("sub5", "") + "&origin=" + origin)
                }
            }
        }
    }

    private fun saveQueryParam(appLinkData: AppLinkData, key: String) {
        if (!appLinkData.targetUri.getQueryParameter(key).isNullOrEmpty()) {
            editor.putString(key, appLinkData.targetUri.getQueryParameter(key))
            editor.apply()
        }
    }

    private fun saveTargetUrl(key: String, default: String) {
        if (getString(key, default) == default && !AppLinks.getTargetUrlFromInboundIntent(this@WebViewActivity, intent).getQueryParameter(key).isNullOrEmpty()) {
            editor.putString(key, AppLinks.getTargetUrlFromInboundIntent(this@WebViewActivity, intent).getQueryParameter(key))
            editor.apply()
        }
    }

    private fun getString(key: String, default: String) =
        preferences.getString(key, default)!!

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && content.canGoBack()) {
            content.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    companion object {
        private const val LINK_EXTRA = "extra_link"

        fun start(caller: AppCompatActivity, link: String) {
            val intent = Intent(caller, WebViewActivity::class.java).apply { putExtra(LINK_EXTRA, link) }
            caller.startActivity(intent)
        }
    }
}
