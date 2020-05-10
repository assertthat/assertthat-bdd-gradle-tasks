#!/usr/bin/env bash
if [ "$TRAVIS_BRANCH" = 'master' ] && [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
    ./gradlew uploadArchives -Psigning.gnupg.executable=gpg -Psigning.gnupg.useLegacyGpg=true -Psigning.gnupg.keyName=$GPG_KEY_NAME -Psigning.gnupg.passphrase=$GPG_PASSPHRASE
fi