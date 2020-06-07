package com.example.job_finder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferViewHolder> {
    List<Offer> offerList;

    //ajouter un constructeur prenant en entrée une liste
    public OfferAdapter(List<Offer> list) {
        this.offerList = list;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public OfferViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offer_card_view,viewGroup,false);
        return new OfferViewHolder(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(OfferViewHolder myViewHolder, int position) {
        Offer offer = offerList.get(position);
        myViewHolder.bind(offer);
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }
}
