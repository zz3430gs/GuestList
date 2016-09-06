package com.example.zz3430gs.guestlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GuestList extends AppCompatActivity {

    EditText mEnterGuestET;
    Button mSaveGuestButton;
    TextView mGuestListTV;

    ArrayList<String> mGuests;

    private final static String GUEST_LIST_KEY = "guest list bundle key";

    @Override
    protected void onSaveInstanceState(Bundle outBundle) {
        outBundle.putStringArrayList(GUEST_LIST_KEY, mGuests);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_list);

        if (savedInstanceState != null){
            mGuests = savedInstanceState.getStringArrayList(GUEST_LIST_KEY);
        }
        if (mGuests == null) {
            mGuests = new ArrayList<String>();
        }

        mEnterGuestET = (EditText) findViewById(R.id.guest_name_edit_text);
        mSaveGuestButton = (Button) findViewById(R.id.save_button);
        mGuestListTV = (TextView) findViewById(R.id.guest_list_text_view);
        updateGuestList();

        mSaveGuestButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String newGuestName = mEnterGuestET.getText().toString();
                if (newGuestName.length()>0){
                    mGuests.add(newGuestName);
                    mEnterGuestET.getText().clear();
                    GuestList.this.updateGuestList();
                }
                else{
                    Toast.makeText(GuestList.this, "Please enter a guest name", Toast.LENGTH_LONG).show();
                        }
            }
        });
    }
    private void updateGuestList(){
        String displayString = "";
        for (String name : mGuests){
            displayString = displayString + name + "\n";
        }
        mGuestListTV.setText(displayString);
    }
}
