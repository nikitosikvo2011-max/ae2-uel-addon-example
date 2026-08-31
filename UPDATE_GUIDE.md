# AE2 UEL Addon - Update Guide

This guide explains how to update the addon for new versions of AE2 UEL and other dependencies.

## Table of Contents

1. [Quick Update Checklist](#quick-update-checklist)
2. [Updating AE2 UEL Version](#updating-ae2-uel-version)
3. [Updating Minecraft Version](#updating-minecraft-version)
4. [Updating Build System](#updating-build-system)
5. [Testing After Update](#testing-after-update)
6. [Common Issues](#common-issues)
7. [Version Compatibility Matrix](#version-compatibility-matrix)

## Quick Update Checklist

### For Minor AE2 Updates (patch versions)

- [ ] Check [AE2 UEL Releases](https://github.com/AE2-UEL/Applied-Energistics-2/releases)
- [ ] Update `build.gradle` dependency version
- [ ] Run `./gradlew setupDecompWorkspace --refresh-dependencies`
- [ ] Run `./gradlew build`
- [ ] Test with `./gradlew runClient`
- [ ] Update `CHANGELOG.md` with changes

### For Major AE2 Updates (feature/breaking changes)

- [ ] Review API changes in [AE2 UEL GitHub](https://github.com/AE2-UEL/Applied-Energistics-2)
- [ ] Update all points from "Minor" checklist
- [ ] Review `AE2Integration.java` for API compatibility
- [ ] Update Access Transformers in `ae2ueladdon_at.cfg` if needed
- [ ] Update any AE2 API calls in code
- [ ] Run full test suite: `./gradlew test`
- [ ] Create new branch for testing
- [ ] Document breaking changes in `CHANGELOG.md`

## Updating AE2 UEL Version

### Step 1: Find New Version

1. Visit [AE2 UEL CurseForge](https://www.curseforge.com/minecraft/mc-mods/ae2-extended-life)
2. Select desired version
3. Note the CurseForge project ID and file ID
4. Check [GitHub Releases](https://github.com/AE2-UEL/Applied-Energistics-2/releases) for API changes

### Step 2: Update gradle.properties

If Minecraft version changed, update:

```properties
minecraftVersion = 1.12.2  # or new version
```

Update version tags in `src/main/java/com/example/ae2uel/Tags.java`:

```java
public static final String AE2_UEL_VERSION = "rv6-stable-8";  // Updated version
```

### Step 3: Update build.gradle

Find the AE2 dependency line:

```gradle
// OLD:
compileOnly 'curse.maven:ae2-extended-life-570458:6302101'

// NEW:
compileOnly 'curse.maven:ae2-extended-life-570458:NEW_FILE_ID'
```

**Finding the file ID:**
1. Go to CurseForge page for desired version
2. Right-click version link and copy URL
3. File ID is the last number in the URL
4. Or: `https://www.curseforge.com/minecraft/mc-mods/ae2-extended-life/files/FILE_ID`

### Step 4: Check API Changes

Compare old and new versions:

```bash
# Clone latest AE2 UEL
git clone https://github.com/AE2-UEL/Applied-Energistics-2.git
cd Applied-Energistics-2

# Check git log for API changes
git log --oneline --grep="API" --since="2 months ago"

# Or view specific commit
git show <commit_hash>
```

### Step 5: Update Integration Code

Update `src/main/java/com/example/ae2uel/integration/AE2Integration.java`:

```java
public class AE2Integration {
    // Update this comment with new version
    /**
     * Current AE2 UEL Version: rv6-stable-8
     * Last updated: 2026-09-01
     */
    
    public static void initialize() {
        LOGGER.info("Initializing AE2 UEL integration...");
        try {
            // Add new API calls here if API changed
            // See: https://github.com/AE2-UEL/Applied-Energistics-2/blob/master/API.md
            
            LOGGER.info("AE2 UEL integration initialized successfully");
        } catch (Exception e) {
            LOGGER.error("Failed to initialize AE2 UEL integration", e);
        }
    }
}
```

### Step 6: Update Access Transformers (if needed)

If class structure changed, update `src/main/resources/ae2ueladdon_at.cfg`:

```cfg
# Example: If appeng.api.AEApi method changed visibility
public appeng.api.AEApi field_INSTANCE
```

## Updating Minecraft Version

### Prerequisites

- Minecraft version must have Forge available
- AE2 UEL must support that version
- Build system must support that Java version

### Step 1: Update gradle.properties

```properties
minecraftVersion = 1.16.5  # New version
```

### Step 2: Update build.gradle

Check if RetroFuturaGradle supports new version:

```gradle
plugins {
    id 'com.gtnewhorizons.retrofuturagradle' version '1.4.9+'
}
```

Check [RetroFuturaGradle Releases](https://github.com/GTNewHorizons/RetroFuturaGradle/releases).

### Step 3: Update Dependencies

Update all dependencies to versions supporting new MC:

```gradle
dependencies {
    // Update versions
    compileOnly 'curse.maven:ae2-extended-life-570458:NEW_VERSION'
    implementation 'mezz.jei:jei_1.16.5:...'  // Update version
}
```

### Step 4: Verify Java Compatibility

Check if new MC version requires different Java:

```gradle
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))  // or 16, 17, etc.
    }
}
```

Common versions:
- MC 1.12.2 → Java 8
- MC 1.16.5 → Java 8+
- MC 1.17+ → Java 16+
- MC 1.20.4+ → Java 21+

### Step 5: Clean Build

```bash
# Clean everything
./gradlew clean

# Regenerate workspace
./gradlew setupDecompWorkspace --refresh-dependencies

# Rebuild
./gradlew build
```

## Updating Build System

### Updating Gradle Wrapper

```bash
# Check current version
./gradlew --version

# Update to new version
./gradlew wrapper --gradle-version 8.0
```

### Updating RetroFuturaGradle

Edit `build.gradle`:

```gradle
plugins {
    id 'com.gtnewhorizons.retrofuturagradle' version '1.5.0'  // New version
}
```

Then:

```bash
./gradlew setupDecompWorkspace --refresh-dependencies
```

### Updating Buildscript

The project uses GTNewHorizons buildscript. To update:

```bash
# Update automatically
./gradlew updateBuildScript

# Or manually download from
# https://github.com/GregTechCEu/Buildscripts/blob/master/build.gradle
```

## Testing After Update

### Unit Tests

```bash
# Run all tests
./gradlew test

# Run specific test
./gradlew test --tests com.example.ae2uel.integration.AE2IntegrationTest

# Run with output
./gradlew test --info
```

### Integration Testing

```bash
# Run client in development
./gradlew runClient

# In-game checklist:
# [ ] Mod loads without errors
# [ ] No crash on startup
# [ ] AE2 UEL is detected and working
# [ ] All custom items/blocks work (if any)
# [ ] Configuration loads correctly
# [ ] No console warnings/errors
```

### Server Testing

```bash
# Run server
./gradlew runServer

# Connect with client and verify:
# [ ] Server starts without errors
# [ ] Players can join
# [ ] Mod content works on server
# [ ] Data saves/loads correctly
```

### Build Verification

```bash
# Verify JAR
./gradlew build

# Check output
ls -lh build/libs/

# Expected files:
# - ae2-uel-addon-1.0.0.jar (main)
# - ae2-uel-addon-1.0.0-api.jar (API)
# - ae2-uel-addon-1.0.0-sources.jar (sources)
```

## Common Issues

### Issue: "Could not find AE2 UEL classes"

**Cause:** AE2 UEL not downloaded or dependency incorrect

**Solution:**

```bash
# Refresh dependencies
./gradlew build --refresh-dependencies

# Or manually clear cache
rm -rf ~/.gradle/caches/
./gradlew setupDecompWorkspace
```

### Issue: "Class not found: appeng.api.AEApi"

**Cause:** API jar missing or not in classpath

**Solution:**

1. Check dependency in `build.gradle`:
   ```gradle
   compileOnly 'curse.maven:ae2-extended-life-570458:...'
   ```

2. Ensure it's `compileOnly` not `implementation`

3. Verify CurseForge file ID is correct

### Issue: "Access Transformer errors"

**Cause:** Class structure changed in AE2

**Solution:**

1. Check what class failed
2. Verify it still exists in new AE2 version
3. Update or remove from `ae2ueladdon_at.cfg`
4. Check class path is correct: `appeng.package.ClassName`

### Issue: "Version mismatch in @Mod annotation"

**Cause:** Gradle didn't inject version tokens

**Solution:**

```bash
# Clean and rebuild
./gradlew clean build

# Check Tags.java was generated:
grep "public static final String VERSION" src/main/java/com/example/ae2uel/Tags.java
```

### Issue: "Compilation fails with Java syntax errors"

**Cause:** Java version too old for code features

**Solution:**

1. Check `enableModernJavaSyntax` in `gradle.properties`:
   ```properties
   enableModernJavaSyntax = true
   ```

2. Ensure Java 17+ is available for compilation

3. Or use Java 8 features only

## Version Compatibility Matrix

| AE2 UEL Version | Minecraft | Forge | Java | Status |
|---|---|---|---|---|
| rv6-stable-7 | 1.12.2 | 14.23.5.2860+ | 8+ | ✅ Current |
| rv6-stable-6 | 1.12.2 | 14.23.5.2860+ | 8+ | ✅ Supported |
| rv6-stable-5 | 1.12.2 | 14.23.5.2860+ | 8+ | ⚠️ Outdated |
| rv6-stable-8 | 1.12.2 | 14.23.5.2860+ | 8+ | 📅 Planned |

**Note:** Always check [AE2 UEL Releases](https://github.com/AE2-UEL/Applied-Energistics-2/releases) for actual version numbers.

## API Reference Updates

When major API changes occur, key files to review:

- **appeng.api.AEApi** - Main API entry point
  - Location: `src/api/java/appeng/api/AEApi.java`
  - Key methods: `instance()`, `storage()`, `crafting()`

- **appeng.api.storage** - Storage system
  - `IStorageGrid` - Main storage interface
  - `IStorageCell` - Storage cell interface
  - `IStorageMedium` - Storage medium interface

- **appeng.api.crafting** - Crafting system
  - `ICraftingGrid` - Crafting grid interface
  - `ICraftingJob` - Crafting job interface
  - `IPatternDetails` - Pattern interface

- **appeng.api.parts** - Grid parts
  - `IPartHost` - Part host interface
  - `IPart` - Base part interface
  - `PartItemStack` - Part item stack

- **appeng.api.networking** - Networking
  - `IGridNode` - Grid node interface
  - `IGridStorage` - Grid storage interface

## Support and Resources

- [AE2 UEL GitHub Issues](https://github.com/AE2-UEL/Applied-Energistics-2/issues)
- [AE2 API Documentation](https://github.com/AppliedEnergistics/Applied-Energistics-2/blob/main/API.md)
- [Forge Documentation](https://docs.minecraftforge.net/)
- [Gradle Documentation](https://docs.gradle.org/)

## Getting Help

If you encounter issues:

1. Check this guide and [CHANGELOG.md](CHANGELOG.md)
2. Search [GitHub Issues](https://github.com/nikitosikvo2011-max/ae2-uel-addon-example/issues)
3. Review [AE2 UEL Issues](https://github.com/AE2-UEL/Applied-Energistics-2/issues)
4. Ask in Minecraft Forge community forums
5. Check Minecraft Modding Discord servers

---

**Last Updated:** 2026-08-31  
**Compatible With:** AE2 UEL rv6-stable-7 (Minecraft 1.12.2)
