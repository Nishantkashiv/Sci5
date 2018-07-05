package com.example.android.sci5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build());

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Button signInButton = (Button) findViewById(R.id.signinbutton);
        Button signOutButton = (Button) findViewById(R.id.signoutbutton);
        if (user != null) {
            // User is signed in
            TextView userName = (TextView) findViewById(R.id.username);
            userName.setText("Name: " + user.getDisplayName());
            signInButton.setActivated(false);
        } else {
            // No user is signed in
            // Create and launch sign-in intent
            signOutButton.setActivated(false);
        }

    }

    protected void startSignInActivity(View view){
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build());
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                TextView userName = (TextView) findViewById(R.id.username);
                userName.setText("Name:" + user.getDisplayName());
            } else {
                TextView userName = (TextView) findViewById(R.id.username);

            }
        }
    }
}
