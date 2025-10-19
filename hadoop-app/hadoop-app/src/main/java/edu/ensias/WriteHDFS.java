package edu.ensias;  // ou ton package habituel

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class WriteHDFS {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: WriteHDFS <chemin_fichier_HDFS> <texte>");
            System.exit(1);
        }

        String fichierHDFS = args[0]; // ex: /user/root/input/bonjour.txt
        String contenu = args[1];     // texte à écrire dans le fichier

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path cheminComplet = new Path(fichierHDFS);

        if (!fs.exists(cheminComplet)) {
            FSDataOutputStream outStream = fs.create(cheminComplet);
            outStream.writeUTF(contenu);  // écrit le texte dans le fichier
            outStream.close();
            System.out.println("Fichier créé et texte écrit sur HDFS : " + fichierHDFS);
        } else {
            System.out.println("Le fichier existe déjà : " + fichierHDFS);
        }
        fs.close();
    }
}
