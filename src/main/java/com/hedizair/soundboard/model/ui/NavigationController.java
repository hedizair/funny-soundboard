package com.hedizair.soundboard.model.ui;

import java.util.Stack;

public class NavigationController {

    private MainWindow window;
    private Stack<SoundboardPanel> navigationStack;

    public NavigationController() {
        this.navigationStack = new Stack<>();
    }

    public void navigate(SoundboardPanel targetPanel) {
        if (window == null) {
            System.out.println("window is not set. Cannot navigate.");
            return;
        }
        navigationStack.push(targetPanel);
        window.changePanel(targetPanel);
    }

    public void reset() {

        if (!navigationStack.isEmpty() && window != null) {
            SoundboardPanel rootPanel = navigationStack.getFirst();
            navigationStack.clear();
            navigationStack.push(rootPanel);
            window.changePanel(rootPanel);

        }
    }

    public void back() {
        // Logic to navigate back to the previous screen
        System.out.println("Back button pressed");
        if (navigationStack.size() > 1 && window != null) {
            System.out.println("Navigating back to previous screen");
            navigationStack.pop();
            window.changePanel(navigationStack.peek());
        }
    }

    public void setwindow(MainWindow window) {
        this.window = window;
        this.navigationStack.clear();
        this.navigationStack.push(window.getCurrentPanel());

    }

}
