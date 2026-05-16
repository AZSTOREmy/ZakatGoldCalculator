package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editWeight, editValue;
    RadioButton rbKeep, rbWear;
    Button btnCalculate;
    Button btnAbout;
    TextView txtTotalValue, txtZakatPayable, txtTotalZakat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link XML components

        editWeight = findViewById(R.id.editWeight);
        editValue = findViewById(R.id.editValue);

        rbKeep = findViewById(R.id.rbKeep);
        rbWear = findViewById(R.id.rbWear);

        btnCalculate = findViewById(R.id.btnCalculate);

        btnAbout=findViewById(R.id.btnAbout);

        txtTotalValue = findViewById(R.id.txtTotalValue);
        txtZakatPayable = findViewById(R.id.txtZakatPayable);
        txtTotalZakat = findViewById(R.id.txtTotalZakat);

        // Button click

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check empty input

                if (editWeight.getText().toString().isEmpty()) {
                    editWeight.setError("Enter gold weight");
                    return;
                }

                if (editValue.getText().toString().isEmpty()) {
                    editValue.setError("Enter gold value");
                    return;
                }

                // Get input values

                double weight = Double.parseDouble(editWeight.getText().toString());
                double value = Double.parseDouble(editValue.getText().toString());

                double threshold;
                double excessGold;
                double totalGoldValue;
                double zakatPayable;
                double totalZakat;

                // Determine gold type

                if (rbKeep.isChecked()) {

                    threshold = 85;

                } else if (rbWear.isChecked()) {

                    threshold = 200;

                } else {

                    Toast.makeText(MainActivity.this,
                            "Please select gold type",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                // Total gold value

                totalGoldValue = weight * value;

                // Excess gold

                excessGold = weight - threshold;

                // If less than zero

                if (excessGold < 0) {
                    excessGold = 0;
                }

                // Zakat payable

                zakatPayable = excessGold * value;

                // Total zakat

                totalZakat = zakatPayable * 0.025;

                // Display result

                txtTotalValue.setText(
                        "Total Gold Value: RM " + totalGoldValue);

                txtZakatPayable.setText(
                        "Zakat Payable: RM " + zakatPayable);

                txtTotalZakat.setText(
                        "Total Zakat: RM " + totalZakat);
            }
        });

        btnAbout.setOnClickListener(view -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    AboutActivity.class);

            startActivity(intent);

        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_share) {

            Intent shareIntent = new Intent(Intent.ACTION_SEND);

            shareIntent.setType("text/plain");

            shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Download my app here: https://github.com/AZSTOREmy/ZakatGoldCalculator"
            );

            startActivity(Intent.createChooser(
                    shareIntent,
                    "Share via"
            ));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
