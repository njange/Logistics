#!/usr/bin/env pwsh
<#
This script creates GitHub issues from markdown files in `.github/ISSUES/` using the GitHub CLI `gh`.

Prerequisites:
- Install GitHub CLI: https://cli.github.com/
- Authenticate: `gh auth login`

Usage:
pwsh .\scripts\create_github_issues.ps1
#>

$issueDir = Join-Path $PSScriptRoot "..\.github\ISSUES"
if (-not (Test-Path $issueDir)) {
    Write-Error "Issue directory not found: $issueDir"
    exit 1
}

Get-ChildItem -Path $issueDir -Filter "*.md" | Sort-Object Name | ForEach-Object {
    $file = $_.FullName
    $lines = Get-Content -Path $file
    if ($lines.Length -eq 0) { return }
    # Expect first line to be '# Title'
    $titleLine = $lines[0]
    $title = $titleLine -replace '^#\s*', ''

    $body = (Get-Content -Path $file -Raw)

    Write-Host "Creating issue: $title"
    gh issue create --title "$title" --body "$body"
}
