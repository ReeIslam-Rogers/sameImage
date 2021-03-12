package models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ImageUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static util.Config.CONFIG;

public class Dictionary {
    private static Logger logger = LoggerFactory.getLogger(Dictionary.class);

    private final String loc;
    private final File dir;
    private final String[] EXTENSIONS;
    private final double thrasHold;

    private final List<ImageFile> imageFiles = new ArrayList<>();


    public Dictionary(String loc, String[] ext){
        this.loc = loc;
        this.dir = new File(loc);
        this.EXTENSIONS = ext;
        this.thrasHold = Double.parseDouble(CONFIG.get("diffPercentage"));
    }

    public List<ImageFileSet> getSet(){
        load();

        List<ImageFileSet> set = new ArrayList<>();
        for (int i=0;i<imageFiles.size();i++){
            ImageFileSet imageFileSet = createSet(i);
            if(imageFileSet != null){
                set.add(imageFileSet);
            }
        }
        return set;
    }

    private ImageFileSet createSet(int start){
        ImageFileSet imageFileSet = new ImageFileSet();
        ImageFile img1 = imageFiles.get(start);
        imageFileSet.add(img1);
        for (int i=start+1;i<imageFiles.size();i++){
            ImageFile img2 = imageFiles.get(i);
            if(!img1.alreadyMatch(img2)) {
                double prc = ImageUtil.getPercentageDiff(img1, img2);
                if (prc < thrasHold) {
                    imageFileSet.add(img2);
                }
            }
        }
        return imageFileSet.getImageFiles().size() > 1 ? imageFileSet : null;
    }

    private void load(){
        if(dir.isDirectory()){
            for(final File f:dir.listFiles(IMAGE_FILTER)){
                try{
                    imageFiles.add(new ImageFile(f));
                } catch (IOException e) {
                    logger.error("Error loading images::\n\tFile::{}\n{}",f.getAbsolutePath(),e.fillInStackTrace());
                }
            }
        } else {
            logger.error("{} is not a directory", loc);
            throw new RuntimeException();
        }
    }

    final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };
}
