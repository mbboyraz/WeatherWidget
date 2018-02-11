package io.androidedu.weatherwidget.core.network

import android.graphics.Bitmap
import android.support.v4.util.LruCache
import com.android.volley.toolbox.ImageLoader


/******************************
 * Created by Gökhan ÖZTÜRK   |
 * 10.02.2018                 |
 * GokhanOzturk@AndroidEdu.IO |
 *****************************/

class LruBitmapCache constructor(sizeInKB: Int = defaultLruCacheSize) :
        LruCache<String, Bitmap>(sizeInKB), ImageLoader.ImageCache {

    override fun sizeOf(key: String, value: Bitmap): Int = value.rowBytes * value.height / 1024

    override fun getBitmap(url: String): Bitmap? {
        return get(url)
    }

    override fun putBitmap(url: String, bitmap: Bitmap) {
        put(url, bitmap)
    }

    companion object {
        val defaultLruCacheSize: Int
            get() {
                return (Runtime.getRuntime().maxMemory() / 1024).toInt() / 8
            }
    }
}
