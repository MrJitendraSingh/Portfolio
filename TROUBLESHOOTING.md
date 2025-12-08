# Troubleshooting GitHub Pages Deployment

If you're having issues with deployment, follow these steps:

## Common Issues and Solutions

### 1. Workflow Not Running

**Problem:** The workflow doesn't start when you push to GitHub.

**Solutions:**
- Check if the workflow file is in `.github/workflows/deploy.yml`
- Ensure you're pushing to the `main` branch (or update the workflow to use your branch name)
- Go to **Actions** tab and check if the workflow appears
- Make sure GitHub Actions is enabled in your repository settings

### 2. Build Fails

**Problem:** The Gradle build fails with an error.

**Solutions:**
- Check the Actions tab for the specific error message
- Common issues:
  - **Task not found:** The task name might be different. Check available tasks by running locally:
    ```bash
    ./gradlew tasks --all | grep wasm
    ```
  - **Dependency issues:** Make sure all dependencies in `gradle/libs.versions.toml` are correct
  - **Java version:** The workflow uses Java 17, ensure your project is compatible

### 3. Build Output Not Found

**Problem:** The workflow can't find the build output directory.

**Solutions:**
- The build output location might be different. Check locally:
  ```bash
  ./gradlew :composeApp:wasmJsBrowserProductionWebpack
  find composeApp/build -name "index.html"
  ```
- Update the `OUTPUT_DIR` paths in the workflow if your output is in a different location
- Check the workflow logs in the Actions tab to see where it's searching

### 4. GitHub Pages Not Enabled

**Problem:** The site doesn't deploy even though the workflow succeeds.

**Solutions:**
1. Go to **Settings** → **Pages** in your repository
2. Under **Source**, make sure **"GitHub Actions"** is selected (NOT "Deploy from a branch")
3. Click **Save**
4. Wait a few minutes for the first deployment

### 5. Permission Errors

**Problem:** Workflow fails with permission errors.

**Solutions:**
- Go to **Settings** → **Actions** → **General**
- Under **Workflow permissions**, select **"Read and write permissions"**
- Check **"Allow GitHub Actions to create and approve pull requests"**
- Click **Save**

### 6. Site Shows 404

**Problem:** The site URL shows a 404 error.

**Solutions:**
- Wait 5-10 minutes after deployment completes (GitHub Pages takes time to update)
- Check the correct URL format: `https://[username].github.io/[repository-name]/`
- Make sure the repository name matches exactly
- Clear browser cache and try again
- Check if the workflow completed successfully in the Actions tab

### 7. Wrong Task Name

**Problem:** The Gradle task name is incorrect.

**Solutions:**
1. Check available tasks locally:
   ```bash
   ./gradlew tasks --all | grep -i "wasm\|browser\|production"
   ```
2. Common task names:
   - `wasmJsBrowserProductionWebpack`
   - `wasmJsBrowserProductionDistribution`
   - `wasmJsBrowserProductionExecutable`
3. Update the workflow file with the correct task name

## Manual Testing

Before deploying, test the build locally:

```bash
# Build production version
./gradlew :composeApp:wasmJsBrowserProductionWebpack

# Find the output
find composeApp/build -name "index.html"

# Serve locally to test
cd composeApp/build/dist/wasmJs/productionExecutable
python3 -m http.server 8000
# Then visit http://localhost:8000
```

## Getting Help

1. **Check Workflow Logs:**
   - Go to **Actions** tab
   - Click on the failed workflow run
   - Expand each step to see detailed logs
   - Look for error messages

2. **Check Build Output:**
   - The workflow logs show where it's searching for files
   - Compare with your local build output location

3. **Verify Configuration:**
   - Ensure `.github/workflows/deploy.yml` is correct
   - Check that `build.gradle.kts` has wasmJs target configured
   - Verify all required files are committed to git

## Alternative: Use JS Target Instead

If wasmJs continues to have issues, you can use the JS target:

1. Update the workflow to use:
   ```yaml
   ./gradlew :composeApp:jsBrowserProductionWebpack
   ```

2. Update the output directory search to:
   ```yaml
   composeApp/build/dist/js/productionExecutable
   ```

Note: JS builds are larger and slower, but more compatible with older browsers.



