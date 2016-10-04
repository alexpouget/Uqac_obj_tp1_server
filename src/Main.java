import com.company.Command;

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
        Scanner reader = new Scanner(System.in);
        System.out.println("Port number: ");
        int portNumber = reader.nextInt();
        ServerApplication server = new ServerApplication(portNumber);
        System.out.println("server instance Ok");
        server.order();
        /*Command c = new Command();
        c.setCommand("compilation");
        c.setIdentificator("./ressource/Cours.java ./ressource/Etudiant.java");
        c.setFunction("./classes");
        server.command(c);
        c = new Command();
        c.setCommand("chargement");
        c.setIdentificator("ca.uqac.registraire.Cours");
        server.command(c);
        c.setCommand("creation");
        c.setIdentificator("ca.uqac.registraire.Cours");
        c.setFunction("8inf853");
        server.command(c);
        c.setCommand("ecriture");
        c.setIdentificator("8inf853");
        c.setFunction("titre");
        c.addValues("Architecture des applications");
        server.command(c);
        c.setCommand("lecture");
        c.setIdentificator("8inf853");
        c.setFunction("titre");
        server.command(c);

        c.setCommand("chargement");
        c.setIdentificator("ca.uqac.registraire.Etudiant");
        server.command(c);
        c.setCommand("creation");
        c.setIdentificator("ca.uqac.registraire.Etudiant");
        c.setFunction("mathilde");
        server.command(c);
        c = new Command();
        c.setCommand("ecriture");
        c.setIdentificator("mathilde");
        c.setFunction("nom");
        c.addValues("Mathilde Boivin");
        server.command(c);

        //ecriture#mathilde#nom#Mathilde Boivin

        /*Command c1 = new Command();
        c1.setCommand("fonction");
        c1.setIdentificator("8inf853");
        c1.setFunction("getNote");
        c1.addTypes("ca.uqac.registraire.Etudiant");
        c1.addValues("mathilde");
        server.command(c1);*/

        /*Command c1 = null;
        c1 = new Command();
        c1.setCommand("fonction");
        c1.setIdentificator("8inf853");
        c1.setFunction("ajouteEtudiant");
        c1.addTypes("ca.uqac.registraire.Etudiant");
        c1.addValues("mathilde");
        server.command(c1);

/*
        c1 = new Command();
        c1.setCommand("fonction");
        c1.setIdentificator("8inf853");
        c1.setFunction("getNote");
        c1.addTypes("ca.uqac.registraire.Etudiant");
        c1.addValues("mathilde");
        server.command(c1);*/


    }
}
