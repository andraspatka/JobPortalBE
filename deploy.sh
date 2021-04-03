#!/bin/bash
echo "Reverting modified files (mvnw, pom.xml)..."
git restore mvnw pom.xml
echo "Reverting files successful. Installing heroku CLI..."
curl https://cli-assets.heroku.com/install.sh | sh;  #install heroku
echo "Heroku CLI installed, logging into heroku registry..."
docker login --username=$HEROKU_LOGIN --password=$HEROKU_API_KEY registry.heroku.com;  #login to registry.heroku.com
echo "Login successful, pushing container to heroku container registry..."
docker push registry.heroku.com/$HEROKU_APP/web;
echo "Push successful, deploying..."
heroku container:release web --app $HEROKU_APP
echo "Deployment successful. Tagging release"
git tag -a v0.0.1 -m "Release v0.0.1"
git push