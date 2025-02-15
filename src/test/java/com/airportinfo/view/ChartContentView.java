package com.airportinfo.view;

import com.airportinfo.Setting;
import com.airportinfo.util.Screenshot;
import com.airportinfo.view.chart.*;
import com.airportinfo.view.content.ContentView;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ChartContentView extends ContentView {
    private JPanel panel;
    private JButton saveButton;
    private JPanel chartViewPanel;
    private JButton toggleThemeButton;
    private JButton barButton;
    private JButton pieButton;
    private JButton changeColorSchemeButton;
    private AbstractChartView chartView;
    private final PieChartView pieChartView = new PieChartView();
    private final HistogramView barChartView = new HistogramView();

    public ChartContentView(MainFrame mainFrame) {
        chartView = barChartView;
        chartViewPanel.add(chartView.getPanel());
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
        toggleThemeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.toggleTheme();
            }
        });

        barButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setChartView(barChartView);
            }
        });
        pieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setChartView(pieChartView);
            }
        });

        addComponentView(barChartView);
        addComponentView(pieChartView);
        changeColorSchemeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Color[] colors = {Color.decode("#B1AFFF"), Color.decode("#C8FFD4"), Color.decode("#DFD3C3"), Color.decode("#F8EDE3"), Color.decode("#AEBDCA"), Color.decode("#FF8787")};
                chartView.setColorScheme(colors);
            }
        });

        Setting setting = Setting.getInstance();
        setting.attach(() -> {
            barChartView.showLegendLabel = setting.isShowChartLabel();
            pieChartView.showLegendLabel = setting.isShowChartLabel();
            chartView.getPanel().repaint();
        }, Setting.CHART_CHANGE);
        setting.attach(() -> {
            barChartView.showGuideline = setting.isShowHistogramGuideLine();
            barChartView.setLegendInterval(setting.getHistogramLegendInterval());
            chartView.getPanel().repaint();
        }, Setting.HISTOGRAM_CHANGE);
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void load() {
        LegendList legendList = new LegendList();
        legendList.add("apple", 0.1);
        legendList.add("banana", 0.2);
        legendList.add("cherry", 0.3);
        legendList.add("dragon", 0.25);
        legendList.add("eraser", 0.17);
        legendList.add("fruit", 0.15);
        chartView.setLegends(legendList);
        chartView.setNumberFormat(NumberFormat.DOUBLE_FORMAT);

        panel.revalidate();
        panel.repaint();
    }

    private void setChartView(AbstractChartView chartView) {
        this.chartView = chartView;
        chartViewPanel.removeAll();
        chartViewPanel.add(chartView.getPanel());
        load();
    }

    private void createUIComponents() {
        chartView = barChartView;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        chartViewPanel = new JPanel();
        chartViewPanel.setLayout(new CardLayout(0, 0));
        panel.add(chartViewPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        saveButton = new JButton();
        saveButton.setText("Save");
        panel.add(saveButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        toggleThemeButton = new JButton();
        toggleThemeButton.setText("Toggle Theme");
        panel.add(toggleThemeButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panel1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        barButton = new JButton();
        barButton.setText("Bar");
        panel1.add(barButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pieButton = new JButton();
        pieButton.setText("Pie");
        panel1.add(pieButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        changeColorSchemeButton = new JButton();
        changeColorSchemeButton.setText("Change Color Scheme");
        panel.add(changeColorSchemeButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
