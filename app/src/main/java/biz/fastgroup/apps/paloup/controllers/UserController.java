package biz.fastgroup.apps.paloup.controllers;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import biz.fastgroup.apps.paloup.R;
import biz.fastgroup.apps.paloup.models.LoginCredentials;
import biz.fastgroup.apps.paloup.models.UserModel;
import biz.fastgroup.apps.paloup.rest.ApiClient;
import biz.fastgroup.apps.paloup.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by gronel on 05/02/2017.
 */

public class UserController {

   /* Boolean loginSuccess = false;
    String errorMessage = "";
    Context context;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public UserController(Context c) {

        this.context = c;

    }

    public Boolean setLocalRepo(String jsonProfile) {

        //get realm instance

        try {

            JSONObject profile = new JSONObject(jsonProfile);

           // Realm realm = Realm.getInstance(context);
         //   realm.beginTransaction();

            UserModel n = realm.createObject(UserModel.class); // Create a new object
            n.setUserId(profile.getInt("userId"));
            n.setUsername(profile.getString("username"));
            n.setUserrole(profile.getString("userrole"));
            n.setLastname(profile.getString("lastname"));
            n.setFirstname(profile.getString("firstname"));
            n.setMiddlename(profile.getString("middlename"));
            n.setUsertype(profile.getString("usertype"));
            n.setEmplId(profile.getString("emplId"));
            n.setEmailaddress(profile.getString("emailaddress"));
            n.setMobileno(profile.getString("mobileno"));
            n.setHashcode(profile.getString("hashcode"));

            realm.commitTransaction();
            loginSuccess = true;

        } catch (JSONException e) {
            e.printStackTrace();

            errorMessage = e.getMessage();
            return false;
        }
        return loginSuccess;
    }*/
}
