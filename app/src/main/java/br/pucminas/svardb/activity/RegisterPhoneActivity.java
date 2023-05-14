package br.pucminas.svardb.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import br.pucminas.svardb.R;

import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterPhoneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_phone);

        EditText editTextPhoneNumber = findViewById(R.id.edt_register_phone);
        editTextPhoneNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        findViewById(R.id.btn_register_phone).setOnClickListener(view -> {
            editTextPhoneNumber.setText("");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Numero cadastrado com sucesso");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
        });
    }
}