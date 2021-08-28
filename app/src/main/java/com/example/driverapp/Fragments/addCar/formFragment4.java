package com.example.driverapp.Fragments.addCar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.driverapp.Fragments.addCar.formFragment;
import com.example.driverapp.MainActivity;
import com.example.driverapp.Models.Car;
import com.example.driverapp.Models.CarViewModel;
import com.example.driverapp.R;

/**
 * A simple {@link Fragment} subclass.

 */
public class formFragment4 extends Fragment {

    private Button button_annuler ;
    private Button button_confirmer;
    private ImageButton button_retour ;
    private formFragment bottomSheetFragment;

    private TextView txtMarque;
    private TextView txtCode;
    private TextView txtMatricul;
    private TextView txtNum;
    private TextView carName;



    public formFragment4() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
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
        View v = inflater.inflate(R.layout.fragment_form4, container, false);

        txtCode = v.findViewById(R.id.value_code_secret);
        txtMatricul = v.findViewById(R.id.value_matricule);
        txtNum = v.findViewById(R.id.value_num_tele);
        carName = v.findViewById(R.id.car_name);


        txtCode.setText(formFragment.codeSecret);
        txtMatricul.setText(formFragment.matricul);
        txtNum.setText(formFragment.phoneNumber);
        carName.setText(formFragment.marque+" "+formFragment.model);

        annulerButton(v);
        confirmerButton(v);
        retourButton(v);
        return v;
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

    public void confirmerButton (View v) {
        button_confirmer = v.findViewById(R.id.confirmer);

        button_confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.myCars.insert(new Car(formFragment.marque, formFragment.model, formFragment.matricul,
                        formFragment.codeSecret, formFragment.phoneNumber, false));
                bottomSheetFragment.dismiss();
                startActivity(new Intent(getContext(),MainActivity.class));
            }
        });
    }

    public void retourButton (View v) {

        button_retour = v.findViewById(R.id.retour);

        button_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetFragment.setFragment3();
            }
        });
    }

}