#!/bin/bash
curl https://cli-assets.heroku.com/install.sh | sh;  #install heroku
docker login --username=$HEROKU_LOGIN --password=$HEROKU_API_KEY registry.heroku.com;  #login to registry.heroku.com
docker push registry.heroku.com/$HEROKU_APP/web;
heroku container:release web --app $HEROKU_APP
git tag -a v0.0.1 -m "Release v0.0.1"
git push