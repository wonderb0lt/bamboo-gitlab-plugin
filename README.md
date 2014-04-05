# Bamboo GitLab Plugin

The Bamboo GitLab Plugin allows easy links from [Atlassian Bamboo](https://www.atlassian.com/software/bamboo) to repositories hosted in [GitLab](http://gitlab.org/).

[![Build Status](https://travis-ci.org/commercehub-oss/bamboo-gitlab-plugin.svg?branch=master)](https://travis-ci.org/commercehub-oss/bamboo-gitlab-plugin)
[![Project Status](http://stillmaintained.com/commercehub-oss/bamboo-gitlab-plugin.png)](http://stillmaintained.com/commercehub-oss/bamboo-gitlab-plugin)
[![Download](https://api.bintray.com/packages/commercehub-oss/main/bamboo-gitlab-plugin/images/download.png)](https://bintray.com/commercehub-oss/main/bamboo-gitlab-plugin/_latestVersion)

# Usage

After installing the plugin, go to the configuration of a Git repository for a build plan.  In the `Advanced options` section, select `GitLab` as the `Web repository`.  Enter a `Web Repository URL` and click `Save repository`.  Commits for that plan should now be properly linked.

# Caveats

Over time, the URLs used by GitLab may change.  This plugin has currently only been tested with GitLab 5.2.0.

# Building

The officially recommended way to develop plugins for Atlassian applications is to install the Atlassian Plugin SDK.  You can find information about it in the [SDK Development documentation](https://developer.atlassian.com/display/DOCS/Getting+Started).

Alternatively, you should be able to build this plugin with just [Maven](http://maven.apache.org/).  However, it doesn't appear that the Atlassian plugins are compatible with all versions of Maven.  As of this writing, it appears to work with version 3.0.5.
