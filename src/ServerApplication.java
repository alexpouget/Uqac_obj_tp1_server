import com.company.Command;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.util.*;

/**
 * Created by alex on 19/09/2016.
 */
public class ServerApplication {
    private ServerSocket serverSocket;
    private Socket socketConnection;
    private String compilationFolder;
    private Map<String,Class> myClass;
    private Map<String,Object> myObjects;
    private URLClassLoader classLoader;
    /**
           * prend le numéro de port, crée un SocketServer sur le port
          */

    public ServerApplication (int port) {
        myClass = new HashMap<>();
        myObjects = new HashMap<>();
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
                socketConnection = serverSocket.accept();
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
        switch (command.getCommand()){
            case "lecture":
                read(myObjects.get(command.getIdentificator()),command.getFunction());
                break;
            case "chargement":
                load(command.getIdentificator());
                break;
            case "creation":
                create(myClass.get(command.getIdentificator()),command.getFunction());
                break;
            case "ecriture":
                write(myObjects.get(command.getIdentificator()),command.getFunction(),command.getValues().get(0));
                break;
            case "compilation":
                compilationFolder = command.getFunction();
                compile(command.getIdentificator());
                break;
            case "fonction":
                String[] types = new String[command.getTypes().size()];
                types = command.getTypes().toArray(types);

                call(myObjects.get(command.getIdentificator()),command.getFunction(),types,
                        command.getValues().toArray());
                break;
            default:
                retour("fermeture du socket server");
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


        }

    }
    /**
           * traiterLecture : traite la lecture d’un attribut. Renvoies le résultat par le
     * socket
           */
    public void read(Object _object, String attribut) {
        Object value = null;
        try {
            Field field = _object.getClass().getDeclaredField(attribut);
            //error access
            if(!field.isAccessible()){//if attribute is private
                attribut = "get"+ ( attribut.substring(0, 1).toUpperCase() + attribut.substring(1));
                call(_object,attribut,null,null);
            }
            else{
                value = field.get(_object);

            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        retour(value.toString());




    }
    /**
           * traiterEcriture : traite l’écriture d’un attribut. Confirmes au client que l’écriture
     * s’est faite correctement.
           */
    public void write(Object _object, String attribut, Object value) {

        try {
            Field field = _object.getClass().getDeclaredField(attribut);
            //error access
            if(!field.isAccessible()){//if attribute is private
                String method = "set"+ ( attribut.substring(0, 1).toUpperCase() + attribut.substring(1));
                call(_object,method,new String[]{new String(value.getClass().getName())},new Object[]{value});
            }
            else{
              // if(field.getType().toString().equals("float")){
                Class c= Class.forName(field.getType().toString());
                //field.set(_object, Float.valueOf(String.valueOf(value)));
                field.set(_object, c.cast(value));

              // }
                System.out.println("ok");
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        retour("ecriture ok");
    }
    /**
           * traiterCreation : traite la création d’un objet. Confirme au client que la création
     * s’est faite correctement.
           */
    public void create(Class _class, String identificateur) {
        Constructor<?> constructor = null;
        try {
            constructor = _class.getConstructor();
            Object object = constructor.newInstance();
            System.out.println("object Ok");
            myObjects.put(identificateur,object);
           // write(object,"nom","jean");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        retour("creation ok");

    }

    /**
     * traiterChargement : traite le chargement d’une classe. Confirmes au client que la création
     * s’est faite correctement.
     */

    public void load(String path) {
        // url = ca.uqac.8inf853.Cours
        File f = new File(compilationFolder);
        URL url = null;
        try {
            url = f.toURI().toURL();
        } catch (MalformedURLException e) {

        }

        URL[] urls = new URL[]{url};
        classLoader = new URLClassLoader(urls);

        Class _class = null;
        try {
            _class = (Class) classLoader.loadClass(path);
            myClass.put(path,_class);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        retour("chargement ok");
    }
        /**
     * traiterCompilation : traite la compilation d’un fichier source java. Confirme au client
     * que la compilation s’est faite correctement. Le fichier source est donné par son chemin
     * relatif par rapport au chemin des fichiers sources.
           */
     public void compile(String pathFile) {

         File f = new File(compilationFolder);
         f.mkdir();

         pathFile = pathFile.replace(","," ");

         Runtime rt = Runtime.getRuntime();
         try {
             System.out.println("javac -d "+compilationFolder+" "+pathFile);
             rt.exec("javac -d "+compilationFolder+" "+pathFile);

         } catch (IOException e) {
             e.printStackTrace();
         }
         retour("compilation ok");
     }

    /**
     * traiterAppel : traite l’appel d’une méthode, en prenant comme argument l’objet
     * sur lequel on effectue l’appel, le nom de la fonction à appeler, un tableau de nom de
     * types des arguments, et un tableau d’arguments pour la fonction. Le résultat de la
     * fonction est renvoyé par le serveur au client (ou le message que tout s’est bien
     * passé)
           */
     public void call(Object _object, String nameFunction, String[] types,
     Object[] valeurs) {
         Object result = null;
         Class<?> _class = _object.getClass();
         Method method = null;

         try {
             if (types != null) {
                 System.out.println("Appel de la methode:" + nameFunction);
                 Class[] t = new Class[types.length];

                 for(int i = 0; i < types.length; i++){
                     String nameClass = "";
                     nameClass = (((String)types[i]).indexOf(".")>0?"":"java.lang.") + (String)types[i];
                     t[i] = (Class) classLoader.loadClass(nameClass);

                     //------------------------------------------------------*/
                 }
                 Class classObject = (Class)classLoader.loadClass(_object.getClass().getName());
                 Method m = classObject.getMethod(nameFunction, t);
                 result = m.invoke(classObject.cast(_object), valeurs);

             }else{
                 method = _class.getMethod(nameFunction);
                 result = method.invoke(_object);
                 System.out.println(result.toString());
             }

         } catch (NoSuchMethodException e) {
             retour("la methode n'existe pas");
             e.printStackTrace();
         } catch (InvocationTargetException e) {
             e.printStackTrace();
         } catch (IllegalAccessException e) {
             e.printStackTrace();
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }

         if(null == result) {
             retour("Call ok");
         }else{
             retour(result.toString());
         }

     }

    public void retour(String retour){
        //retour
        try {
            ObjectOutputStream outToServer = new ObjectOutputStream(socketConnection.getOutputStream());
            //Send command to server

            outToServer.writeUTF(retour);
            outToServer.flush();
            outToServer.close();
            socketConnection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        order();
    }


}
