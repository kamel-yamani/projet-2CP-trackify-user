package com.example.driverapp.Fragments.addCar;

import android.graphics.Color;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.driverapp.Fragments.addCar.formFragment;
import com.example.driverapp.R;
import com.hbb20.CountryCodePicker;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.

 */
public class formFragment1 extends Fragment {

    View thisFragment;
    private Button button_annuler ;
    private Button button_suivant;
    private ImageButton button_retour;
    private EditText phone;
    CountryCodePicker ccp;
    TextView phoneErrors ;

    private formFragment bottomSheetFragment;

    public formFragment1() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Recuperer l'objet de bottomSheetFragment pour pouvoir utiliser le bouton annuler.
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            bottomSheetFragment = bundle.getParcelable("this"); // Key
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thisFragment = inflater.inflate(R.layout.fragment_form1, container, false);
        assignViews(thisFragment);
        button_suivant.setEnabled(false);
        ccp.registerCarrierNumberEditText(phone);
        ccp.setCountryForNameCode("DZ");
        suivantButton(thisFragment);
        annulerButton(thisFragment);
        retourButton(thisFragment);
        getPhoneNumberEdit();
        return thisFragment;
    }


    public void annulerButton (View v){
        button_annuler = v.findViewById(R.id.annuler);

        button_annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetFragment.dismiss();

            }
        });
    }


    public void suivantButton (View v){

        button_suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formFragment.phoneNumber = ccp.getFullNumberWithPlus();
                bottomSheetFragment.setFragment2();
            }
        });


    }
    public void retourButton (View v) {

        button_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetFragment.setFragment0();
            }
        });
    }

    public void getPhoneNumberEdit(){
        try {
            phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    button_suivant.setEnabled(false);
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(ccp.isValidFullNumber()){
                        button_suivant.setEnabled(true);
                        phoneErrors.setTextColor(getResources().getColor(R.color.green));
                        phoneErrors.setText("This phone number is valid in "+ ccp.getSelectedCountryName());
                    }else {
                        if(phone.getText().toString().length()>0){
                            button_suivant.setEnabled(false);
                            phoneErrors.setTextColor(Color.RED);
                            phoneErrors.setText("This phone number is not valid in "+ ccp.getSelectedCountryName());
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }catch (Exception e){

        }
    }
    private void assignViews(View v){
        button_retour = (ImageButton) v.findViewById(R.id.retour);
        button_suivant = v.findViewById(R.id.suivant);
        phone=(EditText) v.findViewById(R.id.phone);
        ccp=(CountryCodePicker) v.findViewById(R.id.ccp);
        phoneErrors = (TextView) v.findViewById(R.id.phoneErrors);
    }


}