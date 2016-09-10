package com.rui.ruiengine.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rui.ruiengine.R;

/**
 * Created by rhuang on 1/2/16.
 */
public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    private static final int REQUEST_SIGN_IN = 0;
    private EditText mNameText;
    private EditText mEmailText;
    private EditText mPasswordText;
    private Button mSignUpButton;
    private TextView mSigninLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        mNameText = (EditText)findViewById(R.id.input_name);
        mEmailText = (EditText)findViewById(R.id.input_email);
        mPasswordText = (EditText)findViewById(R.id.input_password);
        mSignUpButton = (Button)findViewById(R.id.btn_signup);
        mSigninLink = (TextView)findViewById(R.id.link_signin);

        mSigninLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // We start sign up activity
                Intent intent = new Intent(getApplicationContext(), SigninActivity.class);
                startActivityForResult(intent, REQUEST_SIGN_IN);
            }
        });
    }

    public void signup() {
        Log.d(TAG, "SignUp");

        if(!validate()) {
            onSignupFailed();
            return;
        }

        mSignUpButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this, R.style.AppTheme_Dark_Dialog);

        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = mNameText.getText().toString();
        String email = mEmailText.getText().toString();
        String password = mPasswordText.getText().toString();

        // TODO: implement sign up logic here

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // On complete call either onSignupSuccessful or onSignupFailed
                // depending on success
                onSignupSuccessful();
                progressDialog.dismiss();

            }
        }, 3000);

    }

    public void onSignupSuccessful() {

        mSignUpButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();

    }

    public void onSignupFailed() {

        Toast.makeText(getBaseContext(), "Sign Up failed", Toast.LENGTH_LONG).show();

        mSignUpButton.setEnabled(true);

    }

    public boolean validate() {

        boolean isValid = true;
        String name = mNameText.getText().toString();
        String email = mEmailText.getText().toString();
        String password = mPasswordText.getText().toString();

        if(name.isEmpty() || name.length() < 3) {
            mNameText.setError("Shall contain at least 3 characters!");
            isValid = false;
        } else {
            mNameText.setError(null);
        }

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailText.setError("Please enter a valid email address!");
            isValid = false;
        } else {
            mEmailText.setError(null);
        }

        if(password.isEmpty() || password.length() < 4 || password.length() > 10) {
            mPasswordText.setError("Password shall contain 4 - 10 alphanumeric characters!");
            isValid = false;
        } else {
            mPasswordText.setError(null);
        }

        return isValid;
    }
}
