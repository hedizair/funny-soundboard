package com.hedizair.soundboard.model.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

public class NavigationController {

    private MainWindow window;
    private Stack<SoundboardPanel> navigationStack;
    private List<NavigationListener> listeners = new ArrayList<>();

    public NavigationController() {
        this.navigationStack = new Stack<>();
    }

    public void navigate(SoundboardPanel targetPanel) {
        if (window == null) {
            return;
        }
        navigationStack.push(targetPanel);
        emit(NavigationListener::onNavigate);
        window.changePanel(targetPanel);
    }

    public void reset() {

        if (!navigationStack.isEmpty() && window != null) {
            SoundboardPanel rootPanel = navigationStack.getFirst();
            navigationStack.clear();
            navigationStack.push(rootPanel);
            emit(NavigationListener::onNavigate);
            window.changePanel(rootPanel);

        }
    }

    public void back() {
        // Logic to navigate back to the previous screen
        if (navigationStack.size() > 1 && window != null) {
            navigationStack.pop();
            emit(NavigationListener::onNavigate);
            window.changePanel(navigationStack.peek());
        }
    }

    public void setwindow(MainWindow window) {
        this.window = window;
        this.navigationStack.clear();
        this.navigationStack.push(window.getCurrentPanel());

    }

    public boolean isAtRoot() {
        if (this.navigationStack.size() == 1) {
            return true;
        }
        return false;
    }

    public void addListener(NavigationListener listener) {
        listeners.add(listener);
    }

    private void emit(Consumer<NavigationListener> action) {
        for (NavigationListener listener : listeners) {
            action.accept(listener);
        }
    }

}
