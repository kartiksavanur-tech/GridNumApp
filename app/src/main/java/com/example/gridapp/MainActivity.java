package com.example.gridapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.example.gridapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        gridLayout = binding.grid;
        showGridView();

        binding.evenbtn.setOnClickListener(view -> {
            highlightEvenNum();
        });
        binding.oddbtn.setOnClickListener(view -> {
            highlightOddNum();
        });
        binding.prime.setOnClickListener(view -> {
            highlightPrimeNum();
        });
        binding.fibinoccci.setOnClickListener(view -> {
            highlightFibonacciNum();
        });
    }

    public void showGridView(){
        int numbers = 1;
        for (int i=0; i<100; i++){
            Button btn = new Button(this);
            btn.setText(String.valueOf(numbers));
            btn.setTextColor(Color.WHITE);
            btn.setLayoutParams(new GridLayout.LayoutParams());
            btn.setGravity(Gravity.CENTER);
            btn.setBackgroundColor(Color.GRAY);
            gridLayout.addView(btn);
            numbers++;
        }
    }

    public void highlightEvenNum(){
        for (int i =0; i<gridLayout.getChildCount(); i++){
            Button btn = (Button) gridLayout.getChildAt(i);
            btn.setBackgroundColor(Color.GRAY);
            int number = Integer.parseInt(btn.getText().toString());
            if (number % 2 == 0){
                btn.setBackgroundColor(Color.GREEN);
            }
        }
    }
    public void highlightOddNum(){
        for (int i =0; i<gridLayout.getChildCount(); i++){
            Button btn = (Button) gridLayout.getChildAt(i);
            btn.setBackgroundColor(Color.GRAY);
            int number = Integer.parseInt(btn.getText().toString());
            if (number % 2 != 0){
                btn.setBackgroundColor(Color.RED);
            }
        }
    }
    private boolean isFibonacci(int num) {
        if (num < 0) return false;
        int a = 0, b = 1, c = 0;
        while (c < num) {
            c = a + b;
            a = b;
            b = c;
        }
        return c == num;
    }

    private void highlightFibonacciNum() {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button btn = (Button) gridLayout.getChildAt(i);
            btn.setBackgroundColor(Color.GRAY);
            int number = Integer.parseInt(btn.getText().toString());
            if (isFibonacci(number)) {
                btn.setBackgroundColor(Color.BLUE); // Highlight Fibonacci numbers in blue
            }
        }
    }

    private boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private void highlightPrimeNum() {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button btn = (Button) gridLayout.getChildAt(i);
            btn.setBackgroundColor(Color.GRAY);
            int number = Integer.parseInt(btn.getText().toString());
            if (isPrime(number)) {
                btn.setBackgroundColor(Color.MAGENTA); // Highlight prime numbers in red
            }
        }
    }


}