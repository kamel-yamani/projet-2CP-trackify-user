package com.example.driverapp.Fragments.addCar;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.driverapp.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class formFragment extends BottomSheetDialogFragment implements Parcelable {

    private Fragment fragment;

    public static String phoneNumber, codeSecret, marque, model, matricul;

    public formFragment() {
        // Required empty public constructor
    }

    protected formFragment(Parcel in) {
    }

    public static final Creator<formFragment> CREATOR = new Creator<formFragment>() {
        @Override
        public formFragment createFromParcel(Parcel in) {
            return new formFragment(in);
        }

        @Override
        public formFragment[] newArray(int size) {
            return new formFragment[size];
        }
    };
    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public Dialog getDialog() {
        return new BottomSheetDialog(requireContext(),getTheme());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);
       setFragment0();

        return v;
    }



    public void setFragment0 (){
        fragment = new formFragment0();
        getChildFragmentManager().beginTransaction()
                .replace(R.id.formContainer,fragment).commit();

        // Pour envoyer l'objet this au fragment.
        Bundle bundle = new Bundle();
        bundle.putParcelable("this", this) ; // Key, value
        fragment.setArguments(bundle);
    }

    public void setFragment1 (){
        fragment = new formFragment1();
        getChildFragmentManager().beginTransaction().setCustomAnimations(
                R.anim.slide_in,  // enter
                R.anim.fade_out,  // exit
                R.anim.fade_in,   // popEnter
                R.anim.slide_out  // popExit
        ).replace(R.id.formContainer,fragment).commit();

        // Pour envoyer l'objet this au fragment.
        Bundle bundle = new Bundle();
        bundle.putParcelable("this", this) ; // Key, value
        fragment.setArguments(bundle);
    }

    public void setFragment2 (){
        fragment = new formFragment2();
        getChildFragmentManager().beginTransaction().setCustomAnimations(
                R.anim.slide_in,  // enter
                R.anim.fade_out,  // exit
                R.anim.fade_in,   // popEnter
                R.anim.slide_out  // popExit
        )
                .replace(R.id.formContainer,fragment).commit();

        Bundle bundle = new Bundle();
        bundle.putParcelable("this", this) ;;// Key, value
        fragment.setArguments(bundle);
    }

    public void setFragment3 (){
        fragment = new formFragment3();
        getChildFragmentManager().beginTransaction().setCustomAnimations(
                R.anim.slide_in,  // enter
                R.anim.fade_out,  // exit
                R.anim.fade_in,   // popEnter
                R.anim.slide_out  // popExit
        )
                .replace(R.id.formContainer,fragment).commit();

        Bundle bundle = new Bundle();
        bundle.putParcelable("this", this) ; // Key, value
        fragment.setArguments(bundle);
    }

    public void setFragment4 (){
        fragment = new formFragment4();
        getChildFragmentManager().beginTransaction().setCustomAnimations(
                R.anim.slide_in,  // enter
                R.anim.fade_out,  // exit
                R.anim.fade_in,   // popEnter
                R.anim.slide_out  // popExit
        )
                .replace(R.id.formContainer,fragment).commit();

        Bundle bundle = new Bundle();
        bundle.putParcelable("this", this) ;// Key, value
        fragment.setArguments(bundle);
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        BottomSheetDialog bottomSheetDialog=(BottomSheetDialog)super.onCreateDialog(savedInstanceState);
        bottomSheetDialog.setOnShowListener(dialog -> {
            BottomSheetDialog dialogc = (BottomSheetDialog) dialog;
            // When using AndroidX the resource can be found at com.google.android.material.R.id.design_bottom_sheet
            FrameLayout bottomSheet =  dialogc.findViewById(com.google.android.material.R.id.design_bottom_sheet);

            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
            bottomSheetBehavior.setPeekHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        });
        return bottomSheetDialog;


    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
