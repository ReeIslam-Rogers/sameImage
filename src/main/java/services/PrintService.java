package services;

import models.ImageFileSet;

import java.util.List;

public interface PrintService {
    void print(List<ImageFileSet> set);
}
