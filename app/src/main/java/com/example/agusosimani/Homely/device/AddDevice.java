package com.example.agusosimani.Homely.device;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.agusosimani.Homely.R;

public class AddDevice extends Activity {
    private static final String TAG = "AddDevice";
    private static Spinner type;
    private static ArrayAdapter typeAdapter;
    private static EditText nameInput;
    private static ImageButton save, back;
    private String name;
    private String typeSelection;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_device);

        setUpType();
        save = findViewById(R.id.save);
        back = findViewById(R.id.back_arrow);
        nameInput = findViewById(R.id.name);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View view){
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View view){
                name = nameInput.getText().toString();
                if (name.length() ==0){
                    nameInput.setError(getResources().getString(R.string.device_name_error));
                }else{
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.added_successfully), Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }


    private void setUpType(){
        type= findViewById(R.id.type);

        typeAdapter = ArrayAdapter.createFromResource(this, R.array.types, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        type.setAdapter(typeAdapter);

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                typeSelection = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
