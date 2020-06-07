package com.example.job_finder;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


// Un petit faux singleton
public class OfferListSingleton {
    private static OfferListSingleton instance = new OfferListSingleton();

    private List<Offer> offerList;

    private Stack<Offer> singleOfferList;

    public OfferListSingleton() {
        this.offerList = new ArrayList();
        this.singleOfferList = new Stack();
    }

    public static final OfferListSingleton getInstance(){
        return instance;
    }

    public static void addOffer(Offer offer){
        OfferListSingleton.getInstance().offerList.add(offer);
    }


    public static Offer getOffer(int index){
        return OfferListSingleton.getInstance().offerList.get(index);
    }

    public static List<Offer> getOfferList(){
        return OfferListSingleton.getInstance().offerList;
    }

    public static List<Offer> getSingleOfferInList(int index){
        // Si deja un elem on pop
        if(!OfferListSingleton.getInstance().singleOfferList.isEmpty()){
            OfferListSingleton.getInstance().singleOfferList.pop();
        }
        // On push l'objet
        OfferListSingleton.getInstance().singleOfferList.push(OfferListSingleton.getOffer(index));
        // On retourne la pile
        return OfferListSingleton.getInstance().singleOfferList;
    }
}
