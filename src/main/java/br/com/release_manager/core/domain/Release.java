package br.com.release_manager.core.domain;

import java.util.ArrayList;
import java.util.List;

public class Release {
    private Long id;
    private String system;
    private String version;
    private String notes;
    private List<String> commits;
    private String user;
    private String userUpdate;
    private String releasedAt;
    private String deletedAt;
    private List<String> errors = new ArrayList<>();

    public Release(final String system, final String version, final String notes, final List<String> commits, final String user, final String userUpdate) {
        this.system = systemValidate(system).trim();
        this.version = versionValidate(version);
        this.notes = notes;
        this.commits = commits;
        this.user = userValidate(user);
        this.userUpdate = userUpdateValidate(userUpdate);
        validateListErrors();
    }

    public Long getId() {
        return id;
    }

    public String getSystem() {
        return system;
    }

    public String getVersion() {
        return version;
    }

    public String getNotes() {
        return notes;
    }

    public List<String> getCommits() {
        return commits;
    }

    public String getUser() {
        return user;
    }

    public String getUserUpdate() {
        return userUpdate;
    }

    public String getReleasedAt() {
        return releasedAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    private void validateListErrors() {
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors.toString());
        }
    }

    private String systemValidate(final String system) {
        if (system.length() > 255) {
            errors.add("The 'system' field must not exceed 255 characters.");
        } else if (system.isEmpty()) {
            errors.add("The 'system' field is required.");
        }
        return system;
    }

    private String versionValidate(final String version) {
        if (version.length() > 30) {
            errors.add("The 'version' field must not exceed 30 characters.");
        } else if (version.isEmpty()) {
            errors.add("The 'version' field is required.");
        }
        return version;
    }

    private String userValidate(final String user) {
        if (user.isEmpty()) {
            errors.add("The 'user' field is required.");
        }
        return user;
    }

    //TODO lembrar de extrair do token
    private String userUpdateValidate(final String userUpdate) {
        if (userUpdate.isEmpty()) {
            errors.add("The 'userUpdate' field is required.");
        }
        return userUpdate;
    }
}
