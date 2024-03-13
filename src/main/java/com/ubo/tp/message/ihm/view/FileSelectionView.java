package main.java.com.ubo.tp.message.ihm.view;

import javax.swing.*;

public class FileSelectionView {
    JFrame frame;
    String directory;
    public FileSelectionView() {
        frame = new JFrame();
    }
    public String getDirectory() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = fileChooser.showDialog(FileSelectionView.this.frame, "Select a file");
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            String selectedFolderPath = fileChooser.getSelectedFile().getAbsolutePath();
            JOptionPane.showMessageDialog(FileSelectionView.this.frame, "Selected file : " + selectedFolderPath);
            FileSelectionView.this.setDirectory(selectedFolderPath);
        }
        return this.directory;
    }
    private void setDirectory(String directory) {
        this.directory = directory;
    }
}
