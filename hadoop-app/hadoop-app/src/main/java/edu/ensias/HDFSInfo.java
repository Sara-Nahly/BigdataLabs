package edu.ensias;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSInfo {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: HDFSInfo <chemin_fichier_HDFS>");
            System.exit(1); }

        String fichierHDFS = args[0]; // ex: /user/root/input/achats.txt

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path cheminComplet = new Path(fichierHDFS);
        if (!fs.exists(cheminComplet)) {
            System.out.println("Le fichier n'existe pas");
        } else {
            FileStatus infos = fs.getFileStatus(cheminComplet);
            System.out.println("File Length: " + infos.getLen() + " octets");
            System.out.println("File Name: " + infos.getPath().getName());
            System.out.println("File Replication: " + infos.getReplication());
            System.out.println("File Block Size: " + infos.getBlockSize());
            // Afficher les blocs
            BlockLocation[] blocks = fs.getFileBlockLocations(infos, 0, infos.getLen());
            System.out.println("Number of blocks: " + blocks.length);
            for (int i = 0; i < blocks.length; i++) {
                System.out.println("Block " + i + ": " + blocks[i].getOffset() + " -> " + (blocks[i].getOffset() + blocks[i].getLength()));
            }  }

        fs.close();
    }
}
