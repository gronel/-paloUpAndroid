package biz.fastgroup.apps.paloup.app

import android.app.Application

import com.facebook.stetho.Stetho
import io.socket.client.IO
import io.socket.client.Socket

import java.net.URISyntaxException

import io.realm.Realm
import io.realm.RealmConfiguration

import biz.fastgroup.apps.paloup.helper.Constant.NOTIFICATION_SERVER_URI

/*import io.realm.Realm;
import io.realm.RealmConfiguration;*/


class MyApplication : Application() {

    var mSocket: Socket

    override fun onCreate() {

        super.onCreate()
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)

        Stetho.initializeWithDefaults(this)
    }

    init {
        try {
            mSocket = IO.socket(NOTIFICATION_SERVER_URI)
        } catch (e: URISyntaxException) {
            throw RuntimeException(e)
        }

    }

    fun getmSocket(): Socket {
        return mSocket
    }
}
