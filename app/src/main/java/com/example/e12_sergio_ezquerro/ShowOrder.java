package com.example.e12_sergio_ezquerro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ShowOrder extends AppCompatActivity {

    String name;
    Boolean whippedCream;
    Boolean chocolate;
    Integer count;
    TextView tvOrder;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_order);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            name = extras.getString("name");
            whippedCream = extras.getBoolean("whippedCream");
            chocolate = extras.getBoolean("chocolate");
            count = extras.getInt("count");
        }

        tvOrder = findViewById(R.id.textView_orderSummary);

        //Change tvOrder maxwidth
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        tvOrder.setMaxWidth(width - 150);

        changeTextView();
    }

    private void changeTextView() {
        if (Locale.getDefault().getLanguage().equals("es")) {
            text = (name.isEmpty() ? "H" : name + ", h") + "as ordernado " + count + " cafés" + (whippedCream || chocolate ? " con " : "") +
                    (whippedCream ? "nata montada" : "") + (whippedCream && chocolate ? " y " : "")
                    + (chocolate ? "chocolate" : "") + ".\nEl precio total es: " + (2 * count) + "'00€";
        } else {
            text = (name.isEmpty() ? "Y" : name + ", y") + "ou have ordered " + count + " coffees" + (whippedCream || chocolate ? " with " : "") +
                    (whippedCream ? "whippedCream" : "") + (whippedCream && chocolate ? " and " : "")
                    + (chocolate ? "chocolate" : "") + ".\nThe total price is: " + (2 * count) + "'00€";
        }
        tvOrder.setText(text);
    }

    public void goBack(View view) {
        Intent intent = new Intent(com.example.e12_sergio_ezquerro.ShowOrder.this, MainActivity.class);

        intent.putExtra("name", name);
        intent.putExtra("whippedCream", whippedCream);
        intent.putExtra("chocolate", chocolate);
        intent.putExtra("count", count);

        startActivity(intent);
    }

    public void sendMail(View view){
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","", null));

        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"dam04.2020.jesuitas@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.email_subject));
        intent.putExtra(Intent.EXTRA_TEXT, text);

        startActivity(Intent.createChooser(intent, "Send Email"));
    }
}