<!--
#
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
# 
# http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
#  KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
#
-->
# Release Notes

### 0.2.2 (Sept 25, 2013)
* CB-4889 bumping&resetting version
* [windows8] commandProxy was moved
* CB-4889
* CB-4889 renaming core inside windows8
* CB-4889 renaming org.apache.cordova.core.device-motion to org.apache.cordova.device-motion
* Rename CHANGELOG.md -> RELEASENOTES.md
* [CB-4752] Incremented plugin version on dev branch.

### 0.2.3 (Oct 28, 2013)
* tweak scoping
* fixed the scope
* properly stop watching...
* adding timestamp to the response
* fix acceleromter for firefox os
* update firefoxos integration
* fixed callbacks
* accelerometer registers, but is not responding
* fxos added, not working
* CB-5128: added repo + issue tag to plugin.xml for device motion
* CB-5012 ensure result is returned
* [CB-4825] Add CoreMotion.framework to plugin.xml
* [CB-4825] avoid retain cycle in update block
* [CB-4825] use CoreMotion framework for accelerometer
* [CB-4915] Incremented plugin version on dev branch.

### 0.2.4 (Dec 4, 2013)
* add ubuntu platform
* 1. Added amazon-fireos platform. 2. Change to use amazon-fireos as the platform if the user agent string contains 'cordova-amazon-fireos'

### 0.2.5 (Jan 02, 2014)
* CB-5658 Add doc/index.md for Device Motion plugin

### 0.2.6 (Feb 05, 2014)
* Add Tizen support

### 0.2.7 (Apr 17, 2014)
* CB-6422: [windows8] use cordova/exec/proxy
* CB-6460: Update license headers
* CB-6465: Add license headers to Tizen code
* Add NOTICE file

### 0.2.8 (Jun 05, 2014)
* CB-6127 Spanish and French Translations added. Github close #10. Github close #12. Github close #11
* ubuntu: don't destroy callback after use
* CB-6798 Add license
* CB-6491 add CONTRIBUTING.md
* FFOS added to supported platforms

### 0.2.9 (Aug 06, 2014)
* [FFOS] update accelerometer.js
* CB-6127 Updated translations for docs
* FFOS added to supported platforms



### 0.2.10 (Sep 17, 2014)
* CB-7471 cordova-plugin-device-motion documentation translation: cordova-plugin-device-motion
* Updated doc for browser
* Added support for the browser
* CB-7249 cordova-plugin-device-motion documentation translation
* [CB-7313] minor tweak to documentation of watchAcceleration function parameters
* CB-7160 move to tests dir, add nested plugin.xml
* Removed js-module for tests from plugin.xml
* CB-7160 added manual tests
* added documentation for manual tests
* Removed js-module for tests from plugin.xml
* CB-7160 added manual tests
* Changing cdvtest format to use module exports
* register tests using new style
* update
* Feature Branch: First attempt at new-style-tests

### 0.2.11 (Dec 02, 2014)
* CB-8083 Fix `accelerometer` callback on **Windows**
* Renamed **Windows8** -> **Windows**
* CB-7977 Mention `deviceready` in plugin docs
* CB-7700 cordova-plugin-device-motion documentation translation: cordova-plugin-device-motion
* CB-7571 Bump version of nested plugin to match parent plugin

### 1.0.0 (Apr 15, 2015)
* CB-8746 gave plugin major version bump
* CB-8683 updated windows and tizen specific references of old id to new id
* CB-8683 changed plugin-id to pacakge-name
* CB-8653 properly updated translated docs to use new id
* CB-8653 updated translated docs to use new id
* Use TRAVIS_BUILD_DIR, install paramedic by npm
* CB-8312 Multiply accelerometer values by -g on Windows
* CB-8653 Updated Readme
* CB-8562 Integrate TravisCI
* CB-8438 cordova-plugin-device-motion documentation translation: cordova-plugin-device-motion
* CB-8538 Added package.json file
* CB-8096 Pended recently added spec.12 if accelerometer doesn't exist on the device
* CB-8096 Pended auto tests if accelerometer doesn't exist on the device
* CB-8083 Adds test to make sure success callback is called each time

### 1.1.0 (May 06, 2015)
* CB-8926: The tests module tries to access an undefined global `Accelerometer` on fail callbacks.  This results in another JS error, `ReferenceError: 'Accelerometer' is undefined.`  This change passes through the error message instead of attempting to index into it.
* CB-8876 Introduced a small timeout between tests
* CB-8876 Rewrote **wp8** impementation to be more stable
