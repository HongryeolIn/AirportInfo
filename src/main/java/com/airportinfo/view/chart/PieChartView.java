package com.airportinfo.view.chart;

import com.airportinfo.utils.FontManager;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.ResourceBundle;
import java.util.function.Function;

/**
 * Show chart with pie.
 *
 * @author lalaalal
 */
public class PieChartView extends AbstractChartView {
    private JPanel panel;
    private JLabel titleLabel;
    private JPanel pieChartPanel;
    private JPanel entryDetailPanel;

    public PieChartView() {
        $$$setupUI$$$();

        titleLabel.setFont(FontManager.HEADER_FONT);
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Add Entry and add entry name to entry detail field.
     *
     * @param name  Entry name
     * @param value Entry value
     */
    @Override
    public void addEntry(String name, Number value) {
        super.addEntry(name, value);
        EntryDetailView entryDetailView = new EntryDetailView(name, getColor(entries.size() - 1));
        entryDetailPanel.add(entryDetailView.getPanel());
    }

    @Override
    public void updateChartView(Graphics graphics) {
        final int width = (int) (pieChartPanel.getWidth() * 0.8);
        final int height = (int) (pieChartPanel.getHeight() * 0.8);
        final int diameter = Math.min(width, height);
        final int x = (pieChartPanel.getWidth() - diameter) / 2;
        final int y = (pieChartPanel.getHeight() - diameter) / 2;

        drawChart((startAngle, angle, ratio, colorIndex, entry) -> {
            graphics.setColor(getColor(colorIndex));
            graphics.fillArc(x, y, diameter, diameter, startAngle, angle);
        });
        drawChart((startAngle, angle, ratio, colorIndex, entry) -> {
            String label = numberFormat.formatNumber(entry.value()) + String.format(" (%.1f%%)", ratio * 100);
            drawString(x, y, diameter / 2, startAngle, angle, graphics, label);
        });
    }

    private void drawChart(ChartDrawer drawer) {
        final double sum = sumOfEntries();
        int startAngle = 90;
        int colorIndex = 0;
        for (Entry entry : entries) {
            double ratio = entry.value().doubleValue() / sum;
            int angle = (int) -(ratio * 360);
            drawer.draw(startAngle, angle, ratio, colorIndex, entry);

            startAngle += angle;
            colorIndex += 1;
        }
    }

    @FunctionalInterface
    private interface ChartDrawer {
        void draw(int startAngle, int angle, double ratio, int colorIndex, Entry entry);
    }

    private void drawString(int x, int y, int radius, int startAngle, int angle, Graphics graphics, String string) {
        int stringAngle = startAngle + angle / 2;
        int centerX = x + radius;
        int centerY = y + radius;
        int stringWidth = graphics.getFontMetrics().stringWidth(string);
        int stringX = centerX + (int) (radius * Math.cos(Math.toRadians(stringAngle)) * 0.6 - (stringWidth * 0.4));
        int stringY = centerY - (int) (radius * Math.sin(Math.toRadians(stringAngle)) * 0.6);
        graphics.setColor(Color.DARK_GRAY);
        graphics.setFont(UIManager.getDefaults().getFont("Label.font"));
        graphics.drawString(string, stringX, stringY);
    }

    private double sumOfEntries() {
        double sum = 0;
        for (Entry entry : entries)
            sum += entry.value().doubleValue();

        return sum;
    }

    private void createUIComponents() {
        pieChartPanel = chartPanel;
        entryDetailPanel = new JPanel();
        entryDetailPanel.setLayout(new BoxLayout(entryDetailPanel, BoxLayout.X_AXIS));
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
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        titleLabel = new JLabel();
        this.$$$loadLabelText$$$(titleLabel, this.$$$getMessageFromBundle$$$("string", "default_chart_title"));
        panel.add(titleLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.add(pieChartPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.add(entryDetailPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
    }

    private static Method $$$cachedGetBundleMethod$$$ = null;

    private String $$$getMessageFromBundle$$$(String path, String key) {
        ResourceBundle bundle;
        try {
            Class<?> thisClass = this.getClass();
            if ($$$cachedGetBundleMethod$$$ == null) {
                Class<?> dynamicBundleClass = thisClass.getClassLoader().loadClass("com.intellij.DynamicBundle");
                $$$cachedGetBundleMethod$$$ = dynamicBundleClass.getMethod("getBundle", String.class, Class.class);
            }
            bundle = (ResourceBundle) $$$cachedGetBundleMethod$$$.invoke(null, path, thisClass);
        } catch (Exception e) {
            bundle = ResourceBundle.getBundle(path);
        }
        return bundle.getString(key);
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadLabelText$$$(JLabel component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setDisplayedMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
