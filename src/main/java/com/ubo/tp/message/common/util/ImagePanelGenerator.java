package com.ubo.tp.message.common.util;

import com.ubo.tp.message.ihm.view.panel.ImagePanel;

import java.awt.*;
import java.io.File;

public class ImagePanelGenerator {
    public ImagePanel getImagePanelFromUrl(String url, Dimension size) {
        File imageFile = new File(url);
        return new ImagePanel(imageFile, size);
    }
}
