package itp341.verduzco.salvador.usclassifieds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class Loginpage2 extends AppCompatActivity {
    Button main_page;
    Button sign_out;
    TextView nameTV;
    TextView emailTV;
    TextView idTV;
    EditText locationTV;
    EditText numberTV;
    String personName;
    String personEmail;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page2);

        main_page = findViewById(R.id.main_page);
        sign_out = findViewById(R.id.log_out);
        nameTV = findViewById(R.id.name);
        emailTV = findViewById(R.id.email);
        idTV =findViewById(R.id.id);
        locationTV = findViewById(R.id.edit_location);
        numberTV = findViewById(R.id.edit_number);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient= GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(Loginpage2.this);
        if(acct!= null){
            personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            personEmail = acct.getEmail();
            String personId = acct.getId();

            nameTV.setText("Name: " + personName);
            emailTV.setText("Email: " + personEmail);
            idTV.setText("ID: "+ personId);

        }
        main_page.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String personName = acct.getDisplayName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                String personLocation = locationTV.getText().toString();
                String personNumber = numberTV.getText().toString();

                //send to backend
                User currUser = new User(personName, personEmail, personLocation, personNumber);
                FirestoreHelper firestore = new FirestoreHelper();
                firestore.doLogin(personId, currUser);
                UserSingleton.getInstance(getApplicationContext()).setID(personId);

                Intent intent = new Intent(Loginpage2.this, MainListActivity.class);
                startActivity(intent);
            }
        });
        sign_out.setOnClickListener((view)->{signOut();});
    }

    private void signOut(){
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this,(task)-> {
                    Toast.makeText(Loginpage2.this, "Successfully Signed Out", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Loginpage2.this, LoginPage.class));
                    finish();
                });
    }

}
