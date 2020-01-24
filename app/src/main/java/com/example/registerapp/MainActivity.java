package com.example.registerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private Spinner spinner;
    private Button btnSubmit;
    private RatingBar ratingBar;
    Button btnDatePicker;
    TextView txtDate;
    private int mYear, mMonth, mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ConstraintLayout bgElement = (ConstraintLayout) findViewById(R.id.container);
        bgElement.setBackgroundColor(Color.GRAY);
        addItemsOnSpinner2( );
        addListenerOnButton( );
        addListenerOnRatingBar();
        addListenerOnSpinnerItemSelection( );
        btnDatePicker.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance( );
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener( ) {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show( );
        }
    }



        // add items into spinner dynamically
    public void addItemsOnSpinner2() {

        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>( );
        list.add("Male");
        list.add("Female");
        list.add("Others");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener( ));
    }

    public void addListenerOnRatingBar() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

               // txtRatingValue.setText(String.valueOf(rating));

            }
        });
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner = (Spinner) findViewById(R.id.spinner);

        btnSubmit = (Button) findViewById(R.id.submitBtn);

        final RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        btnDatePicker=(Button)findViewById(R.id.btn_date);

        txtDate=(TextView)findViewById(R.id.in_date);




        btnSubmit.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {


                // Get the checked Radio Button ID from Radio Grou[
                int selectedRadioButtonID = rg.getCheckedRadioButtonId();

                // If nothing is selected from Radio Group, then it return -1
                if (selectedRadioButtonID != -1) {

                    RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioButtonID);

                    ratingBar = (RatingBar) findViewById(R.id.ratingBar);
                    String selectedRadioButtonText = selectedRadioButton.getText().toString();



                    Toast.makeText(MainActivity.this,
                            "OnClickListener : " +
                                    "\nSpinner 1: "+ String.valueOf(spinner.getSelectedItem())+
                                    "\nRadio button : "+ selectedRadioButtonText +
                            "\nRating  : "+ String.valueOf(ratingBar.getRating()),
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Nothing selected from Radio Group.",  Toast.LENGTH_SHORT).show();
                }
            }

        });


    }
}
