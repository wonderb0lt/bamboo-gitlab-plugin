# Bamboo GitLab Plugin

The Bamboo GitLab Plugin allows easy links from [Atlassian Bamboo](https://www.atlassian.com/software/bamboo) to repositories hosted in [GitLab](http://gitlab.org/).

[![Build Status](https://travis-ci.org/wonderb0lt/bamboo-gitlab-plugin.svg?branch=master)](https://travis-ci.org/wonderb0lt/bamboo-gitlab-plugin)

# Installation

I can't be bothered to set up the Atlassian Marketplace, so download the [latest version](https://github.com/wonderb0lt/bamboo-gitlab-plugin/releases/download/v1.0
.1/gitlab-1.0.1.jar) of this plugin and upload it under `Add-Ons > Upload add-on`

# Configuration

After installing the plugin, go to the configuration of a Git repository for a build plan.  In the `Advanced options` section,
select `GitLab` as the `Web repository`.  Enter a `Web Repository URL` and click `Save repository`.  An example URL may look like so:
http://gitlab.example.com/group/project. Save, and the commits for that plan should now be properly linked.

# Caveats

Over time, the URLs used by GitLab may change.  This plugin has only been tested with 6.7.5.

# Building

Pretty standard: mvn package and _TADA_, you've got an uploadable JAR. If you feel this should/could go on the Atlassian Marketplace, knock yourself out,
change the POM. Pull requests are welcome!
