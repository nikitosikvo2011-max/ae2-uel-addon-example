# ============================================================
# AE2 UEL Addon - Installation Instructions
# ============================================================

## For Users (Running the Addon)

### Prerequisites

- Minecraft 1.12.2
- Minecraft Forge (recommended: 14.23.5.2860+)
- AE2 Unofficial Extended Life (v0.56.7+)
- Java 8 or higher

### Installation Steps

1. **Download the JAR**
   - Get `ae2-uel-addon-1.0.0.jar` from [Releases](https://github.com/nikitosikvo2011-max/ae2-uel-addon-example/releases)
   - Or build it yourself (see below)

2. **Locate Mods Folder**
   - Windows: `%APPDATA%/.minecraft/mods/`
   - Linux: `~/.minecraft/mods/`
   - macOS: `~/Library/Application Support/minecraft/mods/`
   - MultiMC: `Instance Folder/mods/`

3. **Copy JAR**
   - Place `ae2-uel-addon-1.0.0.jar` in the mods folder

4. **Verify Installation**
   - Check `config/ae2ueladdon.cfg` was created
   - Start game and check for mod in mod list
   - Verify no error messages in logs

5. **Configure (Optional)**
   - Edit `config/ae2ueladdon.cfg`
   - Change settings as needed
   - Restart game for changes to take effect

## For Developers (Building from Source)

### Prerequisites

- Java Development Kit 8+ (17 recommended)
  - Download: [Adoptium OpenJDK](https://adoptium.net/)
  - Or: [Eclipse Temurin](https://projects.eclipse.org/projects/adoptium.temurin)
- Git
- ~10 GB free disk space (for Minecraft decompilation)

### Building Steps

#### Option 1: Command Line

```bash
# 1. Clone repository
git clone https://github.com/nikitosikvo2011-max/ae2-uel-addon-example.git
cd ae2-uel-addon-example

# 2. Setup workspace (first time only, ~5-10 minutes)
./gradlew setupDecompWorkspace

# 3. Build JAR
./gradlew build

# 4. Find output
ls -lh build/libs/
# Output:
# ae2-uel-addon-1.0.0.jar
# ae2-uel-addon-1.0.0-api.jar
# ae2-uel-addon-1.0.0-sources.jar
```

#### Option 2: IntelliJ IDEA

```bash
# 1. Open project
# File -> Open -> select project folder

# 2. Setup workspace
# Gradle panel -> Tasks -> Other -> setupDecompWorkspace
# Or: Run configuration "1. Setup Workspace"

# 3. Build
# Build -> Build Project
# Or: Gradle panel -> Tasks -> build -> build
```

#### Option 3: Eclipse

```bash
# 1. Setup workspace
./gradlew setupDecompWorkspace
./gradlew eclipse

# 2. Import into Eclipse
# File -> Import -> Existing Projects into Workspace

# 3. Build
# Right-click project -> Gradle -> Gradle Build
# Or: Run Gradle Tasks -> build
```

### Build Output Locations

**Main JAR** (use in mods folder):
```
build/libs/ae2-uel-addon-1.0.0.jar
```

**API JAR** (for other mods to depend on):
```
build/libs/ae2-uel-addon-1.0.0-api.jar
```

**Sources JAR** (for IDE source attachment):
```
build/libs/ae2-uel-addon-1.0.0-sources.jar
```

**Reobfuscated JAR** (obfuscated production build):
```
build/libs/ae2-uel-addon-1.0.0.jar (if running in normal Minecraft)
```

## Testing the Build

### Option 1: Development Run

```bash
# Start Minecraft with mod loaded in dev environment
./gradlew runClient

# Verify:
# - No crash on startup
# - Mod appears in mod list
# - No error messages in logs (logs/latest.log)
```

### Option 2: Production JAR

```bash
# 1. Build production JAR
./gradlew build

# 2. Copy to mods folder
cp build/libs/ae2-uel-addon-1.0.0.jar ~/.minecraft/mods/

# 3. Start Minecraft normally
# 4. Check logs for errors
```

## Publishing JAR

### To Maven Local

```bash
./gradlew publishToMavenLocal
```

Then use in other projects:

```gradle
dependencies {
    implementation 'com.example.ae2uel:ae2-uel-addon:1.0.0:api'
}
```

### To CurseForge

1. Create project on CurseForge
2. Get Project ID and API token
3. Set environment variables:
   ```bash
   export CURSEFORGE_API_KEY="your_token"
   export CURSEFORGE_PROJECT_ID="your_project_id"
   ```
4. Publish:
   ```bash
   ./gradlew curseforge
   ```

### To Modrinth

1. Create project on Modrinth
2. Get Project ID and API token
3. Set environment variables:
   ```bash
   export MODRINTH_API_KEY="your_token"
   export MODRINTH_PROJECT_ID="your_project_id"
   ```
4. Publish:
   ```bash
   ./gradlew modrinth
   ```

## Troubleshooting

### Issue: "Gradle is not recognized"

**Windows:**
```cmd
# Use gradlew.bat instead
gradlew.bat build
```

**Linux/Mac:**
```bash
# Make gradlew executable
chmod +x gradlew
./gradlew build
```

### Issue: "Could not find AE2"

```bash
# Refresh dependencies
./gradlew build --refresh-dependencies

# Or clear cache and rebuild
rm -rf ~/.gradle/caches/
./gradlew setupDecompWorkspace
```

### Issue: OutOfMemory during build

Edit `gradle.properties`:

```properties
org.gradle.jvmargs = -Xmx4G
```

### Issue: Workspace not decompiling

```bash
# Clean and retry
./gradlew clean setupDecompWorkspace --refresh-dependencies
```

### Issue: IDE doesn't see sources

```bash
# Regenerate IDE files
./gradlew cleanIdea idea      # IntelliJ
./gradlew cleanEclipse eclipse # Eclipse
```

## Verification

### Check Build Success

```bash
./gradlew build
# Should end with: BUILD SUCCESSFUL in XXs
```

### Verify JAR Contents

```bash
# List JAR contents (requires unzip or 7zip)
unzip -l build/libs/ae2-uel-addon-1.0.0.jar | head -20

# Should show:
# - mcmod.info
# - com/example/ae2uel/AE2UELAddon.class
# - META-INF/MANIFEST.MF
```

### Check manifest

```bash
unzip -p build/libs/ae2-uel-addon-1.0.0.jar META-INF/MANIFEST.MF
```

Should show:
```
Manifest-Version: 1.0
Implementation-Title: AE2 UEL Addon Example
Implementation-Version: 1.0.0
Implementation-Vendor: com.example.ae2uel
```

## Next Steps

1. ✅ Build successful - See [DEVELOPMENT.md](DEVELOPMENT.md)
2. ✅ Want to extend - See [CONTRIBUTING.md](CONTRIBUTING.md)
3. ✅ Update for new AE2 - See [UPDATE_GUIDE.md](UPDATE_GUIDE.md)
4. ✅ Full reference - See [README.md](README.md)

---

**Questions?** Check [QUICKSTART.md](QUICKSTART.md) or open an issue!
