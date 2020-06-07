package com.example.job_finder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class OfferViewHolder extends RecyclerView.ViewHolder {


    TextView salaire;
    TextView intitule;


    public OfferViewHolder(View itemView) {
        super(itemView);
        this.salaire = (TextView) itemView.findViewById(R.id.salaire);
        this.intitule = (TextView) itemView.findViewById(R.id.intitule);
    }

    public void bind(Offer offer){
        this.salaire.setText(offer.getSalaire());
        this.intitule.setText(offer.getIntitule());
    }
}
