package com.hedizair.soundboard.model.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class StopButton extends JButton {
    private static final int WIDTH = 90;
    private static final int HEIGHT = 36;
    private static final int ARC = 18;

    private static final Color BG = new Color(247, 247, 248);
    private static final Color BG_HOVER = new Color(235, 235, 238);
    private static final Color BG_PRESSED = new Color(220, 220, 225);
    private static final Color BORDER = new Color(225, 225, 229);
    private static final Color ICON = new Color(60, 60, 66);
    private static final Color ICON_DISABLED = new Color(195, 195, 200);
    private static final Color TEXT = new Color(60, 60, 66);
    private static final Color TEXT_DISABLED = new Color(195, 195, 200);

    private boolean hover = false;
    private boolean pressed = false;

    public StopButton() {
        super("Stop");
        setPreferredSize(new java.awt.Dimension(WIDTH, HEIGHT));
        setMinimumSize(new java.awt.Dimension(WIDTH, HEIGHT));
        setMaximumSize(new java.awt.Dimension(WIDTH, HEIGHT));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setMargin(new java.awt.Insets(0, 0, 0, 0));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setFont(getFont().deriveFont(java.awt.Font.PLAIN, 13f));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                pressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressed = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        boolean enabled = isEnabled();

        RoundRectangle2D shape = new RoundRectangle2D.Float(0.5f, 0.5f, w - 1f, h - 1f, ARC, ARC);

        Color bg = !enabled ? BG : (pressed ? BG_PRESSED : (hover ? BG_HOVER : BG));
        g2.setColor(bg);
        g2.fill(shape);

        g2.setStroke(new BasicStroke(1f));
        g2.setColor(BORDER);
        g2.draw(shape);

        int squareSize = 9;
        int squareX = 16;
        int squareY = (h - squareSize) / 2;
        g2.setColor(enabled ? ICON : ICON_DISABLED);
        g2.fillRoundRect(squareX, squareY, squareSize, squareSize, 2, 2);

        g2.setFont(getFont());
        g2.setColor(enabled ? TEXT : TEXT_DISABLED);
        java.awt.FontMetrics fm = g2.getFontMetrics();
        int textX = squareX + squareSize + 8;
        int textY = (h + fm.getAscent() - fm.getDescent()) / 2;
        g2.drawString("Stop", textX, textY);

        g2.dispose();
    }

    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        repaint();
    }
}