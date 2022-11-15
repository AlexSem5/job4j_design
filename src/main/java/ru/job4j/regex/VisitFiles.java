package ru.job4j.regex;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * A simple visitor of files with default behavior to visit all files
 * in compliance with the given predicate.
 */
public class VisitFiles extends SimpleFileVisitor<Path> {
    
    private Predicate<Path> condition;
    
    private List<Path> paths = new ArrayList<>();
    
    public List<Path> getPaths() {
        return paths;
    }
    
    public VisitFiles(Predicate<Path> condition) {
        this.condition = condition;
    }
    
    /**
     * @param file  a reference to the file
     * @param attrs the file's basic attributes
     * @return
     * @throws IOException
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            paths.add(file);
        }
        return super.visitFile(file, attrs);
    }
}
