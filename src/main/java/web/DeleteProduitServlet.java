package web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import services.ProduitMetier;
import services.ProduitMetierImpl;

/*
 * Servlet responsable de la suppression d'un produit.
 * Partie "Controller" dans MVC1.
 * 
 * Flux :
 * 1. L'utilisateur clique sur "Supprimer" dans la JSP
 * 2. Le lien contient l'ID du produit à supprimer
 * 3. La servlet récupère l'ID et appelle la couche métier
 * 4. La couche métier supprime le produit dans le DAO
 * 5. Redirection vers ListProduitServlet pour rafraîchir l'affichage
 */
public class DeleteProduitServlet extends HttpServlet {

    // Singleton de la couche métier pour accéder à la liste des produits
    private static final ProduitMetier metier = ProduitMetierImpl.getInstance();

    /*
     * Méthode GET utilisée pour recevoir l'ID du produit à supprimer.
     * On utilise GET ici car l'action est déclenchée par un lien <a href="deleteProduit?id=...">
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Récupération du paramètre "id" depuis l'URL
        Long id = Long.parseLong(req.getParameter("id"));

        // Appel à la couche métier pour supprimer le produit
        metier.deleteProduit(id);

        // Redirection vers ListProduitServlet pour afficher la liste mise à jour
        // Important : sendRedirect déclenche une nouvelle requête HTTP
        resp.sendRedirect("produits");
    }
}
