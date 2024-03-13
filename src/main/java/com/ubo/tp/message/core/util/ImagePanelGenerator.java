package main.java.com.ubo.tp.message.core.util;

import main.java.com.ubo.tp.message.ihm.view.panel.ImagePanel;

import java.awt.*;
import java.io.File;

public class ImagePanelGenerator {
    public ImagePanel getImagePanelFromUrl(String url, Dimension size) {
        File imageFile = new File(url);
        return new ImagePanel(imageFile, size);
    }
}
