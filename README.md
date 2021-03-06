# Uqac_obj_tp1_server
Server Part tp1 cours object avancé partie Server

Objectif :

Le but de ce projet est de familiariser les étudiants avec les concepts suivants :
Paradigme objet : abstraction, héritage, modularité
Paquetage d’introspection (« reflection package »)
Chargement dynamique des classes
Instancier des classes par ClassLoader…
Invoquer des fonctions dynamiquement
Programmation de socket
Sérialisation      
Divers mécanismes avancés de Java : généricité, lambda, entres-sorties nio, etc.

Structure générale du programme :

Il s’agit de développer un programme client-serveur illustré dans la figure 1.
La partie client ira lire dans un fichier de données un ensemble de commandes que le client devra faire exécuter par le serveur.
Le client et le serveur communiquent à l’aide de sockets, RMI, etc.


•        La classe ApplicationServeur devra implanter au moins les méthodes suivantes:
public class ApplicationServeur {
      /**
      * prend le numéro de port, crée un SocketServer sur le port
      */
      public ApplicationServeur (int port) {…}
     
      /**
      * Se met en attente de connexions des clients. Suite aux connexions, elle lit
* ce qui est envoyé à travers la Socket, recrée l’objet Commande envoyé par
* le client, et appellera traiterCommande(Commande uneCommande)
      */        
      public void aVosOrdres() {…}
     
     /**
            * prend uneCommande dument formattée, et la traite. Dépendant du type de commande,
* elle appelle la méthode spécialisée
      */
      public void traiteCommande(Commande uneCommande) {…}
     
      /**
      * traiterLecture : traite la lecture d’un attribut. Renvoies le résultat par le
	* socket
      */
      public void traiterLecture(Object pointeurObjet, String attribut) {…}
     
      /**
      * traiterEcriture : traite l’écriture d’un attribut. Confirmes au client que l’écriture
	* s’est faite correctement.
      */
      public void traiterEcriture(Object pointeurObjet, String attribut, Object valeur) {…}
 
      /**
      * traiterCreation : traite la création d’un objet. Confirme au client que la création
	* s’est faite correctement.
      */
      public void traiterCreation(Class classeDeLobjet, String identificateur) {…}
 
      /**
      * traiterChargement : traite le chargement d’une classe. Confirmes au client que la création
	* s’est faite correctement.
      */
      public void traiterChargement(String nomQualifie) {…}
 
      /**
      * traiterCompilation : traite la compilation d’un fichier source java. Confirme au client
	* que la compilation s’est faite correctement. Le fichier source est donné par son chemin
	* relatif par rapport au chemin des fichiers sources.
      */
      public void traiterCompilation(String cheminRelatifFichierSource) {…}

      /**
            * traiterAppel : traite l’appel d’une méthode, en prenant comme argument l’objet
	* sur lequel on effectue l’appel, le nom de la fonction à appeler, un tableau de nom de
	* types des arguments, et un tableau d’arguments pour la fonction. Le résultat de la
	* fonction est renvoyé par le serveur au client (ou le message que tout s’est bien
	* passé)
      /**
      public void traiterAppel(Object pointeurObjet, String nomFonction, String[] types,
Object[] valeurs) {…}
     
      /**
      * programme principal. Prend 4 arguments: 1) numéro de port, 2) répertoire source, 3)
      * répertoire classes, et 4) nom du fichier de traces (sortie)
      * Cette méthode doit créer une instance de la classe ApplicationServeur, l’initialiser
      * puis appeler aVosOrdres sur cet objet
      */
      public static void main(String[] args) {…}
}
