package com.example.notfoodpandatest.swing;

import javax.swing.*;
import java.awt.*;

public class RoundedLabel extends JLabel {
    private Color backgroundColor;

    public RoundedLabel(String text, Color backgroundColor) {
        super(text);
        this.backgroundColor = backgroundColor;
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.BOTTOM);
        setForeground(Color.WHITE);
        setOpaque(false);
        setPreferredSize(new Dimension(20, 20));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(backgroundColor);

        int diameter = Math.min(getWidth(), getHeight());
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;

        g2.fillOval(x, y, diameter, diameter);
        g2.dispose();

        super.paintComponent(g);
    }
}
