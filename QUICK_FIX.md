# Quick Fix Guide for GitHub Pages Deployment

## Step-by-Step Fix

### Step 1: Check What's Wrong

1. Go to your GitHub repository
2. Click on the **Actions** tab
3. Look for the workflow run (it should be named "Deploy Portfolio to GitHub Pages")
4. Click on it to see what failed

### Step 2: Common Fixes

#### If the workflow doesn't appear:
- Make sure the file `.github/workflows/deploy.yml` exists in your repository
- Check that you've pushed it to GitHub
- Go to **Settings** → **Actions** → **General** and ensure "Allow all actions and reusable workflows" is enabled

#### If the build fails:
- Check the error message in the Actions tab
- The most common issue is the Gradle task name
- Try using the simpler workflow: rename `.github/workflows/deploy-simple.yml` to `.github/workflows/deploy.yml` (backup the old one first)

#### If "Build output not found":
- The build might be in a different location
- Check the workflow logs to see where it's searching
- Update the workflow with the correct path

#### If GitHub Pages shows 404:
1. Go to **Settings** → **Pages**
2. Make sure **"GitHub Actions"** is selected (NOT "Deploy from a branch")
3. Click **Save**
4. Wait 5-10 minutes
5. Check the URL: `https://[your-username].github.io/[repository-name]/`

### Step 3: Use the Simple Workflow

I've created a simpler workflow file. To use it:

1. Delete or rename the current workflow:
   ```bash
   mv .github/workflows/deploy.yml .github/workflows/deploy.yml.backup
   ```

2. Use the simple one:
   ```bash
   mv .github/workflows/deploy-simple.yml .github/workflows/deploy.yml
   ```

3. Commit and push:
   ```bash
   git add .github/workflows/
   git commit -m "Use simpler deployment workflow"
   git push origin main
   ```

### Step 4: Manual Check

Test the build locally first:

```bash
# Build the production version
./gradlew :composeApp:wasmJsBrowserProductionWebpack

# Find where the output is
find composeApp/build -name "index.html"

# The path shown is where your build output is
```

Then update the workflow if the path is different.

### Step 5: Still Not Working?

1. **Check the exact error:**
   - Go to Actions tab
   - Click on the failed workflow
   - Expand each step to see the error

2. **Try JS target instead:**
   - Change `wasmJs` to `js` in the workflow
   - Change `wasmJsBrowserProductionWebpack` to `jsBrowserProductionWebpack`
   - Update output path to `composeApp/build/dist/js/productionExecutable`

3. **Check permissions:**
   - Settings → Actions → General
   - Workflow permissions: "Read and write permissions"
   - Save

## Need More Help?

Share the error message from the Actions tab, and I can help you fix it specifically!



