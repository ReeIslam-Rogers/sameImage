package models;

import java.util.ArrayList;
import java.util.List;

public class ImageFileSet {
    private final List<ImageFile> imageFiles = new ArrayList<>();

    public List<ImageFile> getImageFiles() {
        return imageFiles;
    }

    public void add(ImageFile imageFile){
        imageFiles.stream().forEach(imgF -> imgF.matched(imageFile));
        imageFile.matched(imageFiles);
        imageFiles.add(imageFile);
    }
}
