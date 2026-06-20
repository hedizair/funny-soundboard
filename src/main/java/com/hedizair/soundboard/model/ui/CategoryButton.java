package com.hedizair.soundboard.model.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

public class CategoryButton extends SoundboardButton {
    private static final Color BADGE_BG = new Color(60, 60, 66);
    private static final Color BADGE_ARROW = Color.WHITE;
    private static final int BADGE_SIZE = 22;

    private String categoryName;
    private SoundboardPanel targetPanel;

    public CategoryButton(String categoryName, String iconFilePath, SoundboardPanel targetPanel,
            NavigationController navigationController) {
        super(categoryName, iconFilePath);
        this.categoryName = categoryName;
        this.targetPanel = targetPanel;
        this.addActionListener(e -> {
            if (navigationController != null) {
                navigationController.navigate(targetPanel);
            }
        });
    }

    @Override
    protected void paintOverlay(Graphics2D g2, int width, int imageAreaHeight) {
        Graphics2D badgeG2 = (Graphics2D) g2.create();
        badgeG2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int margin = 6;
        float x = width - BADGE_SIZE - margin;
        float y = margin;

        Ellipse2D circle = new Ellipse2D.Float(x, y, BADGE_SIZE, BADGE_SIZE);
        badgeG2.setColor(BADGE_BG);
        badgeG2.fill(circle);

        float cx = x + BADGE_SIZE / 2f;
        float cy = y + BADGE_SIZE / 2f;
        float arrowW = 7f;
        float arrowH = 8f;

        Path2D arrow = new Path2D.Float();
        arrow.moveTo(cx - arrowW / 2, cy - arrowH / 2);
        arrow.lineTo(cx + arrowW / 2, cy);
        arrow.lineTo(cx - arrowW / 2, cy + arrowH / 2);

        badgeG2.setColor(BADGE_ARROW);
        badgeG2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        badgeG2.draw(arrow);

        badgeG2.dispose();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public SoundboardPanel getTargetPanel() {
        return targetPanel;
    }
}