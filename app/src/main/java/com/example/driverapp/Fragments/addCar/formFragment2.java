package com.example.driverapp.Fragments.addCar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.driverapp.Fragments.addCar.formFragment;
import com.example.driverapp.R;
import com.example.driverapp.Utils.InputContol;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.example.driverapp.Utils.InputContol.displayPrivateCodeErrors;

/**
 * A simple {@link Fragment} subclass.

 */
public class formFragment2 extends Fragment {

    private Button button_annuler ;
    private Button button_suivant ;
    private ImageButton button_retour ;
    private formFragment bottomSheetFragment;
    private TextInputEditText edtCodeSecret;
    private TextInputEditText edtCodeSecretConfirm;
    TextView privateCodeErrors;
    private String matchError = "";



    public formFragment2() {
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
        View v = inflater.inflate(R.layout.fragment_form2, container, false);

        edtCodeSecret = v.findViewById(R.id.edtcodeSecret);
        edtCodeSecretConfirm = v.findViewById(R.id.edtcodeSecretConfirm);
        privateCodeErrors = v.findViewById(R.id.private_code_errors);
        annulerButton(v);
        suivantButton(v);
        retourButton(v);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configureErrors();
    }

    private void configureErrors() {
        button_suivant.setEnabled(false);
        edtCodeSecret.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals(edtCodeSecretConfirm.getText().toString())){
                    matchError = "";
                }else {
                    matchError = "Les deux codes privés ne correspondent pas!";
                }
                String errors = displayPrivateCodeErrors(s.toString());
                privateCodeErrors.setText(errors+"\n"+ matchError);

                if(errors.isEmpty() && matchError.isEmpty()){
                    button_suivant.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtCodeSecretConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals(edtCodeSecret.getText().toString())){
                    matchError = "";
                }else {
                    matchError = "Les deux codes privés ne correspondent pas!";
                }
                String errors = displayPrivateCodeErrors(edtCodeSecret.getText().toString());
                privateCodeErrors.setText(errors+"\n"+ matchError);

                if(errors.isEmpty() && matchError.isEmpty()){
                    button_suivant.setEnabled(true);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void annulerButton (View v) {
        button_annuler = v.findViewById(R.id.annuler);

        button_annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetFragment.dismiss();
            }
        });
    }

    public void suivantButton (View v) {

        button_suivant = v.findViewById(R.id.suivant);

        button_suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    formFragment.codeSecret = edtCodeSecret.getText().toString();
                    bottomSheetFragment.setFragment3();
            }
        });
    }

    public void retourButton (View v) {

        button_retour = v.findViewById(R.id.retour);

        button_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetFragment.setFragment1();


            }
        });
    }


}