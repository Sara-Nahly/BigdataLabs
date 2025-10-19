package edu.ensias;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class ReadHDFS {

    public static void main(String[] args) throws IOException {
        // Vérifier que le fichier est passé en argument
        if (args.length != 1) {
            System.out.println("Usage: ReadHDFS <chemin_fichier_HDFS>");
            System.exit(1);
        }

        String fichierHDFS = args[0]; // ex: /user/root/input/achats.txt

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        Path nomComplet = new Path(fichierHDFS);

        if (!fs.exists(nomComplet)) {
            System.out.println("Le fichier n'existe pas : " + fichierHDFS);
        } else {
            // Lire le fichier ligne par ligne
            FSDataInputStream inStream = fs.open(nomComplet);
            BufferedReader br = new BufferedReader(new InputStreamReader(inStream));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            inStream.close();
        }

        fs.close();
    }
}
