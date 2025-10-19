package edu.ensias;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HadoopFileStatus {
    public static void main(String[] args) {
        // Vérification des arguments
        if (args.length != 3) {
            System.out.println("Usage: HadoopFileStatus <chemin_dossier_HDFS> <nom_fichier> <nouveau_nom>");
            System.exit(1);}

        String cheminDossier = args[0];  // ./input
        String nomFichier = args[1];     // purchases.txt
        String nouveauNom = args[2];     // achats.txt

        Configuration conf = new Configuration();
        try {
            FileSystem fs = FileSystem.get(conf);

            // Chemin complet du fichier source
            Path cheminComplet = new Path(cheminDossier, nomFichier);

            // Récupération des informations du fichier
            FileStatus infos = fs.getFileStatus(cheminComplet);
            System.out.println("File Length: " + infos.getLen() + " octets");
            System.out.println("File Name: " + infos.getPath().getName());
            System.out.println("File Size: " + infos.getLen());
            System.out.println("File Replication: " + infos.getReplication());
            System.out.println("File Block Size: " + infos.getBlockSize());

            // Renommer le fichier sur HDFS
            Path nouveauChemin = new Path(cheminDossier, nouveauNom);
            if (fs.rename(cheminComplet, nouveauChemin)) {
                System.out.println("File renamed to " + nouveauNom);
            } else {
                System.out.println("Failed to rename file");
            }

            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

