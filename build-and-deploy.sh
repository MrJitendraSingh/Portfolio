#!/bin/bash

# Script to build wasmJs production webpack and copy to docs directory
# Usage: ./build-and-deploy.sh

set -e

echo "🔨 Building wasmJs production webpack..."
./gradlew :composeApp:wasmJsBrowserProductionWebpack

echo "🔍 Finding build output directory..."
OUTPUT_DIR=""

# Try different possible output locations
for dir in \
  "composeApp/build/kotlin-webpack/wasmJs/productionExecutable" \
  "composeApp/build/dist/wasmJs/productionExecutable" \
  "composeApp/build/dist/wasmJs/production" \
  "composeApp/build/dist/wasmJs" \
  "composeApp/build/wasmJs/productionExecutable" \
  "composeApp/build/wasmJs/production"
do
  if [ -d "$dir" ] 2>/dev/null; then
    # Check if directory has wasm/js files (index.html might be in processedResources)
    WASM_COUNT=$(find "$dir" -maxdepth 1 -name "*.wasm" -type f 2>/dev/null | wc -l)
    JS_COUNT=$(find "$dir" -maxdepth 1 -name "*.js" -type f 2>/dev/null | wc -l)
    HAS_HTML=$([ -f "$dir/index.html" ] 2>/dev/null && echo "1" || echo "0")
    
    if [ "$WASM_COUNT" -gt 0 ] || [ "$JS_COUNT" -gt 0 ] || [ "$HAS_HTML" = "1" ]; then
      OUTPUT_DIR="$dir"
      echo "✅ Found build output at: $OUTPUT_DIR"
      echo "  - WASM files: $WASM_COUNT"
      echo "  - JS files: $JS_COUNT"
      echo "  - Has index.html: $HAS_HTML"
      break
    fi
  fi
done

if [ -z "$OUTPUT_DIR" ]; then
  echo "❌ Build output not found. Searching for HTML files:"
  find composeApp/build -name "*.html" -type f 2>/dev/null | head -5
  echo ""
  echo "❌ Error: Could not find build output directory"
  exit 1
fi

echo "📋 Contents of build output:"
ls -la "$OUTPUT_DIR"

# Copy index.html from processedResources if it doesn't exist in output dir
if [ ! -f "$OUTPUT_DIR/index.html" ]; then
  echo "index.html not found in output directory, looking for it..."
  HTML_SOURCE=$(find composeApp/build/processedResources/wasmJs/main -name "index.html" 2>/dev/null | head -1)
  if [ -n "$HTML_SOURCE" ]; then
    echo "Copying index.html from $HTML_SOURCE"
    cp "$HTML_SOURCE" "$OUTPUT_DIR/"
  fi
fi

# Copy styles.css if it doesn't exist
if [ ! -f "$OUTPUT_DIR/styles.css" ]; then
  CSS_SOURCE=$(find composeApp/build/processedResources/wasmJs/main -name "styles.css" 2>/dev/null | head -1)
  if [ -n "$CSS_SOURCE" ]; then
    echo "Copying styles.css from $CSS_SOURCE"
    cp "$CSS_SOURCE" "$OUTPUT_DIR/"
  fi
fi

echo "📦 Copying files to docs directory..."
rm -rf docs/*
cp -r "$OUTPUT_DIR"/* docs/

echo "✅ Files copied successfully!"
echo "📁 Docs directory now contains:"
ls -la docs/

echo ""
echo "✨ Done! You can now commit and push the docs directory:"
echo "   git add docs/"
echo "   git commit -m 'Update docs with latest build'"
echo "   git push"

