package com.hedizair.soundboard.model.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;

import javax.swing.JComponent;

public class VolumeSlider extends JComponent {
    private static final int WIDTH = 120;
    private static final int HEIGHT = 36;
    private static final int TRACK_HEIGHT = 4;
    private static final int THUMB_SIZE = 14;
    private static final int ICON_AREA = 26;

    private static final Color TRACK_BG = new Color(225, 225, 229);
    private static final Color TRACK_FILL = new Color(90, 90, 98);
    private static final Color THUMB = new Color(255, 255, 255);
    private static final Color THUMB_BORDER = new Color(140, 140, 148);
    private static final Color ICON_COLOR = new Color(90, 90, 98);

    private int min;
    private int max;
    private int value;
    private final List<IntConsumer> listeners = new ArrayList<>();

    public VolumeSlider(int min, int max, int initial) {
        this.min = min;
        this.max = max;
        this.value = initial;

        setPreferredSize(new java.awt.Dimension(WIDTH, HEIGHT));
        setMinimumSize(new java.awt.Dimension(WIDTH, HEIGHT));
        setMaximumSize(new java.awt.Dimension(WIDTH, HEIGHT));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        MouseAdapter dragHandler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                updateFromMouse(e.getX());
            }
        };
        addMouseListener(dragHandler);
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                updateFromMouse(e.getX());
            }
        });
    }

    private void updateFromMouse(int mouseX) {
        int trackStart = ICON_AREA;
        int trackWidth = getWidth() - ICON_AREA - THUMB_SIZE / 2 - 4;
        float ratio = (mouseX - trackStart) / (float) trackWidth;
        ratio = Math.max(0f, Math.min(1f, ratio));
        int newValue = Math.round(min + ratio * (max - min));
        if (newValue != value) {
            value = newValue;
            repaint();
            notifyListeners();
        }
    }

    public void addChangeListener(IntConsumer listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (IntConsumer l : listeners) {
            l.accept(value);
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int h = getHeight();
        int trackY = h / 2 - TRACK_HEIGHT / 2;
        int trackStart = ICON_AREA;
        int trackWidth = getWidth() - ICON_AREA - THUMB_SIZE / 2 - 4;

        drawVolumeIcon(g2, h);

        RoundRectangle2D track = new RoundRectangle2D.Float(trackStart, trackY, trackWidth, TRACK_HEIGHT, TRACK_HEIGHT,
                TRACK_HEIGHT);
        g2.setColor(TRACK_BG);
        g2.fill(track);

        float ratio = (value - min) / (float) (max - min);
        int fillWidth = Math.round(trackWidth * ratio);
        if (fillWidth > 0) {
            RoundRectangle2D fill = new RoundRectangle2D.Float(trackStart, trackY, fillWidth, TRACK_HEIGHT,
                    TRACK_HEIGHT, TRACK_HEIGHT);
            g2.setColor(TRACK_FILL);
            g2.fill(fill);
        }

        int thumbX = trackStart + fillWidth - THUMB_SIZE / 2;
        int thumbY = h / 2 - THUMB_SIZE / 2;
        Ellipse2D thumb = new Ellipse2D.Float(thumbX, thumbY, THUMB_SIZE, THUMB_SIZE);
        g2.setColor(THUMB);
        g2.fill(thumb);
        g2.setStroke(new BasicStroke(1.5f));
        g2.setColor(THUMB_BORDER);
        g2.draw(thumb);

        g2.dispose();
    }

    private void drawVolumeIcon(Graphics2D g2, int h) {
        int cx = ICON_AREA / 2;
        int cy = h / 2;
        g2.setColor(ICON_COLOR);
        g2.setStroke(new BasicStroke(1.8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        g2.fillRect(cx - 7, cy - 3, 4, 6);
        int[] xs = { cx - 3, cx - 3, cx + 2 };
        int[] ys = { cy - 3, cy + 3, cy };
        g2.fillPolygon(new int[] { cx - 3, cx - 3, cx + 1 }, new int[] { cy - 5, cy + 5, cy }, 3);

        if (value > 0) {
            g2.drawArc(cx + 2, cy - 5, 7, 10, -45, 90);
        }
        if (value > max / 2) {
            g2.drawArc(cx + 4, cy - 8, 10, 16, -45, 90);
        }
    }
}