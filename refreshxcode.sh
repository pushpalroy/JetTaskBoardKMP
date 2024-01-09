#!/bin/bash

# Run the Gradle task
./gradlew :shared:generateDummyFramework

# Move into the iosApp directory
cd iosApp

# Install and update pods
pod install
pod update

# End of script
