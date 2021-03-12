package models;

import util.IdGeneratorUtil;
import util.ImageUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageFile {
    private final long id;
    private final File file;
    private final BufferedImage image;
    private final List<Long> sameGroup;

    public ImageFile(File file) throws IOException {
        this.id = IdGeneratorUtil.next(this.getClass());
        this.file = file;
        this.image = ImageUtil.readImage(file);
        this.sameGroup = new ArrayList<>();
    }

    public File getFile() {
        return file;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void matched(ImageFile img){
        if(!this.sameGroup.contains(img.id)){
            sameGroup.add(img.id);
        }
    }

    public void matched(List<ImageFile> imgs){
        imgs.stream().forEach(img -> {
            if(!this.sameGroup.contains(img.id)){
                sameGroup.add(img.id);
            }
        });
    }

    public boolean alreadyMatch(ImageFile img){
        return sameGroup.contains(img.id);
    }
}
