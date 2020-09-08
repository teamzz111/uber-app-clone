package com.unilibre.familiaapp.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.unilibre.familiaapp.R;

public class GalleryFragment extends Fragment {
    private View root;
    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final Button check = root.findViewById(R.id.button);
        final CheckBox chebkbox1 = root.findViewById(R.id.check1);
        final CheckBox chebkbox2 = root.findViewById(R.id.check2);
        final Spinner spinner = root.findViewById(R.id.planets_spinner);
        final SeekBar seek = root.findViewById(R.id.seekBar);


        check.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addListenerOnButton();
                Snackbar.make(root, "Uno: " + chebkbox1.isChecked() + " Dos: "+ chebkbox2.isChecked() + " S: "+ spinner.getSelectedItem().toString()+ "SEEK: " + seek.getProgress(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        return root;
    }
    public void addListenerOnButton() {
        RadioGroup radioGroup = root.findViewById(R.id.radiogroup);
        // get selected radio button from radioGroup
        int selectedId = radioGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        RadioButton radioButton = root.findViewById(selectedId);

        Toast.makeText(getActivity(), radioButton.getText(), Toast.LENGTH_SHORT).show();

    }
}