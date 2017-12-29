package biz.fastgroup.apps.paloup.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import biz.fastgroup.apps.paloup.R
import biz.fastgroup.apps.paloup.models.UserModel
import io.realm.Realm
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {


    private var realm: Realm by Delegates.notNull()

    private var userModel: UserModel? = null
    private var mUsername: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        realm=Realm.getDefaultInstance()

        if (realm.where(UserModel::class.java!!).findAll().size == 0) {
            val n = Intent(this, LoginActivity::class.java)
            this.startActivity(n)
            finish()
        } else {

            userModel = realm.where(UserModel::class.java!!).findFirst()

            mUsername = userModel!!.username
           // onStartHubConnection()


        }
    }


}
