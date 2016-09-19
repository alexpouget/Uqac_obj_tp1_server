import java.net.ServerSocket;
import java.util.Scanner;

public class Main {

    /**
           * programme principal. Prend 4 arguments: 1) numéro de port, 2) répertoire source, 3)
           * répertoire classes, et 4) nom du fichier de traces (sortie)
           * Cette méthode doit créer une instance de la classe ApplicationServeur, l’initialiser
           * puis appeler aVosOrdres sur cet objet
           */
    public static void main(String[] args) {
        System.out.println("Server Part");
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Port number: ");
        int portNumber = reader.nextInt();
        ServerApplication server = new ServerApplication(portNumber);
        System.out.println("server instance Ok");
        server.order();
    }
}
