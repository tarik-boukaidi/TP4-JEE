package services;

import java.util.List;

import models.Produit;
import dao.ProduitDAO;
import dao.ProduitImpl;

public class ProduitMetierImpl implements ProduitMetier {
    private static final ProduitMetier INSTANCE = new ProduitMetierImpl();

    private ProduitDAO dao;

    private ProduitMetierImpl() {
        this.dao = new ProduitImpl();
    }

    public static ProduitMetier getInstance() {
        return INSTANCE;
    }
    
    @Override
    public void addProduit(Produit p) {
        getDao().addProduit(p);
    }

    @Override
    public void deleteProduit(Long id) {
        getDao().deleteProduit(id);
    }

    @Override
    public List<Produit> getAllProduits() {
        return getDao().getAllProduits();
    }

    @Override
    public Produit getProduitById(Long id) {
        return getDao().getProduitById(id);
    }

    @Override
    public void updateProduit(Produit p) {
        getDao().updateProduit(p);
    }
    
    private ProduitDAO getDao() {
        return dao;
    }
}
