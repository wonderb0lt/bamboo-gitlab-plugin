package com.commercehub.bamboo.plugins.gitlab;

import com.atlassian.bamboo.commit.Commit;
import com.atlassian.bamboo.commit.CommitFile;
import com.atlassian.bamboo.repository.RepositoryData;
import com.atlassian.bamboo.utils.error.ErrorCollection;
import com.atlassian.bamboo.webrepository.DefaultWebRepositoryViewer;
import com.atlassian.bamboo.ww2.actions.build.admin.create.BuildConfiguration;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.opensymphony.util.UrlUtils;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class GitLabWebRepositoryViewer extends DefaultWebRepositoryViewer {
    private static final String ATLASSIAN_GIT_PLUGIN_KEY = "com.atlassian.bamboo.plugins.atlassian-bamboo-plugin-git:git";
    private static final String WEB_REPOSITORY_URL = "webRepositoryUrl";

    private String webRepositoryUrl = "";

    @Override
    public void populateFromConfig(@NotNull HierarchicalConfiguration config) {
        super.populateFromConfig(config);
        webRepositoryUrl = config.getString(WEB_REPOSITORY_URL, "");
    }

    @NotNull
    @Override
    public HierarchicalConfiguration toConfiguration() {
        HierarchicalConfiguration config = super.toConfiguration();
        config.setProperty(WEB_REPOSITORY_URL, webRepositoryUrl);
        return config;
    }

    @NotNull
    @Override
    public ErrorCollection validate(@NotNull BuildConfiguration buildConfiguration) {
        ErrorCollection errors = super.validate(buildConfiguration);
        String url = buildConfiguration.getString(WEB_REPOSITORY_URL, "");
        if (url.isEmpty()) {
            errors.addError("webRepositoryUrl", "This field is required");
        } else if (!UrlUtils.verifyHierachicalURI(url)) {
            errors.addError("webRepositoryUrl", "This is not a valid URL");
        }
        return errors;
    }

    @Override
    public String getWebRepositoryUrl() {
        return webRepositoryUrl;
    }

    @Override
    public void setWebRepositoryUrl(String webRepositoryUrl) {
        this.webRepositoryUrl = Strings.nullToEmpty(webRepositoryUrl);
    }

    @Nullable
    @Override
    public String getWebRepositoryUrlForFile(@NotNull CommitFile file, RepositoryData repositoryData) {
        return urlConcat(getWebRepositoryUrl(), "blob/master/", file.getName());
    }

    @Nullable
    @Override
    public String getWebRepositoryUrlForRevision(CommitFile file, RepositoryData repositoryData) {
        return urlConcat(getWebRepositoryUrl(), "blob/", file.getRevision(), "/", file.getName());
    }

    @Nullable
    @Override
    public String getWebRepositoryUrlForDiff(CommitFile file, RepositoryData repositoryData) {
        // GitLab doesn't currently support per-file diffs, so just link to the commit view
        // http://feedback.gitlab.com/forums/176466-general/suggestions/4300635-option-to-compare-specific-files-directories-in-th
        // TODO submit per-file diff support to gitlab
        return getWebRepositoryUrlForRevision(file.getRevision(), repositoryData);
    }

    @Override
    public Map<Commit, String> getWebRepositoryUrlForCommits(Collection<Commit> commits, RepositoryData repositoryDefinition) {
        Map<Commit, String> urls = Maps.newLinkedHashMap();
        for (Commit commit : commits) {
            urls.put(commit, getWebRepositoryUrlForCommit(commit, repositoryDefinition));
        }
        return urls;
    }

    @Nullable
    @Override
    public String getWebRepositoryUrlForCommit(Commit commit, @NotNull RepositoryData repositoryData) {
        return getWebRepositoryUrlForRevision(commit.getChangeSetId(), repositoryData);
    }

    @Override
    public String getWebRepositoryUrlForRevision(String revisionId, @NotNull RepositoryData repositoryDefinition) {
        return urlConcat(getWebRepositoryUrl(), "commit/", revisionId);
    }

    @NotNull
    @Override
    public Collection<String> getSupportedRepositories() {
        return Collections.singleton(ATLASSIAN_GIT_PLUGIN_KEY);
    }

    private static String urlConcat(String base, String... suffixes) {
        StringBuilder sb = new StringBuilder();
        sb.append(base);
        if (!base.endsWith("/")) {
            sb.append("/");
        }
        for (String suffix : suffixes) {
            sb.append(suffix);
        }
        return sb.toString();
    }
}
