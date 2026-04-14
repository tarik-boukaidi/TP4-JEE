package dao;

import java.util.ArrayList;
import java.util.List;

import models.Produit;

public class ProduitImpl implements ProduitDAO {
    private static final List<Produit> produits = new ArrayList<>();
    private static Long nextId = 1L;

    static {
        produits.add(new Produit(1L, "PC 1", "Sony  1", 7000.0));
        produits.add(new Produit(2L, "PC 2", "Sony  2", 6000.0));
        produits.add(new Produit(3L, "Imprimante", "HP ", 2500.0));
        nextId = 4L;
    }

    @Override
    public void addProduit(Produit p) {
        p.setIdProduit(nextId++);
        produits.add(p);
    }

    @Override
    public void deleteProduit(Long id) {
        produits.remove(getProduitById(id));
    }

    @Override
    public Produit getProduitById(Long id) {
        for (Produit p : produits) {
            if (p.getIdProduit().equals(id)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Produit> getAllProduits() {
        return produits;
    }

    @Override
    public void updateProduit(Produit p) {
        Produit existingProduit = getProduitById(p.getIdProduit());
        if (existingProduit != null) {
            existingProduit.setNom(p.getNom());
            existingProduit.setDescription(p.getDescription());
            existingProduit.setPrix(p.getPrix());
        }
    }
}
