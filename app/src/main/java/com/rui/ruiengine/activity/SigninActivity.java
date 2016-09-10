package com.rui.ruiengine.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.common.base.Strings;

import com.rui.ruiengine.R;
import com.rui.ruiengine.util.AppConstants;

import java.io.IOException;

/**
 * Created by rhuang on 1/2/16.
 */
public class SigninActivity extends AppCompatActivity {

    private static final String TAG = "SigninActivity";
    private static final int REQUEST_SIGN_UP = 0;

    private Button mSigninButton;
    private TextView mSignupLinkTextView;

    private Button mGetGoogleAccountButton;
    private TextView mGoogleAccountTextView;

    private AuthorizationCheckTask mAuthTask;
    private String mAccount = "";

    private String accessToken = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mSigninButton = (Button)findViewById(R.id.btn_login);
        mGetGoogleAccountButton = (Button)findViewById(R.id.btn_get_google_account);
        mSignupLinkTextView = (TextView)findViewById(R.id.link_sign_up);
        mGoogleAccountTextView = (TextView)findViewById(R.id.tv_google_account);
        mGoogleAccountTextView.requestFocus();

        mSigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
            }
        });
        // I don't allow user to use Signin button by default, only there is a Google account registered,
        // user can signin
        mSigninButton.setEnabled(false);
        mGetGoogleAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGoogleAccount();
            }
        });

        mSignupLinkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // We start sign up activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGN_UP);
            }
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if(mAuthTask != null)
        {
            mAuthTask.cancel(true);
            mAuthTask = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_SIGN_UP) {
            if(resultCode == RESULT_OK) {

                // TODO: implement successful SignUp logic here
                // By default, we just finish the Activity and log user in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }

    public void onLoginSuccessful() {
        mSigninButton.setEnabled(false);

        // We start connect activity
        Intent intent = new Intent(getApplicationContext(), ConnectNetElementHubActivity.class);
        startActivity(intent);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed...", Toast.LENGTH_LONG).show();
        mSigninButton.setEnabled(true);
    }

    public void getGoogleAccount() {
        int accountsNum = AppConstants.countGoogleAccounts(this);
        if(accountsNum == 0)
        {
            // No Google accounts registered in the device

            mGoogleAccountTextView.setError(getString(R.string.no_google_accounts));

            // Show 2 seconds and then dismiss
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    mGoogleAccountTextView.setError(null);
                }
            }, 2000);


        }
        else if(accountsNum == 1)
        {
            // Allow user to use Signin button
            mSigninButton.setEnabled(true);
            Toast.makeText(this, R.string.only_google_account, Toast.LENGTH_LONG).show();
            AccountManager am = AccountManager.get(this);
            Account[] accounts = am.getAccountsByType(GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
            if(accounts != null && accounts.length > 0)
            {
                // Select account and perform authorization check.
                mGoogleAccountTextView.setText(accounts[0].name);
                mAccount = accounts[0].name;
            }
        }
        else
        {
            // More than one Google accounts registered,
            // handle this in future.
        }

    }

    public void startAuthCheck(String account)
    {
        // Cancel previously running tasks.
        if (mAuthTask != null)
        {
            try
            {
                mAuthTask.cancel(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(SigninActivity.this, R.style.AppTheme_Dark_Dialog);

        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        mAuthTask = new AuthorizationCheckTask();
        mAuthTask.execute(account);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // On complete call either onLoginSuccessful or onLoginFailed
                if (accessToken != null) {
                    onLoginSuccessful();

                } else {
                    onLoginFailed();
                }

                progressDialog.dismiss();
            }
        }, 3000);
    }

    private class AuthorizationCheckTask extends AsyncTask<String, Integer, Boolean>
    {

        /*
        public String getAccessToken()
        {
            return accessToken;
        }
        */

        @Override
        protected Boolean doInBackground(String... accounts)
        {

            if(!AppConstants.checkGooglePlayServiceAvailable(SigninActivity.this))
            {
                return false;
            }

            String account = accounts[0];

            // Ensure one task running
            mAuthTask = this;
            // Ensure one account has been selected.
            if(Strings.isNullOrEmpty(account))
            {
                return false;
            }

            try
            {
                GoogleAccountCredential credential = GoogleAccountCredential.usingAudience(
                        SigninActivity.this, AppConstants.AUDIENCE);
                credential.setSelectedAccountName(account);

                accessToken = credential.getToken();
                Log.d(TAG, accessToken);
                return true;
            }

            catch(GoogleAuthException unrecoverableException)
            {
                Log.e(TAG, "Exception checking OAuth2 authentication.", unrecoverableException);
                return false;
            }
            catch(IOException ioException)
            {
                Log.e(TAG, "Exception checking OAuth2 authentication.", ioException);
                return false;
            }
        }


        @Override
        protected void onProgressUpdate(Integer... stringIds)
        {
            Integer stringId = stringIds[0];
        }

        @Override
        protected void onPreExecute()
        {
            mAuthTask = this;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean)
        {
            TextView googleAccountTextView = (TextView) SigninActivity.this.findViewById(R.id.tv_google_account);
            if(aBoolean) {
                // Authorization check successful, set internal variable.
                mAccount = googleAccountTextView.getText().toString();
            } else {
                // Authorization check unsuccessful, reset TextView to empty.
                googleAccountTextView.setText("");
            }

            mAuthTask = null;
        }

        @Override
        protected void onCancelled()
        {
            mAuthTask = null;
        }
    }


    public void signin() {

        mSigninButton.setEnabled(false);

        startAuthCheck(mAccount);

    }
}
