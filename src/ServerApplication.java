import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by alex on 19/09/2016.
 */
public class ServerApplication {
    private ServerSocket serverSocket;

    /**
           * prend le numéro de port, crée un SocketServer sur le port
          */

    public ServerApplication (int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
           * Se met en attente de connexions des clients. Suite aux connexions, elle lit
     * ce qui est envoyé à travers la Socket, recrée l’objet Commande envoyé par
     * le client, et appellera traiterCommande(Commande uneCommande)
           */
    public void order() {
        if(serverSocket != null){
            try {
                Socket socketConnection = serverSocket.accept();
                //SocketConnection.getInputStream is blocking
                ObjectInputStream inFromClient = new ObjectInputStream(socketConnection.getInputStream());
                Command command = (Command) inFromClient.readObject();
                command(command);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
    /**
     * prend uneCommande dument formattée, et la traite. Dépendant du type de commande,
     * elle appelle la méthode spécialisée
     * */
    public void command(Command command) {
        switch (command.getFunction()){
            case "lecture":
                //get the object
                read("object",command.getFunction());
                break;
            case "chargement":
                //get the object
                load("class");
                break;
            case "creation":
                //get the object
               // create("class",command.getFunction());
                break;
            case "ecriture":
                //get the object
                write("object",command.getFunction(),"string");
                break;
            case "compilation":
                //get the object
                compile("chemin");
                break;
            case "fonction":
                //get the object
                read("object",command.getFunction());
                break;
            default:
                System.out.println("fermeture du socket server");


        }

    }
    /**
           * traiterLecture : traite la lecture d’un attribut. Renvoies le résultat par le
     * socket
           */
    public void read(Object pointeurObjet, String attribut) {

    }
    /**
           * traiterEcriture : traite l’écriture d’un attribut. Confirmes au client que l’écriture
     * s’est faite correctement.
           */
    public void write(Object pointeurObjet, String attribut, Object valeur) {

    }
    /**
           * traiterCreation : traite la création d’un objet. Confirme au client que la création
     * s’est faite correctement.
           */
    public void create(Class classeDeLobjet, String identificateur) {

    }
    /**
     * traiterChargement : traite le chargement d’une classe. Confirmes au client que la création
     * s’est faite correctement.
     */
    public void load(String nomQualifie) {

    }
    /**
     * traiterCompilation : traite la compilation d’un fichier source java. Confirme au client
     * que la compilation s’est faite correctement. Le fichier source est donné par son chemin
     * relatif par rapport au chemin des fichiers sources.
           */
     public void compile(String cheminRelatifFichierSource) {

     }

    /**
     * traiterAppel : traite l’appel d’une méthode, en prenant comme argument l’objet
     * sur lequel on effectue l’appel, le nom de la fonction à appeler, un tableau de nom de
     * types des arguments, et un tableau d’arguments pour la fonction. Le résultat de la
     * fonction est renvoyé par le serveur au client (ou le message que tout s’est bien
     * passé)
           */
     public void call(Object pointeurObjet, String nomFonction, String[] types,
     Object[] valeurs) {

    }


}
