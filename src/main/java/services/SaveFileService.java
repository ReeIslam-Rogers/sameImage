package services;

import models.ImageFile;
import models.ImageFileSet;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SaveFileService implements PrintService{
    private final String filename;

    public SaveFileService(String name){
        this.filename = name;
    }

    @Override
    public void print(List<ImageFileSet> set) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename,false))){
            set.stream().forEach(imageFileSet -> {
                writer.println("Set::");
                for(ImageFile f:imageFileSet.getImageFiles()){
                    writer.println(f.getFile().getAbsolutePath());
                }
                writer.println("\n\n");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
