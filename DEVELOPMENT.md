# ============================================================
# AE2 UEL Addon Example - Development Guide
# ============================================================

This file provides quick reference for developing the addon.

## Quick Start

```bash
# 1. Setup workspace (do this once)
./gradlew setupDecompWorkspace

# 2. Build the project
./gradlew build

# 3. Run in development
./gradlew runClient      # Client
./gradlew runServer      # Server
```

## Project Structure

```
src/
├── main/
│   ├── java/com/example/ae2uel/     # Main source code
│   │   ├── AE2UELAddon.java          # Entry point (@Mod)
│   │   ├── registry/                 # Item/Block/Recipe registration
│   │   ├── integration/              # AE2 integration
│   │   ├── event/                    # Event handlers
│   │   ├── config/                   # Configuration
│   │   ├── util/                     # Utilities
│   │   └── api/                      # Public API classes
│   └── resources/
│       ├── mcmod.info                # Mod metadata
│       ├── ae2ueladdon.cfg           # Configuration
│       └── ae2ueladdon_at.cfg        # Access Transformers
├── api/
│   └── java/com/example/ae2uel/api/  # Public API
└── test/
    └── java/com/example/ae2uel/      # Test classes

build/
└── libs/                              # Build output (JAR files)

run/                                   # Development game directory
└── [created after first run]
```

## Common Tasks

### Building

```bash
# Full build
./gradlew build

# Clean build
./gradlew clean build

# Build with tests
./gradlew build

# Only compile (no JAR)
./gradlew classes

# Generate only sources JAR
./gradlew sourcesJar

# Generate only API JAR
./gradlew apiJar
```

### Development

```bash
# Run client
./gradlew runClient

# Run server
./gradlew runServer

# Run with debug port 5005
./gradlew runClient --debug-jvm
```

### Testing

```bash
# Run all tests
./gradlew test

# Run specific test
./gradlew test --tests TestClassName

# Run tests with output
./gradlew test --info
```

### IDE Setup

```bash
# IntelliJ IDEA
./gradlew idea

# Eclipse
./gradlew eclipse

# Clean IDE files
./gradlew cleanIdea cleanEclipse
```

## Configuration Files

### gradle.properties

Project properties:
- `modName` - Display name
- `modId` - Unique identifier (lowercase)
- `modGroup` - Package group
- `modVersion` - Version string
- `minecraftVersion` - Target MC version

### build.gradle

Build configuration:
- Plugins and versions
- Dependencies
- Source sets
- Compilation options
- JAR building

### dependencies.gradle

Custom dependencies. Add your libraries here.

## Adding Code

### Adding a New Class

1. Create file in `src/main/java/com/example/ae2uel/yourpackage/YourClass.java`
2. Follow naming conventions: PascalCase for classes
3. Add Javadoc comments
4. Rebuild: `./gradlew build`

### Adding a New Package

1. Create directory: `src/main/java/com/example/ae2uel/newpackage/`
2. Add classes to directory
3. Import in your code
4. Rebuild

## Debugging

### Enable Debug Logging

Edit `src/main/resources/ae2ueladdon.cfg`:

```properties
debug.enabled=true
```

Or in code:

```java
AE2UELAddon.LOGGER.debug("Debug message: {}", variable);
```

### Debug Client

```bash
./gradlew runClient --debug-jvm
```

Then attach debugger to port 5005 in IDE.

### View Logs

```bash
# Development
run/logs/latest.log

# Or
tail -f run/logs/latest.log
```

## Common Issues

### "Could not resolve dependency"

**Solution:**

```bash
./gradlew build --refresh-dependencies
```

### Gradle daemon crashes

**Solution:**

```bash
./gradlew --stop
./gradlew build
```

### OutOfMemory during build

**Solution:** Edit `gradle.properties`:

```properties
org.gradle.jvmargs = -Xmx4G
```

### IDE doesn't recognize imports

**Solution:**

```bash
# IntelliJ
./gradlew cleanIdea idea

# Eclipse
./gradlew cleanEclipse eclipse
```

## Version Control

### .gitignore

Already configured. Don't commit:
- `.gradle/` - Build cache
- `build/` - Build output
- `.idea/` - IDE settings
- `run/` - Development game
- `*.class` - Compiled files

### Commits

Good commit messages:

```
add: Add new feature X
fix: Fix bug in module Y
refactor: Reorganize package Z
update: Update AE2 integration for v1.5
```

## Publishing

### To Maven Local

```bash
./gradlew publishToMavenLocal
```

Then in other projects:

```gradle
dependencies {
    implementation 'com.example.ae2uel:ae2-uel-addon:1.0.0:api'
}
```

### To CurseForge

```bash
export CURSEFORGE_API_KEY=your_token
export CURSEFORGE_PROJECT_ID=your_project_id
./gradlew curseforge
```

### To Modrinth

```bash
export MODRINTH_API_KEY=your_token
export MODRINTH_PROJECT_ID=your_project_id
./gradlew modrinth
```

## Performance Tips

### Faster Builds

```bash
# Parallel builds
./gradlew build --parallel

# Skip tests
./gradlew build -x test

# Use daemon (faster between builds)
./gradlew build --daemon
```

### IDE Performance

- Disable unnecessary plugins
- Increase IDE heap size
- Index excluded directories (gradle, build)
- Use "Offline Mode" in Gradle settings

## Resources

- [Gradle Documentation](https://docs.gradle.org/)
- [Forge Documentation](https://docs.minecraftforge.net/)
- [GTNewHorizons Buildscript](https://github.com/GregTechCEu/Buildscripts)
- [RetroFuturaGradle](https://github.com/GTNewHorizons/RetroFuturaGradle)

## Getting Help

1. Check error messages carefully
2. Search GitHub issues
3. Check Forge documentation
4. Ask in Minecraft Forge Discord

---

**For detailed update instructions, see [UPDATE_GUIDE.md](UPDATE_GUIDE.md)**
