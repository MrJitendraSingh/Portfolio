# Deployment Guide for GitHub Pages

This guide will help you deploy your Portfolio web application to GitHub Pages.

## Prerequisites

1. A GitHub account
2. Your repository pushed to GitHub
3. GitHub Actions enabled in your repository

## Setup Steps

### 1. Enable GitHub Pages

1. Go to your repository on GitHub
2. Click on **Settings** → **Pages** (in the left sidebar)
3. Under **Source**, select **"GitHub Actions"** instead of "Deploy from a branch"
4. Click **Save**

### 2. Push Your Code

The deployment workflow is already configured in `.github/workflows/deploy.yml`. Simply push your code to the `main` branch:

```bash
git add .
git commit -m "Setup GitHub Pages deployment"
git push origin main
```

### 3. Monitor Deployment

1. Go to the **Actions** tab in your GitHub repository
2. You should see the "Deploy Portfolio to GitHub Pages" workflow running
3. Wait for it to complete (usually takes 2-5 minutes)
4. Once complete, your site will be available at:
   - `https://[your-username].github.io/[repository-name]/`

### 4. Manual Deployment

You can also manually trigger deployment:
1. Go to **Actions** tab
2. Select "Deploy Portfolio to GitHub Pages" workflow
3. Click **"Run workflow"** button
4. Select the branch (usually `main`)
5. Click **"Run workflow"**

## Build Output

The production build is created in:
```
composeApp/build/dist/wasmJs/productionExecutable/
```

This directory contains:
- `index.html` - Main HTML file
- `composeApp.js` - JavaScript bundle
- `composeApp.wasm` - WebAssembly binary
- Other assets and resources

## Troubleshooting

### Build Fails

If the build fails:
1. Check the Actions tab for error messages
2. Ensure your `build.gradle.kts` is correctly configured
3. Verify all dependencies are available

### Site Not Loading

1. Check if GitHub Pages is enabled (Settings → Pages)
2. Verify the workflow completed successfully
3. Check the deployment URL format
4. Clear browser cache and try again

### Update Not Reflecting

1. Wait a few minutes for GitHub Pages to update
2. Hard refresh your browser (Ctrl+F5 or Cmd+Shift+R)
3. Check if the latest commit was pushed to `main` branch

## Custom Domain (Optional)

If you want to use a custom domain:
1. Add a `CNAME` file in the root with your domain name
2. Configure DNS settings as per GitHub Pages documentation
3. Update the workflow to preserve the CNAME file

## Local Testing

Before deploying, test the production build locally:

```bash
# Build production version
./gradlew :composeApp:wasmJsBrowserProductionWebpack

# Serve locally (requires a local server)
cd composeApp/build/dist/wasmJs/productionExecutable
python3 -m http.server 8000
# Or use any other static file server
```

Then visit `http://localhost:8000` in your browser.



