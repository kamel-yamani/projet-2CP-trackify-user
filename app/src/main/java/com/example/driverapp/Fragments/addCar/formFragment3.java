package com.example.driverapp.Fragments.addCar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.driverapp.Fragments.addCar.formFragment;
import com.example.driverapp.R;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 */

public class formFragment3 extends Fragment {

    private Button button_annuler ;
    private Button button_suivant;
    private ImageButton button_retour ;
    private formFragment bottomSheetFragment;

    TextInputEditText edtMarque;
    TextInputEditText edtModel;
    TextInputEditText edtMatricul;

    public formFragment3() {
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
        View v = inflater.inflate(R.layout.fragment_form3, container, false);
        button_annuler = v.findViewById(R.id.annuler);
        button_suivant = v.findViewById(R.id.suivant);
        button_retour = v.findViewById(R.id.retour);

        edtMarque = v.findViewById(R.id.edtMarque);
        edtModel = v.findViewById(R.id.edtModel);
        edtMatricul = v.findViewById(R.id.edtMatricule);
        button_suivant.setEnabled(false);
        annulerButton(v);
        suivantButton(v);
        retourButton(v);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configureSuivant();
    }

    void configureSuivant(){
        edtMatricul.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(             !edtMarque.getText().toString().isEmpty()
                                && !edtModel.getText().toString().isEmpty()
                                && !s.toString().isEmpty()
                )button_suivant.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtMarque.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(             !s.toString().isEmpty()
                                && !edtModel.getText().toString().isEmpty()
                                && !edtMatricul.getText().toString().isEmpty()
                )button_suivant.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtModel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(             !edtMarque.getText().toString().isEmpty()
                                && !s.toString().isEmpty()
                                && !edtMatricul.getText().toString().isEmpty()
                )button_suivant.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void annulerButton (View v) {
        button_annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetFragment.dismiss();

            }
        });
    }

    public void suivantButton (View v) {

        button_suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formFragment.marque = edtMarque.getText().toString();
                formFragment.model = edtModel.getText().toString();
                formFragment.matricul = edtMatricul.getText().toString();
                bottomSheetFragment.setFragment4();
            }
        });
    }

    public void retourButton (View v) {
        button_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetFragment.setFragment2();
            }
        });
    }
}