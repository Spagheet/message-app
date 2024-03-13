package com.ubo.tp.message.common.util;

import javax.swing.*;
import javafx.scene.layout.Pane;

public class TestController {
    TestView view1;
    TestView view2;
    public TestController() {
        view1 = new TestView<JPanel>();
        view1.add(new JPanel());

        view2 = new TestView<Pane>();
        view2.add(new Pane());
    }
}
