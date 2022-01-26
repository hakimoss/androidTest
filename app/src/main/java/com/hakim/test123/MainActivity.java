package com.hakim.test123;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtNumber = null;
    private Button btnCompare;
    private TextView lblResult;
    private ProgressBar pgbScore;
    private TextView lblOutput;
    
    private int searchValue;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNumber = (EditText) findViewById( R.id.txtNumber);
        btnCompare = (Button) findViewById(R.id.btnCompare);
        lblResult = (TextView) findViewById(R.id.lblResult);
        pgbScore = (ProgressBar) findViewById(R.id.pgbScore);
        lblOutput = (TextView) findViewById(R.id.lblOutput);

        btnCompare.setOnClickListener(btnCompareListener);

        init();
    }

    private void init() {
        score = 0;
        searchValue = 1 + (int) (Math.random() * 100);
        Log.i("DEBUG", "valeur rechercheré : " + searchValue);

        txtNumber.setText("");
        pgbScore.setProgress(score);
        lblResult.setText("");
        lblOutput.setText("");

        txtNumber.requestFocus();
    }

    private void congratulations() {
        lblResult.setText("Félicitation");

        AlertDialog retryAlert = new AlertDialog.Builder(this).create();
        retryAlert.setTitle(R.string.app_name);
        retryAlert.setMessage( getString(R.string.strMessage, score) );

        retryAlert.setButton(AlertDialog.BUTTON_POSITIVE, "Oui", new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                init();
            }
        });

        retryAlert.setButton(AlertDialog.BUTTON_NEGATIVE, "Non", new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        retryAlert.show();
    }

    private View.OnClickListener btnCompareListener = new View.OnClickListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View v) {
            Log.i("DEBUG", String.valueOf(txtNumber)+ "\r\n");
            lblResult.setText("Veuillez indiquer un nombre");
            String strNumber = txtNumber.getText().toString();
            if(strNumber.equals("")) return;

            lblOutput.append(strNumber + "\r\n");
            pgbScore.incrementProgressBy(1);
            score++;

            int enteredValue = Integer.parseInt(strNumber);
            if(enteredValue == searchValue) {
                congratulations();
            } else if(enteredValue < searchValue) {
                lblResult.setText("Veuillez saisire une valeur plus grande");
            } else {
                lblResult.setText("Veuillez saisire une valeur plus petite");
            }
            txtNumber.setText("");
        }
    };
}