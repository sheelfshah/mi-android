package com.example.myapplication06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
    @Override
    protected void onStart(){
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }
    public void launchNew(View v){
        Intent i=new Intent(this, NewActivity.class);
        i.putExtra("category",((TextView)v).getText().toString());
        startActivity(i);
    }
    public void updateUI(GoogleSignInAccount account){
        Button signinbtn=(Button)findViewById(R.id.loginbutton);
        TextView username=(TextView)findViewById(R.id.usernametext);
        if(account==null){
            signinbtn.setVisibility(View.VISIBLE);
            username.setVisibility(View.GONE);
        }
        else{
            signinbtn.setVisibility(View.GONE);
            username.setVisibility(View.VISIBLE);
            username.setText(account.getEmail());
        }
    }

    public void btnClick(View v){
        Intent i=new Intent(this,ScannedBarcodeActivity.class);
        startActivity(i);
    }
    public void launchLoginActivity(View v){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,6);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 6) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            //handleSignInResult(task);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                // Signed in successfully, show authenticated UI.
                updateUI(account);
            } catch (ApiException e) {
                // The ApiException status code indicates the detailed failure reason.
                // Please refer to the GoogleSignInStatusCodes class reference for more information.
                Log.w("xeex", "signInResult:failed code=" + e.getStatusCode());
                updateUI(null);
            }
        }
    }
}
