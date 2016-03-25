package com.swe.zonein.zonein.Activities;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.swe.zonein.zonein.Controllers.DBController;
import com.swe.zonein.zonein.R;

public class RestorePasswordActivity extends Activity {

    DBController db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore_password);
        db = new DBController(this);
        final EditText restoreMailET = (EditText) findViewById(R.id.restoreMailET);
        final Button restoreBtn = (Button)findViewById(R.id.restoreBtn);
        final TextView restorePassTV = (TextView) findViewById(R.id.restorePassTV);
        restoreBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String pass;
                pass = db.getPassByMail(restoreMailET.getText().toString());
                if(pass == ""){
                    restorePassTV.setText("This Mail does not exists");
                }else{
                    restorePassTV.setText("Your pass is : "+pass);
                }
            }
        });
    }

}
