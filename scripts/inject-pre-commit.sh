#!/usr/bin/env bash
echo "Injecting pre commit hook"
mkdir -p  ./.git/hooks/
cp ./scripts/pre-commit ./.git/hooks/
chmod +x ./.git/hooks/pre-commit