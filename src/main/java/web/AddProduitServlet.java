package web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Produit;
import services.ProduitMetier;
import services.ProduitMetierImpl;

/*
 * Servlet responsable de l'ajout d'un produit.
 * Partie "Controller" de l'architecture MVC1.
 * 
 * Rôle :
 * - Recevoir les données du formulaire (nom, description, prix)
 * - Créer un objet Produit
 * - Appeler la couche métier pour l'ajouter à la liste DAO
 * - Mettre à jour la liste complète des produits pour l'affichage
 * - Forwarder vers la JSP pour affichage
 */
public class AddProduitServlet extends HttpServlet {

    // Instance singleton de la couche métier
    private static final ProduitMetier metier = ProduitMetierImpl.getInstance();

    // Liste locale, utilisée seulement si on voulait gérer des produits temporaires (inutile ici)
    List<Produit> produits = new ArrayList<Produit>();

    /*
     * Méthode POST : reçoit le formulaire d'ajout d'un produit
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Récupération des paramètres du formulaire
        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        Double prix = Double.parseDouble(req.getParameter("prix")); // conversion String -> Double

        // Création de l'objet Produit
        Produit p = new Produit(nom, description, prix);
        // Ajout via la couche métier (qui met à jour la liste DAO)
        metier.addProduit(p);
        // La liste locale produits.add(p) n'est pas nécessaire ici,
        // car metier.getAllProduits() contient déjà tous les produits.
        produits.add(p);
        // Mise à disposition de la liste complète pour la JSP
        req.setAttribute("listeProduits", metier.getAllProduits());
        // Forward vers la JSP pour affichage
        // Important : on ne redirige pas, sinon la requête perd ses attributs
        req.getRequestDispatcher("/WEB-INF/views/listProduits.jsp").forward(req, resp);
    }
}
