package com.example.notfoodpandatest;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

// Custom cell renderer for Quantity column
class QuantityCellRenderer extends DefaultTableCellRenderer {
    private final JButton upButton;
    private final JButton downButton;

    public QuantityCellRenderer() {
        setOpaque(true);

        upButton = new JButton("+");
        downButton = new JButton("-");

        // Disable focus and border for the buttons
        upButton.setFocusable(false);
        upButton.setBorderPainted(false);
        downButton.setFocusable(false);
        downButton.setBorderPainted(false);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        // Set the value of the cell
        setText(value.toString());

        // Add the buttons to the panel
        panel.add(upButton);
        panel.add(downButton);

        return panel;
    }
}

// Custom cell editor for Quantity column
class QuantityCellEditor extends DefaultCellEditor {
    private final SpinnerNumberModel spinnerModel;
    private final JSpinner spinner;

    public QuantityCellEditor() {
        super(new JTextField());

        spinnerModel = new SpinnerNumberModel(1, 1, 100, 1);
        spinner = new JSpinner(spinnerModel);
        spinner.setBorder(null);

        // Listen for changes in the spinner value
        spinner.addChangeListener(e -> stopCellEditing());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        // Set the initial value of the spinner
        spinner.setValue(value);

        return spinner;
    }

    @Override
    public Object getCellEditorValue() {
        // Get the value from the spinner
        return spinner.getValue();
    }
}
