# AE2 UEL Addon - Quick Reference Card

## 🚀 Quick Start (5 minutes)

```bash
# 1. Clone project
git clone https://github.com/nikitosikvo2011-max/ae2-uel-addon-example.git
cd ae2-uel-addon-example

# 2. Setup workspace (first time only, takes 5-10 minutes)
./gradlew setupDecompWorkspace

# 3. Build JAR
./gradlew build

# Output: build/libs/ae2-uel-addon-1.0.0.jar
```

## 📦 Build Output

After `./gradlew build`, find your JARs in `build/libs/`:

- **ae2-uel-addon-1.0.0.jar** - Main mod JAR (install in mods folder)
- **ae2-uel-addon-1.0.0-api.jar** - API JAR (for other mods to depend on)
- **ae2-uel-addon-1.0.0-sources.jar** - Source code JAR

## 🎮 Run in Development

```bash
# Run Minecraft with mod loaded
./gradlew runClient

# Run server
./gradlew runServer

# Run with debugging
./gradlew runClient --debug-jvm
```

## ⚙️ Configuration

### Project Properties

Edit `gradle.properties`:

```properties
modName = AE2 UEL Addon Example
modId = ae2ueladdon
modGroup = com.example.ae2uel
modVersion = 1.0.0
minecraftVersion = 1.12.2
```

### Runtime Config

After first run, edit `config/ae2ueladdon.cfg`:

```properties
debug.enabled = false
ae2.integration.enabled = true
ae2.crafting.max_tasks = 64
```

## 📝 Common Commands

```bash
# Build everything
./gradlew build

# Clean build
./gradlew clean build

# Run tests
./gradlew test

# Setup IDE
./gradlew idea      # IntelliJ IDEA
./gradlew eclipse   # Eclipse

# View available tasks
./gradlew tasks
```

## 🔧 Customizing

### Add a New Package

```bash
mkdir -p src/main/java/com/example/ae2uel/yourpackage
```

### Create a New Class

1. Create file: `src/main/java/com/example/ae2uel/yourpackage/YourClass.java`
2. Add code
3. Rebuild: `./gradlew build`

## 📖 Documentation

| File | Purpose |
|------|----------|
| [README.md](README.md) | Full project documentation |
| [DEVELOPMENT.md](DEVELOPMENT.md) | Development guide |
| [UPDATE_GUIDE.md](UPDATE_GUIDE.md) | How to update for new AE2 versions |
| [CHANGELOG.md](CHANGELOG.md) | Version history |
| [CONTRIBUTING.md](CONTRIBUTING.md) | How to contribute |

## ❌ Troubleshooting

### "Command not found: gradlew"

```bash
# On Linux/Mac
chmod +x gradlew

# Then try again
./gradlew build
```

### "Could not find AE2"

```bash
./gradlew build --refresh-dependencies
```

### Gradle daemon issues

```bash
./gradlew --stop
./gradlew build
```

### OutOfMemory error

Edit `gradle.properties`:

```properties
org.gradle.jvmargs = -Xmx4G
```

## 📍 Project Structure

```
ae2-uel-addon-example/
├── src/main/java/          Main source code
├── src/main/resources/      Configuration files
├── src/api/java/            Public API
├── build/                   Build output (generated)
├── run/                     Dev game directory (generated)
├── gradle/                  Gradle wrapper
├── build.gradle             Build configuration
├── gradle.properties        Project properties
└── README.md                Full documentation
```

## 🎯 Next Steps

1. ✅ Build project: `./gradlew build`
2. ✅ Read [DEVELOPMENT.md](DEVELOPMENT.md)
3. ✅ Start modifying code in `src/main/java/com/example/ae2uel/`
4. ✅ Rebuild and test: `./gradlew runClient`
5. ✅ Check [UPDATE_GUIDE.md](UPDATE_GUIDE.md) for new AE2 versions

## 🔗 Resources

- [Gradle Documentation](https://docs.gradle.org/)
- [Minecraft Forge Docs](https://docs.minecraftforge.net/)
- [AE2 UEL GitHub](https://github.com/AE2-UEL/Applied-Energistics-2)
- [AE2 API Reference](https://github.com/AppliedEnergistics/Applied-Energistics-2/blob/main/API.md)

## ✨ Key Features

✅ Production-ready architecture  
✅ AE2 UEL v0.56.7 (rv6-stable-7) compatible  
✅ Minecraft 1.12.2 with Forge  
✅ Modern Java syntax support (Jabel)  
✅ Complete documentation  
✅ Easy to extend and customize  
✅ Ready for publishing  

---

**Stuck?** Check [DEVELOPMENT.md](DEVELOPMENT.md) or open an issue on GitHub!
