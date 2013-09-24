# Bamboo GitLab Plugin

The Bamboo GitLab Plugin allow easy links from [Atlassian Bamboo](https://www.atlassian.com/software/bamboo) to repositories hosted in [GitLab](http://gitlab.org/).

# Usage

After installing the plugin, go to the configuration of a Git repository for a build plan.  In the `Advanced options` section, select `GitLab` as the `Web repository`.  Enter a `Web Repository URL` and click `Save repository`.  Commits for that plan should now be properly linked.

# Caveats

Over time, the URLs used by GitLab may change.  This plugin has currently only been tested with GitLab 5.2.0.
