package com.example.e12_sergio_ezquerro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvCounter;
    EditText etName;
    CheckBox chbWhippedCream;
    CheckBox chChocolate;
    Button btnOrder;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCounter = findViewById(R.id.textView_Counter);
        etName = findViewById(R.id.EditText_Name);
        chbWhippedCream = (CheckBox) findViewById(R.id.checkBox_Toppings_WhippedCream);
        chChocolate = (CheckBox) findViewById(R.id.checkBox_Toppings_Chocolate);
        btnOrder = findViewById(R.id.button_Order);

        if (changeFromIntent()){
            btnOrder.setText(R.string.Correct_your_order);
        }
        tvCounter.setText(String.valueOf(count));
    }

    public Boolean changeFromIntent(){
        boolean result;
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            etName.setText(extras.getString("name"));
            chbWhippedCream.setChecked(extras.getBoolean("whippedCream"));
            chChocolate.setChecked(extras.getBoolean("chocolate"));
            count = extras.getInt("count");
            result = true;
        } else{
            result = false;
        }
        return result;
    }

    //Counter
    public void subtractCount(View view){
        if (count != 0){
            count--;
        }
        tvCounter.setText(String.valueOf(count));
    }
    public void addCount(View view) {
        count++;
        tvCounter.setText(String.valueOf(count));
    }

    //order
    public void order(View view) {
        Intent intent = new Intent(com.example.e12_sergio_ezquerro.MainActivity.this, ShowOrder.class);

        intent.putExtra("name", etName.getText().toString());
        intent.putExtra("whippedCream", chbWhippedCream.isChecked());
        intent.putExtra("chocolate", chChocolate.isChecked());
        intent.putExtra("count", count);

        startActivity(intent);
    }
}