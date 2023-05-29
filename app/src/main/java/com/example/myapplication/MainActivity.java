package com.example.myapplication;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int cant = 0;
    String email = "Cafecafetoso@gmail.com";
    String textotmp = "";
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        // String priceMessage = cant*10 + " Total";
        displayPrice(cant * 10);

    }

    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.numero_cantidad);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = findViewById(R.id.numero_precio);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    public void addCoffee(View view) {
        cant++;
        display(cant);
    }

    public void remCoffee(View view) {
        if (cant != 0) {
            cant--;
            display(cant);
        }
    }

    public void sendOrder(View view) {
        EditText cliente= findViewById(R.id.client);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        if (message != null) {
            String uriText = "mailto:" + Uri.encode(email) +
                    "?subject=" + Uri.encode("La orden de café cafetoso") +
                    "&body=" + Uri.encode(message+"a nombre de "+ cliente.getText().toString() );
            Uri uri = Uri.parse(uriText);
            intent.setData(uri);
            startActivity(Intent.createChooser(intent, "enviar la orden..."));
        }
    }

    public void displayOrder(View view) {
        message=null;
        String texto;
        TextView priceTextView = (TextView) findViewById(R.id.resumen_orden);
        CheckBox crema = findViewById(R.id.cream);
        CheckBox chocolate = findViewById(R.id.chocolate);
        if (cant > 1) {
            texto = cant + " cafés \n";
        } else {
            texto= cant + " café\n";
        }
        message=texto;

        if (crema.isChecked()) {
            textotmp = crema.getText().toString() + "\n";
            message = message+textotmp;
        }

        if (chocolate.isChecked()) {
            textotmp = chocolate.getText().toString() + "\n";
            message = message+textotmp;
        }
        priceTextView.setText(message);
        displayPrice(cant*10);
    }


}

