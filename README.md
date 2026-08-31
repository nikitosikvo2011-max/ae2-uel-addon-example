# AE2 UEL Addon Example

A complete, well-structured addon for **AE2 Unofficial Extended Life (v0.56.7+)** demonstrating best practices for mod development with full integration capabilities.

## Features

✅ **Production-Ready Architecture**
- Clean separation of concerns (Registry, Integration, Config, Events, API)
- Extensible package structure for easy feature additions
- Comprehensive logging and error handling
- API package for other mods to depend on

✅ **Full AE2 UEL Integration**
- Compatible with AE2 UEL rv6-stable-7 (Minecraft 1.12.2)
- Easy to update for future AE2 versions
- Access Transformers for protected class members
- Event handling system for AE2 hooks

✅ **Developer-Friendly**
- Detailed code comments explaining purpose and usage
- Configuration system ready for customization
- Test infrastructure with JUnit 5
- IDE configuration for IntelliJ IDEA

✅ **Modern Java Development**
- Java 8 bytecode with modern syntax support (via Jabel)
- Gradle build system with GTNewHorizons buildscript
- Automated asset generation
- Maven publishing ready

## Project Structure

```
ae2-uel-addon-example/
├── src/
│   ├── main/
│   │   ├── java/com/example/ae2uel/
│   │   │   ├── AE2UELAddon.java           # Main entry point
│   │   │   ├── Tags.java                  # Auto-generated version tags
│   │   │   ├── registry/
│   │   │   │   └── RegistryManager.java   # Item/Block/Recipe registration
│   │   │   ├── integration/
│   │   │   │   └── AE2Integration.java    # AE2 UEL API integration
│   │   │   ├── event/
│   │   │   │   ├── EventHandler.java      # Main event handler
│   │   │   │   ├── ServerTickHandler.java
│   │   │   │   └── PlayerEventHandler.java
│   │   │   ├── config/
│   │   │   │   └── ConfigManager.java     # Configuration management
│   │   │   ├── util/
│   │   │   │   ├── Log.java               # Logging utilities
│   │   │   │   └── CompatibilityChecker.java
│   │   │   └── api/
│   │   │       └── AE2UELAddonAPI.java    # Public API for other mods
│   │   └── resources/
│   │       ├── mcmod.info                 # Mod metadata
│   │       ├── pack.mcmeta                # Resource pack metadata
│   │       ├── ae2ueladdon.cfg            # Configuration file
│   │       └── ae2ueladdon_at.cfg         # Access Transformers
│   ├── api/
│   │   └── java/com/example/ae2uel/api/   # Public API classes
│   └── test/
│       └── java/com/example/ae2uel/       # Test classes
├── gradle/
│   └── wrapper/                           # Gradle wrapper files
├── build.gradle                           # Main build configuration
├── gradle.properties                      # Project properties
├── settings.gradle                        # Gradle settings
├── dependencies.gradle                    # Dependency management
└── README.md                              # This file
```

## Getting Started

### Prerequisites

- Java 8+ (JDK 17 recommended for modern syntax support)
- Gradle 8.0+
- Git

### Building the Project

1. **Clone and setup workspace:**
   ```bash
   git clone https://github.com/nikitosikvo2011-max/ae2-uel-addon-example.git
   cd ae2-uel-addon-example
   ./gradlew setupDecompWorkspace
   ```

2. **Build the JAR:**
   ```bash
   ./gradlew build
   ```

3. **JAR Output:**
   - Main JAR: `build/libs/ae2-uel-addon-1.0.0.jar`
   - API JAR: `build/libs/ae2-uel-addon-1.0.0-api.jar`
   - Sources JAR: `build/libs/ae2-uel-addon-1.0.0-sources.jar`

### Development Setup

#### IntelliJ IDEA

1. Open project folder
2. Gradle should auto-detect and configure
3. Use provided run configurations:
   - `1. Setup Workspace` - Run once to decompile Minecraft
   - `2. Run Client` - Start modded Minecraft client
   - `3. Run Server` - Start modded Minecraft server
   - `4. Build Jars` - Compile and package

#### Eclipse

```bash
./gradlew eclipse
# Then import project into Eclipse
```

## Configuration

### gradle.properties

Key properties for customization:

```properties
modName = Your Mod Name
modId = your_mod_id
modGroup = com.example.yourmod
modVersion = 1.0.0
minecraftVersion = 1.12.2
```

### Runtime Configuration

Edit `config/ae2ueladdon.cfg` after first run:

```properties
debug.enabled=false
ae2.integration.enabled=true
ae2.crafting.max_tasks=64
```

## Adding Features

### Adding a New Item

1. Create class in `src/main/java/com/example/ae2uel/item/`:

```java
public class CustomItem extends Item {
    public CustomItem() {
        super();
        this.setUnlocalizedName("custom_item");
    }
}
```

2. Register in `RegistryManager.registerItems()`:

```java
public static void registerItems() {
    GameRegistry.register(new CustomItem().setRegistryName("custom_item"));
}
```

### Adding AE2 Integration

1. Edit `AE2Integration.java`
2. Implement API calls in `initialize()` method
3. Reference: [AE2 API Documentation](https://github.com/AppliedEnergistics/Applied-Energistics-2/blob/rv2/API.md)

### Adding Event Handlers

1. Create handler class:

```java
public class CustomEventHandler {
    @SubscribeEvent
    public void onEvent(SomeEvent event) {
        // Handle event
    }
}
```

2. Register in `EventHandler.registerEventHandlers()`:

```java
MinecraftForge.EVENT_BUS.register(new CustomEventHandler());
```

## Updating for New AE2 UEL Versions

### Version Update Checklist

1. **Update gradle.properties:**
   ```properties
   minecraftVersion = 1.12.2  # Update if needed
   ```

2. **Update dependency in build.gradle:**
   - Find CurseForge project ID for new AE2 UEL version
   - Update curse.maven dependency

3. **Check API Changes:**
   - Visit [AE2 UEL GitHub](https://github.com/AE2-UEL/Applied-Energistics-2)
   - Review commit history for API breaking changes
   - Update `AE2Integration.java` and other integration points

4. **Update Access Transformers:**
   - If AE2 structure changed, update `ae2ueladdon_at.cfg`

5. **Test Thoroughly:**
   ```bash
   ./gradlew setupDecompWorkspace
   ./gradlew runClient
   ```

### Common AE2 API Updates

When updating AE2 versions, check these areas:

- **appeng.api.AEApi** - Main API entry point
- **appeng.api.storage** - Storage interfaces
- **appeng.api.crafting** - Crafting system
- **appeng.api.parts** - Grid parts
- **appeng.api.cells** - Storage cells

See `src/main/java/com/example/ae2uel/integration/AE2Integration.java` for integration points.

## Dependencies

### Required
- **AE2 Unofficial Extended Life** v0.56.7+ (rv6-stable-7)

### Optional
- **JEI (Just Enough Items)** for recipe display
- **TheOneProbe** for debug information

### Build Dependencies
- Minecraft Forge (handled by Gradle)
- RetroFuturaGradle buildscript
- GTNewHorizons buildscript

## Publishing

### To CurseForge

1. Set environment variable:
   ```bash
   export CURSEFORGE_API_KEY=your_api_token
   export CURSEFORGE_PROJECT_ID=your_project_id
   ```

2. Update `gradle.properties`:
   ```properties
   curseForgeProjectId = your_project_id
   ```

3. Publish:
   ```bash
   ./gradlew curseforge
   ```

### To Modrinth

1. Set environment variable:
   ```bash
   export MODRINTH_API_KEY=your_api_token
   export MODRINTH_PROJECT_ID=your_project_id
   ```

2. Update `gradle.properties`:
   ```properties
   modrinthProjectId = your_project_id
   ```

3. Publish:
   ```bash
   ./gradlew modrinth
   ```

## Troubleshooting

### Build Fails with "Could not find appeng classes"

**Solution:** Ensure `setupDecompWorkspace` completed successfully:
```bash
./gradlew setupDecompWorkspace --refresh-dependencies
```

### Mod doesn't load in-game

**Check:**
1. Verify AE2 UEL is installed
2. Check logs in `logs/latest.log`
3. Ensure `modid` is correct in `@Mod` annotation
4. Verify dependency string in `@Mod` annotation

### IDE doesn't recognize imports

**Solution:**
1. Regenerate IDE settings:
   ```bash
   ./gradlew cleanIdea idea  # IntelliJ
   ./gradlew cleanEclipse eclipse  # Eclipse
   ```
2. Refresh/reindex project

## Contributing

To contribute improvements:

1. Fork the repository
2. Create a feature branch
3. Make changes with clear commit messages
4. Submit a pull request

## License

This addon template is provided as-is. When creating your own addon, ensure you comply with:
- **AE2 UEL License** (LGPLv3)
- **AE2 API License** (MIT)
- Any licenses of dependencies used

## Support & Documentation

### Resources

- [AE2 UEL GitHub](https://github.com/AE2-UEL/Applied-Energistics-2)
- [AE2 UEL CurseForge](https://www.curseforge.com/minecraft/mc-mods/ae2-extended-life)
- [Minecraft Forge Documentation](https://docs.minecraftforge.net/)
- [Gradle Documentation](https://docs.gradle.org/)

### Getting Help

- Check existing GitHub issues
- Review AE2 UEL wiki
- Ask in Minecraft Forge community forums

## Changelog

See [CHANGELOG.md](CHANGELOG.md) for version history and updates.

## Version History

- **1.0.0** (2026-08-31) - Initial release
  - Complete project structure
  - AE2 UEL v0.56.7 (rv6-stable-7) compatibility
  - Full documentation and examples
  - Ready for extension

---

**Made with ❤️ for the AE2 community**
