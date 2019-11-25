package itp341.verduzco.salvador.usclassifieds;

import android.content.Intent;
import android.content.pm.SigningInfo;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {
    int RC_SIGN_IN = 0;
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        TextView textview = (TextView)findViewById(R.id.textView2);

        //Initialize
        signInButton = findViewById(R.id.sign_in_button);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener((view) -> { signIn();});

        Button button = findViewById(R.id.LoggingIn);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                UserSingleton.getInstance(getApplicationContext()).setID("TESTUSER");
                Intent intent = new Intent(LoginPage.this, MainListActivity.class);
                startActivity(intent);
            }
        });
    }
    private void signIn(){
        Intent signInIntent= mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try{
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            //Signed in Successful
            startActivity(new Intent(LoginPage.this, Loginpage2.class));
        } catch(ApiException e){
            Log.w("Google Sign In Error", "signInResult:failed cod = " + e.getStatusCode());
            Toast.makeText(LoginPage.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart(){
        GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(this);
        if(account!= null){
            startActivity(new Intent(LoginPage.this, Loginpage2.class));
        }
        super.onStart();
    }
}
