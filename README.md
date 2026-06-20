# 🎵 Funny Soundboard

A simple Java soundboard application built with **Java 21**, **Swing**, and **Maven**.

Funny Soundboard allows you to browse sound categories, play audio clips, adjust the volume, and stop currently playing sounds through a simple graphical interface.

---

## ✨ Features

* 📂 Hierarchical navigation through sound categories
* 🔊 Audio playback
* 🎚️ Volume control
* ⏹️ Stop currently playing sounds
* 🖼️ Automatic icon loading
* 📁 Dynamic soundboard generation from the resources folder
* 🔙 Navigation history with back button support

---

## 🛠️ Tech Stack

* Java 21
* Swing
* Maven

---

## 🚀 Getting Started

### Prerequisites

Make sure you have installed:

* Java 21
* Maven 3+

Verify your installation:

```bash
java --version
mvn --version
```

---

### Run the application

From the project root:

```bash
mvn compile exec:java -Dexec.mainClass="com.hedizair.soundboard.App"
```

---

## 📁 Project Structure

```text
src/
├── main/
│   ├── java/
│   │   └── com/hedizair/soundboard/
│   │       ├── App.java
│   │       ├── AppInitializer.java
│   │       └── model/
│   │           ├── core/
│   │           ├── ui/
│   │           └── util/
│   └── resources/
│       ├── icons/
│       └── sounds/
```

### Packages

| Package | Description                            |
| ------- | -------------------------------------- |
| `core`  | Domain models and soundboard structure |
| `ui`    | Swing user interface components        |
| `util`  | Utility classes and resource scanning  |

---

## ➕ Adding New Sounds

Funny Soundboard automatically generates its navigation tree from the content of the `resources` folder.

### Resource directories

```text
src/main/resources/
├── icons/
└── sounds/
```

Each subdirectory represents a navigation level inside the application.

Example:

```text
icons/
└── animals/
    └── domestic/

sounds/
└── animals/
    └── domestic/
```

This creates:

```text
Animals
└── Domestic
```

inside the application.

---

### Adding a Sound

To add a new sound:

1. Place the audio file inside the corresponding `sounds` folder.
2. Place the matching image inside the corresponding `icons` folder.
3. Give both files the same name (excluding extension).

Example:

```text
sounds/memes/get-out.wav
icons/memes/get-out.jpg
```

The application will automatically associate:

```text
get-out.wav ↔ get-out.jpg
```

---

### Supported Formats

#### Audio

```text
.wav
```

#### Images

```text
.jpg
.png
```

---

## 📸 Example Resource Layout

```text
resources/
├── icons/
│   ├── animals/
│   ├── memes/
│   └── basics/
│
└── sounds/
    ├── animals/
    ├── memes/
    └── basics/
```

Resulting navigation:

```text
Animals
├── Domestic
├── Farm
└── ...

Memes
├── Emotional Damage
├── Get Out
└── ...

Basics
├── Windows XP Startup
├── Error
└── ...
```

---

## 🏗️ Architecture Notes

The application is organized around three main concerns:

### Core

Contains the domain objects representing:

* Categories
* Sounds
* Icons
* Soundboard configuration

### UI

Contains all Swing components:

* Main window
* Navigation controller
* Buttons
* Audio controls
* Soundboard panels

### Resource Discovery

Resources are scanned automatically at startup to build the navigation tree dynamically.

No code changes are required when adding new categories or sounds.

---

## 🎯 Project Goal

This project was created as a learning exercise to practice:

* Java 21
* Swing UI development
* Event-driven programming
* Resource management
* Application architecture
* Maven project structure

---

## 📄 License

This project is provided for educational and personal use.
