
# 🔗 Node Connectivity Challenge

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-blue.svg)](https://kotlinlang.org/)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

A modern Android application built with Kotlin and Jetpack Compose to explore and display the top 100 most connected nodes in the Lightning Network, based on real-time data fetched from a public API.

---

## 📱 Features

- **Lightning Node Explorer**: View a ranked list of nodes based on connectivity
- **Realtime Data**: Fetched from [mempool.space](https://mempool.space)
- **Location Fallback**: Displays node location using pt-BR or en labels
- **Bitcoin Conversion**: Capacity converted from sats to BTC
- **Date Formatting**: Human-readable timestamps from Unix
- **Error and Loading Handling**: Clear UI states for errors and loading
- **Jetpack Compose UI**: Modern and declarative UI toolkit
- **Test Coverage**: Unit tests for core business logic

---

## 🛠️ Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Architecture**: MVVM + Clean Architecture
- **State Management**: ViewModel + StateFlow
- **Networking**: [Ktor Client](https://ktor.io/)
- **Testing**: JUnit, MockK

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or newer
- Android SDK 34
- JDK 17
- Gradle 8.0+

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/wterroni/nodesChallenge.git
   cd nodesChallenge
   ```

2. Open the project in Android Studio and run the app on a device or emulator.

---

## 🏗️ Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/nodes/
│   │   │   ├── di/              # Dependency Injection modules
│   │   │   ├── domain/          # Business logic
│   │   │   ├── data/            # Networking and models
│   │   │   ├── presentation/    # UI layer
│   │   │   └── utils/           # Date formatting, conversions
│   │   └── res/                 # Resources (UI strings, themes)
│   ├── test/                    # Unit tests
│   └── androidTest/            # Instrumented tests
```

---

## 🧪 Testing

- **Unit Tests**: Core business logic (conversions, formatting, etc.)
- **Tools**: JUnit 5, MockK

---

## 📄 License

This project is licensed under the Apache 2.0 License - see the [LICENSE](LICENSE) file for details.
