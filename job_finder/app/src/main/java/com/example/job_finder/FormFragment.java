package com.example.job_finder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.BundleCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FormFragment extends Fragment {

    private EditText nom, prenom,titreEmploie, competences, anneesXp;


    FormFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.form_fragment_layout, container, false);

        nom = (EditText) v.findViewById(R.id.etNom);
        prenom = (EditText) v.findViewById(R.id.etPrenom);
        titreEmploie = (EditText) v.findViewById(R.id.etTitre_emploie);
        competences = (EditText) v.findViewById(R.id.etCompetences);
        anneesXp = (EditText) v.findViewById(R.id.etAnnees_xp);

        Button btn = (Button) v.findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });

        return v;
    }


    public void saveProfile(){
        Bundle bundle = new Bundle();

        bundle.putString("nom",nom.getText().toString());
        bundle.putString("prenom",prenom.getText().toString());
        bundle.putString("titreEmploie",titreEmploie.getText().toString());
        bundle.putString("competences",competences.getText().toString());
        bundle.putString("anneesXp",anneesXp.getText().toString());

        ProfileFragment profileFragment = new ProfileFragment();
        profileFragment.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, profileFragment);
        transaction.commit();
    }
}
