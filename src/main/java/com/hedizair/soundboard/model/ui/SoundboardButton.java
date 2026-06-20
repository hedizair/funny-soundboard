package com.hedizair.soundboard.model.ui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class SoundboardButton extends JButton {
    protected static final int WIDTH = 150;
    protected static final int HEIGHT = 110;
    protected static final int ARC = 16;
    protected static final int LABEL_HEIGHT = 30;

    private static final int MAX_CHARS = 16;
    private static final Color BG = new Color(247, 247, 248);
    private static final Color BG_HOVER = new Color(238, 238, 240);
    private static final Color BG_PRESSED = new Color(225, 225, 229);
    private static final Color BG_DISABLED = new Color(240, 240, 241);
    private static final Color BORDER = new Color(225, 225, 229);
    private static final Color TEXT = new Color(40, 40, 45);
    private static final Color TEXT_DISABLED = new Color(180, 180, 185);
    private static final Color PLACEHOLDER = new Color(210, 208, 220);

    private BufferedImage image;
    private final String displayName;
    private boolean hover = false;
    private boolean pressed = false;

    public SoundboardButton(String name, String iconPath) {
        this.displayName = truncate(name);

        setPreferredSize(new java.awt.Dimension(WIDTH, HEIGHT));
        setMinimumSize(new java.awt.Dimension(WIDTH, HEIGHT));
        setMaximumSize(new java.awt.Dimension(WIDTH, HEIGHT));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setMargin(new java.awt.Insets(0, 0, 0, 0));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (iconPath != null) {
            loadImage(iconPath);
        }

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

    private void loadImage(String imgPath) {
        try {
            URL url = getClass().getClassLoader().getResource(imgPath);
            if (url != null) {
                image = ImageIO.read(url);
            }
        } catch (Exception e) {
            image = null;
        }
    }

    private static String truncate(String text) {
        if (text.length() <= MAX_CHARS) {
            return text;
        }
        return text.substring(0, MAX_CHARS - 1) + "…";
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        int w = getWidth();
        int h = getHeight();
        boolean enabled = isEnabled();

        RoundRectangle2D shape = new RoundRectangle2D.Float(0.5f, 0.5f, w - 1f, h - 1f, ARC, ARC);
        g2.setClip(shape);

        Color bg = !enabled ? BG_DISABLED : (pressed ? BG_PRESSED : (hover ? BG_HOVER : BG));
        g2.setColor(bg);
        g2.fillRect(0, 0, w, h);

        int imageAreaH = h - LABEL_HEIGHT;

        if (image != null) {
            float alpha = enabled ? 1f : 0.35f;
            Graphics2D imgG2 = (Graphics2D) g2.create();
            imgG2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            drawCoverImage(imgG2, image, 0, 0, w, imageAreaH);
            imgG2.dispose();
        } else {
            g2.setColor(enabled ? PLACEHOLDER : BG_DISABLED.darker());
            g2.fillRect(0, 0, w, imageAreaH);
            g2.setColor(enabled ? Color.WHITE : TEXT_DISABLED);
            g2.setFont(getFont().deriveFont(java.awt.Font.BOLD, 22f));
            String initial = displayName.isEmpty() ? "?" : displayName.substring(0, 1).toUpperCase();
            java.awt.FontMetrics fm = g2.getFontMetrics();
            int tx = (w - fm.stringWidth(initial)) / 2;
            int ty = (imageAreaH + fm.getAscent() - fm.getDescent()) / 2;
            g2.drawString(initial, tx, ty);
        }

        g2.setClip(shape);
        g2.setColor(bg);
        g2.fillRect(0, imageAreaH, w, LABEL_HEIGHT);

        g2.setFont(getFont().deriveFont(java.awt.Font.PLAIN, 11.5f));
        g2.setColor(enabled ? TEXT : TEXT_DISABLED);
        java.awt.FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(displayName);
        String label = displayName;
        while (textWidth > w - 16 && label.length() > 1) {
            label = label.substring(0, label.length() - 2) + "…";
            textWidth = fm.stringWidth(label);
        }
        int tx = (w - textWidth) / 2;
        int ty = imageAreaH + (LABEL_HEIGHT + fm.getAscent() - fm.getDescent()) / 2;
        g2.drawString(label, tx, ty);

        paintOverlay(g2, w, imageAreaH);

        g2.setClip(null);
        g2.setStroke(new BasicStroke(1f));
        g2.setColor(BORDER);
        g2.draw(shape);

        g2.dispose();
    }

    protected void paintOverlay(Graphics2D g2, int width, int imageAreaHeight) {
        // hook pour les sous-classes — rien par défaut
    }

    private void drawCoverImage(Graphics2D g2, Image img, int x, int y, int targetW, int targetH) {
        int imgW = img.getWidth(null);
        int imgH = img.getHeight(null);
        if (imgW <= 0 || imgH <= 0)
            return;

        double targetRatio = (double) targetW / targetH;
        double imgRatio = (double) imgW / imgH;

        int srcW, srcH, srcX, srcY;
        if (imgRatio > targetRatio) {
            srcH = imgH;
            srcW = (int) (imgH * targetRatio);
            srcX = (imgW - srcW) / 2;
            srcY = 0;
        } else {
            srcW = imgW;
            srcH = (int) (imgW / targetRatio);
            srcX = 0;
            srcY = (imgH - srcH) / 2;
        }

        g2.drawImage(img, x, y, x + targetW, y + targetH, srcX, srcY, srcX + srcW, srcY + srcH, null);
    }

    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        setCursor(new Cursor(b ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));
        repaint();
    }
}