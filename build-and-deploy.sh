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
  "composeApp/build/dist/wasmJs/productionExecutable" \
  "composeApp/build/dist/wasmJs/production" \
  "composeApp/build/dist/wasmJs" \
  "composeApp/build/wasmJs/productionExecutable" \
  "composeApp/build/wasmJs/production"
do
  if [ -d "$dir" ] && [ -f "$dir/index.html" ] 2>/dev/null; then
    OUTPUT_DIR="$dir"
    echo "✅ Found build output at: $OUTPUT_DIR"
    break
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

