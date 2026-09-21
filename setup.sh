#!/bin/bash

set -u

GOINFRE="/goinfre/$USER"
NVM_DIR="$GOINFRE/.nvm"
PNPM_HOME="$GOINFRE/pnpm"
MAVEN_REPO="$GOINFRE/maven-repo"
GRADLE_HOME="$GOINFRE/gradle"

echo "=============================================="
echo "  42 Spring Starter - environment setup"
echo "=============================================="

mkdir -p "$GOINFRE" "$MAVEN_REPO" "$PNPM_HOME" "$GRADLE_HOME"

if [ -n "${NPM_CONFIG_PREFIX:-}" ]; then
    unset NPM_CONFIG_PREFIX
fi

if [ ! -s "$NVM_DIR/nvm.sh" ]; then
    echo "Installing nvm..."
    git clone --depth 1 https://github.com/nvm-sh/nvm.git "$NVM_DIR"
else
    echo "nvm already installed."
fi

export NVM_DIR
# shellcheck disable=SC1090
. "$NVM_DIR/nvm.sh"

if nvm ls 24 2>/dev/null | grep -q "v24"; then
    echo "Node 24 already installed."
else
    echo "Installing Node 24..."
    nvm install 24
fi

nvm use 24 >/dev/null 2>&1 || true
nvm alias default 24 >/dev/null 2>&1 || true

export PNPM_HOME
export PATH="$PNPM_HOME:$PATH"

if ! command -v pnpm >/dev/null 2>&1; then
    echo "Installing pnpm..."
    curl -fsSL https://get.pnpm.io/install.sh | env PNPM_HOME="$PNPM_HOME" SHELL="$(command -v zsh 2>/dev/null || command -v bash)" sh -
fi

mkdir -p "$GOINFRE/pnpm-store"

cat > "$HOME/.m2/settings.xml" <<EOF
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">
  <localRepository>$MAVEN_REPO</localRepository>
</settings>
EOF

cat > "$GOINFRE/42-java-env.sh" <<EOF
export MAVEN_OPTS="-Dmaven.repo.local=$MAVEN_REPO"
export GRADLE_USER_HOME="$GRADLE_HOME"
export PNPM_HOME="$PNPM_HOME"
export PATH="\$PNPM_HOME:\$PATH"
EOF

echo ""
echo "Setup complete."
echo "Add this to ~/.zshrc if desired:"
echo "source $GOINFRE/42-java-env.sh"
echo ""
echo "Then run: make up"
