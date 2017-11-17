package biz.fastgroup.apps.paloup.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import biz.fastgroup.apps.paloup.R;
import biz.fastgroup.apps.paloup.controllers.UserController;
import biz.fastgroup.apps.paloup.databinding.ActivityLoginBinding;
import biz.fastgroup.apps.paloup.models.LoginCredentials;
import biz.fastgroup.apps.paloup.rest.ApiClient;
import biz.fastgroup.apps.paloup.rest.ApiInterface;
import biz.fastgroup.apps.paloup.utils.ErrorDialog;
import biz.fastgroup.apps.paloup.utils.Procedure;
import retrofit2.Call;
import retrofit2.Callback;
import rx.Subscription;

public class LoginActivity extends AppCompatActivity {


    private static final String TAG ="LoginActivity";
    private EditText inputName, inputEmail, inputPassword;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword;
    private Button btnSignUp,btn_login;
    private ScrollView scrlbg;
    private Subscription subscription;

    Procedure procedure;
    String userName;
    String passWord;
    ProgressDialog pDialog;
    Boolean loginReturn = false;
    UserController uController;
    int loginCounter = 0;
    static Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context=this;
        //final ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

    //    binding.setLogin(new LoginCredentials());
     //   binding.getLogin().setLoginname("gronel");
    //    binding.getLogin().setPassword("debug^@2015");

        procedure=new Procedure(this);
       // uController=new UserController(this);

     /*   binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName = binding.getLogin().getLoginname();
                passWord = binding.getLogin().getPassword();

                pDialog = new ProgressDialog(LoginActivity.this);
                pDialog.setMessage("Signing In to PaloUp. Please wait...");
                pDialog.setCancelable(false);
                pDialog.show();


                ApiInterface apiService =
                        ApiClient.getClient().create(ApiInterface.class);

                Call<JsonObject> call = apiService.LoginUser(new LoginCredentials(userName, passWord));
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {

                        try {

                            String responseBody = response.body().toString();
                            JSONObject jo = new JSONObject(responseBody);
                            String jsonProfile=jo.getString("objParam1");

                          //  loginReturn = uController.setLocalRepo(jsonProfile);
                            if (pDialog.isShowing()) pDialog.dismiss();
                            CheckLogin();
                        }
                        catch (Exception e){
                            if (pDialog.isShowing()) pDialog.dismiss();

                            Log.d("LOGIN Error",e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                        if (pDialog.isShowing()) pDialog.dismiss();
                        onShowDialogLoginError();
                    }
                });
            }
        });*/

    }


    public void openMainActivity() {
        Intent n = new Intent(this, MainActivity.class) ;
        n.putExtra("Login", true);
        this.startActivity(n);
        finish();
    }

    public void CheckLogin() {
        if (loginReturn) {

            openMainActivity();
        } else {
            if (loginCounter >= 4) {
              //  dc.writeData(LOCKED);
                finish();
            }
            DialogFragment dialog = new ErrorDialog();
            Bundle args = new Bundle();
            args.putString("title", "Sign In Error.");
      //      args.putString("message", uController.getErrorMessage()+" Please try again.");
            dialog.setArguments(args);
            dialog.show(getSupportFragmentManager(), "dialog");
            loginCounter++;
        }
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        // super.onBackPressed();
        finish();
    }

    public static void onShowDialogLoginError() {


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Login Error");
        builder.setMessage("please check your network connection");
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });
        builder.show();
    }


}
