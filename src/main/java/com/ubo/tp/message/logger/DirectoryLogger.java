package com.ubo.tp.message.logger;

import com.ubo.tp.message.core.directory.IWatchableDirectoryObserver;

import java.io.File;
import java.util.Set;

public class DirectoryLogger implements IWatchableDirectoryObserver {
    void printFileSet(Set<File> files) {
        for(File f : files) {
            System.out.println("file : " + f.getName() + "\n");
        }
    }
    @Override
    public void notifyPresentFiles(Set<File> presentFiles) {
        System.out.println("present files :\n");
        printFileSet(presentFiles);
    }

    @Override
    public void notifyNewFiles(Set<File> newFiles) {
        System.out.println("new files :\n");
        printFileSet(newFiles);
    }

    @Override
    public void notifyDeletedFiles(Set<File> deletedFiles) {
        System.out.println("deleted files :\n");
        printFileSet(deletedFiles);
    }

    @Override
    public void notifyModifiedFiles(Set<File> modifiedFiles) {
        System.out.println("modified files :\n");
        printFileSet(modifiedFiles);
    }
}
