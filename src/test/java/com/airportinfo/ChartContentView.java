package com.airportinfo;

import com.airportinfo.utils.Screenshot;
import com.airportinfo.view.AbstractChartView;
import com.airportinfo.view.BarChartView;
import com.airportinfo.view.ContentView;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ChartContentView implements ContentView {
    private JPanel panel;
    private JButton saveButton;
    private JPanel chartViewPanel;

    private AbstractChartView chartView;

    public ChartContentView() {
        $$$setupUI$$$();
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Screenshot.createScreenshot(chartViewPanel, "test.png");
                    JOptionPane.showMessageDialog(panel, "Succeed", "Info", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(panel, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void load() {
        chartView.clear();
        chartView.addEntry("a", 10);
        chartView.addEntry("b", 20);
        chartView.addEntry("c", 30);
        chartView.addEntry("d", 40);
        chartView.addEntry("e", 50);
        chartView.addEntry("f", 60);
        chartView.addEntry("g", 70);
        chartView.addEntry("h", 80);
        chartView.addEntry("i", 90);
    }

    private void createUIComponents() {
        chartView = new BarChartView();
        chartViewPanel = chartView.getPanel();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(chartViewPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        saveButton = new JButton();
        saveButton.setText("Save");
        panel.add(saveButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
