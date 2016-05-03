package services;

import pojo.Directory;

import java.util.List;

/**
 * Created by Ilia Komarov on 25.04.2016.
 */
public interface DirectoryService {
    public List getAll();
    public void add(Directory directory);
    public Directory get(String name);
}
