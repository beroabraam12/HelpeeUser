package com.helpee.helpee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    LoginButton loginButton;
    private static final String EMAIL = "email";
    private static final String PUBLIC = "public_profile";
    private static final String GENDER = "user_gender";
    private static final String BIRTHDAY = "user_birthday";
    private static final String AGE = "user_age_range";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();



        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setPermissions(Arrays.asList(EMAIL,PUBLIC));
//        loginButton.setPermissions(Arrays.asList(EMAIL,PUBLIC,GENDER,BIRTHDAY,AGE));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        String userid = loginResult.getAccessToken().getToken();
                        GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                displayUserInfo(object);
                            }
                        });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields","first_name, last_name, email, id");
//                        parameters.putString("fields","first_name, last_name, email, id,gender,birthday,age_range");
                        graphRequest.setParameters(parameters);
                        graphRequest.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Toast.makeText(LoginActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void  displayUserInfo(JSONObject object){
        String first_name , last_name , email , id , gender,birthday,age_range;
        first_name ="";
        last_name ="";
        email ="";
        id ="";
        gender ="";
        birthday="";
        age_range="";
        try {
            first_name = object.getString("first_name");
            last_name = object.getString("last_name");

            id = object.getString("id");
//            email = object.getString("email");
//            birthday = object.getString("birthday");
//            gender = object.getString("gender");
//            //Age As JSON max , min
//            age_range = object.getString("age_range");
//            Toast.makeText(LoginActivity.this, age_range, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LoginActivity.this,FaceBookStepActivity.class);
            startActivity(intent);

        } catch (JSONException e) {
            Toast.makeText(LoginActivity.this , e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
