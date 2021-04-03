#!/bin/bash
echo "Installing Heroku CLI"
curl https://cli-assets.heroku.com/install.sh | sh
echo "Heroku CLI installed"

COMMITS_SINCE_MAIN=$(git rev-list --count HEAD)
TAG_NAME="${TRAVIS_BRANCH}-m.${COMMITS_SINCE_MAIN}"
VERSION_NAME="0.0.1-${TAG_NAME}"
echo "Version ${VERSION_NAME}"
./mvnw versions:set -DnewVersion="${VERSION_NAME}"

echo "Logging into Heroku"
heroku container:login

# Build and push docker image
echo "Building and pushing docker image with JIB"
./mvnw compile jib:build

echo "Deploying..."
heroku container:release web --app $HEROKU_APP

echo "Deployment successful. Tagging release"
git status
git restore pom.xml mvnw
git checkout ${TRAVIS_BRANCH}
git tag -a v0.0.1 -m "Release v${VERSION_NAME}"
git push https://${GITHUB_TOKEN}@github.com/${GITHUB-USER}/JobPortalBE.git ${TRAVIS_BRANCH}