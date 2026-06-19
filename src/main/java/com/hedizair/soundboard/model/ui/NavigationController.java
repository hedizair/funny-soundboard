package com.hedizair.soundboard.model.ui;

import java.util.Stack;

import javax.swing.JPanel;

public class NavigationController {

    private MainWindow mainWindow;
    private Stack<SoundboardPanel> navigationStack;

    public NavigationController(MainWindow mainWindow, SoundboardPanel rootPanel) {
        this.mainWindow = mainWindow;
        this.navigationStack = new Stack<>();
        this.navigationStack.push(rootPanel);
        for (SoundboardComponent component : rootPanel.getSoundBoardComponents()) {
            if (component instanceof CategoryButton) {
                ((CategoryButton) component).setNavigationController(this);
            }
        }
    }

    public void navigate(SoundboardPanel targetPanel) {
        // Logic to navigate to the specified category
        for (SoundboardComponent component : targetPanel.getSoundBoardComponents()) {
            if (component instanceof CategoryButton) {
                ((CategoryButton) component).setNavigationController(this);
            }
        }
        navigationStack.push(targetPanel);
        mainWindow.changePanel(targetPanel);
    }

    public void reset() {
        // Logic to navigate back to the main menu
        if (!navigationStack.isEmpty()) {
            SoundboardPanel rootPanel = navigationStack.getFirst();
            navigationStack.clear();
            navigationStack.push(rootPanel);
            mainWindow.changePanel(rootPanel);

        }
    }

    public void back() {
        // Logic to navigate back to the previous screen
        System.out.println("Back button pressed");
        if (navigationStack.size() > 1) {
            System.out.println("Navigating back to previous screen");
            navigationStack.pop();
            mainWindow.changePanel(navigationStack.peek());
        }
    }
}
