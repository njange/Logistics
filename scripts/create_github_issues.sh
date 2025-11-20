#!/usr/bin/env bash
# Create GitHub issues from markdown files in .github/ISSUES using gh CLI
# Prereqs: gh CLI installed and authenticated (gh auth login)

ISSUE_DIR="$(dirname "$0")/../.github/ISSUES"
if [ ! -d "$ISSUE_DIR" ]; then
  echo "Issue directory not found: $ISSUE_DIR"
  exit 1
fi

for file in "$ISSUE_DIR"/*.md; do
  [ -e "$file" ] || continue
  # title is first line starting with '# '
  title=$(head -n 1 "$file" | sed -E 's/^#\s*//')
  echo "Creating issue: $title"
  gh issue create --title "$title" --body-file "$file"
done
