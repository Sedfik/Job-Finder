package com.example.job_finder;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private TextView tvNom, tvPrenom, tvCompenteces, tvTitreEmploie, tvAnneesXp;

    ProfileFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_fragment_layout, container, false);

        tvNom = (TextView) v.findViewById(R.id.tvNom);
        tvPrenom = (TextView) v.findViewById(R.id.tvPrenom);
        tvCompenteces = (TextView) v.findViewById(R.id.tvCompetences);
        tvTitreEmploie = (TextView) v.findViewById(R.id.tvTitre_emploie);
        tvAnneesXp = (TextView) v.findViewById(R.id.tvAnnees_xp);
        if(getArguments() != null) {
            Bundle bundle = this.getArguments();
            Log.d("bundle", bundle.toString());

            String nom = bundle.getString("nom");
            String prenom = bundle.getString("prenom");
            String competences = bundle.getString("competences");
            String titreEmploie = bundle.getString("titreEmploie");
            String anneesXp = bundle.getString("anneesXp");

            tvNom.setText(nom);
            tvPrenom.setText(prenom);
            tvCompenteces.setText(competences);
            tvTitreEmploie.setText(titreEmploie);
            tvAnneesXp.setText(anneesXp);
        }
        return v;

    }

}
