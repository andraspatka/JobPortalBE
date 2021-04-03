#!/bin/bash
echo "Installing Heroku CLI"
curl https://cli-assets.heroku.com/install.sh | sh
echo "Heroku CLI installed"

# Build and push docker image
echo "Building and pushing docker image with JIB"
./mvnw compile jib:build

echo "Deploying..."
heroku container:release web --app $HEROKU_APP

echo "Deployment successful. Tagging release"
git tag -a v0.0.1 -m "Release v0.0.1"
git push