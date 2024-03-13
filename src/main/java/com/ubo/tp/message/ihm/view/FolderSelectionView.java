package com.ubo.tp.message.ihm.view;

import javax.swing.*;

public class FolderSelectionView {
    JFrame frame;
    String directory;
    public FolderSelectionView() {
        frame = new JFrame();
    }
    public String getDirectory() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showDialog(FolderSelectionView.this.frame, "Select a directory");
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            String selectedFolderPath = fileChooser.getSelectedFile().getAbsolutePath();
            JOptionPane.showMessageDialog(FolderSelectionView.this.frame, "Selected folder : " + selectedFolderPath);
            FolderSelectionView.this.setDirectory(selectedFolderPath);
        }
        return this.directory;
    }
    private void setDirectory(String directory) {
        this.directory = directory;
    }
}
