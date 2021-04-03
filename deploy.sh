#!/bin/bash
# Calculate new version, based on already existing git tags
# Sets the new version to the NEW_VERSION variable
function get_new_version() {
  release_type=$(echo $1 | tr A-Z a-z)
  if [[ ${release_type} != "major" && ${release_type} != "minor" && ${release_type} != "patch" ]]; then
      echo "Wrong release type: ${release_type}! Run the command as such: ./new_version.sh <release_type>"
      echo "Release type can be: major, minor, patch"
      exit 1
  fi

  num_tags=$(git tag | wc -w)
  if [[ ${num_tags} == 0 ]]; then
    NEW_VERSION="0.0.1"
    return 0
  fi

  tags=($(git tag)) # () -> convert to array
  latest_version=${tags[${#tags[@]}-1]} # alphabetical order, could cause problems: e.g. 1.1.0, 1.10.0, 1.2.0
  version_numbers=($(echo ${latest_version} | tr -d 'v' | tr '.' ' '))

  major_version=${version_numbers[0]}
  minor_version=${version_numbers[1]}
  patch_version=${version_numbers[2]}

  case ${release_type} in
      major)
          echo "Bumping up major version..."
          major_version=$((major_version + 1))
          NEW_VERSION="${major_version}.0.0"
      ;;
      minor)
          echo "Bumping up minor version..."
          minor_version=$((minor_version + 1))
          NEW_VERSION="${major_version}.${minor_version}.0"
      ;;
      patch)
          echo "Bumping up patch version..."
          patch_version=$((patch_version + 1))
          NEW_VERSION="${major_version}.${minor_version}.${patch_version}"
      ;;
  esac
  echo "New version: ${NEW_VERSION}"
}

echo "Calculating new version..."
get_new_version ${RELEASE_TYPE}

echo "Installing Heroku CLI"
curl https://cli-assets.heroku.com/install.sh | sh

./mvnw versions:set -DnewVersion="${NEW_VERSION}"

echo "Logging into Heroku container registry"
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
git tag -a v${NEW_VERSION} -m "Release v${NEW_VERSION}"
git push --tags https://${GITHUB_TOKEN}@github.com/${GITHUB_USER}/JobPortalBE.git ${TRAVIS_BRANCH}