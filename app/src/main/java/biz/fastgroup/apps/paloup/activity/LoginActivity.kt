package biz.fastgroup.apps.paloup.activity



import android.content.Intent


import android.os.Bundle

import android.support.v7.app.AppCompatActivity

import android.view.View


import biz.fastgroup.apps.paloup.R

import biz.fastgroup.apps.paloup.utils.ErrorDialog



abstract class LoginActivity : AppCompatActivity() {


    internal var userName: String? = null
    internal var passWord: String? = null
    internal var loginReturn: Boolean? = false
   // private var apiService: ApiInterface? = null
    internal var loginCounter = 0

  //  private var realm: Realm by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

      /*  realm=Realm.getDefaultInstance()
        apiService = ApiClient.getClient().create(ApiInterface::class.java)
        txt_username.setText("gronel")
        txt_password.setText("rjl2130")*/

    }


    fun onLogin(view: View) {
    /*    userName = txt_username.text.toString()
        passWord = txt_password.text.toString()

      val  pDialog = ProgressDialog(this@LoginActivity)

        pDialog.setMessage("Signing In to ITsek. Please wait...")
        pDialog.setCancelable(false)
        pDialog.show()

        val apiCall = apiService!!.LoginUser(LoginCredentials("gronel", "rjl2130","paloup"))
        apiCall.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<LoginDetails>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        MaterialDialog.Builder(this@LoginActivity)
                                .title("Error Message")
                                .content(e.message!!)
                                .positiveText("OK")
                                .onPositive { dialog, which -> dialog.dismiss() }
                                .show()
                    }

                    override fun onNext(messageResult: LoginDetails) {
                        realm.executeTransaction(Realm.Transaction { realm ->
                            messageResult.id=1
                            realm.copyToRealmOrUpdate(messageResult)
                            loginReturn = true
                            CheckLogin()
                        })
                        if (pDialog.isShowing()) pDialog.dismiss()

                    }
                })*/
    }

   /* private fun openMainActivity() {
        val n = Intent(this, MainActivity::class.java)
        n.putExtra("Login", true)
        this.startActivity(n)
        finish()
    }*/

   /* fun CheckLogin() {
        if (loginReturn!!) {

            openMainActivity()
        } else {
            if (loginCounter >= 4) {
                //  dc.writeData(LOCKED);
                finish()
            }
            val dialog = ErrorDialog()
            val args = Bundle()
            args.putString("title", "Sign In Error.")
            //      args.putString("message", uController.getErrorMessage()+" Please try again.");
            dialog.arguments = args
            dialog.show(supportFragmentManager, "dialog")
            loginCounter++
        }
    }

    override fun onBackPressed() {
        // TODO Auto-generated method stub
        // super.onBackPressed();
        finish()
    }
*/


}


