package com.example.job_finder;

/**
 * Representation de l'objet Offre de l'api de pole emploi
 * https://www.emploi-store-dev.fr/portail-developpeur-cms/home/catalogue-des-api/documentation-des-api/api/api-offres-demploi-v2/consulter-une-offre.html;JSESSIONID_JAHIA=A9A501DCC2025A0CD71ECD29A546A86A
 */


public class Offer {


    private String id;
    private String intitule;
    private String description;
    private String lieu_travail_latitude;
    private String lieu_travail_longitude;
    private String code_ROME;
    private String nom_entreprise;
    private String type_contrat;
    private String salaire;
    private String url_postulation;


    public Offer(String id, String intitule, String description, String lieu_travail_latitude, String lieu_travail_longitude, String code_ROME, String nom_entreprise, String type_contrat, String salaire, String url_postulation) {
        this.id = id;
        this.intitule = intitule;
        this.description = description;
        this.lieu_travail_latitude = lieu_travail_latitude;
        this.lieu_travail_longitude = lieu_travail_longitude;
        this.code_ROME = code_ROME;
        this.nom_entreprise = nom_entreprise;
        this.type_contrat = type_contrat;
        this.salaire = salaire;
        this.url_postulation = url_postulation;
    }

    public String getId() {
        return id;
    }

    public String getIntitule() {
        return intitule;
    }

    public String getDescription() {
        return description;
    }

    public String getLieu_travail_latitude() {
        return lieu_travail_latitude;
    }

    public String getLieu_travail_longitude() {
        return lieu_travail_longitude;
    }

    public String getCode_ROME() {
        return code_ROME;
    }

    public String getNom_entreprise() {
        return nom_entreprise;
    }

    public String getType_contrat() {
        return type_contrat;
    }

    public String getSalaire() {
        return salaire;
    }

    public String getUrl_postulation() {
        return url_postulation;
    }

}
