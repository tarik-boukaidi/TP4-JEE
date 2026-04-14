package services;

import java.util.List;

import models.Produit;

public interface ProduitMetier {
    void addProduit(Produit p);

    void deleteProduit(Long id);

    List<Produit> getAllProduits();

    Produit getProduitById(Long id);

    void updateProduit(Produit p);
}
