package services;

import models.ImageFile;
import models.ImageFileSet;

import java.util.List;

public class StdoutService implements PrintService{
    @Override
    public void print(List<ImageFileSet> set) {
        set.stream().forEach(imageFileSet -> {
            System.out.println("Set::");
            for(ImageFile f:imageFileSet.getImageFiles()){
                System.out.println(f.getFile().getAbsolutePath());
            }
            System.out.print("\n\n");
        });
    }
}
