package br.com.release_manager.core.domain;

import br.com.release_manager.core.exception.ReleaseCreateException;
import br.com.release_manager.dependency.dto.ReleaseRequestDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        this.system = systemValidate(system);
        this.version = versionValidate(version);
        this.notes = notes;
        this.commits = commits;
        this.user = userValidate(user);
        this.userUpdate = userUpdateValidate(userUpdate);
        this.releasedAt = LocalDate.now().toString();
        executeTrimOnSystem();
        validateListErrors();
    }

    private void executeTrimOnSystem() {
        if (Objects.nonNull(this.system)) {
            this.system = this.system.trim();
        }
    }

    public Release(Long id, String system, String version, String notes, List<String> commits, String user, String userUpdate, String releasedAt) {
        this.id = id;
        this.system = system;
        this.version = version;
        this.notes = notes;
        this.commits = commits;
        this.user = user;
        this.userUpdate = userUpdate;
        this.releasedAt = releasedAt;
    }

    public static Release create(ReleaseRequestDto releaseRequest, String userUpdate) {
        return new Release(
                releaseRequest.system(),
                releaseRequest.version(),
                releaseRequest.notes(),
                releaseRequest.commits(),
                releaseRequest.user(),
                userUpdate);
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

    public List<String> getErrors() {
        return errors;
    }

    private void validateListErrors() {
        if (!errors.isEmpty()) {
            throw new ReleaseCreateException(errors.toString());
        }
    }

    private String systemValidate(final String system) {
        if (Objects.isNull(system)) {
            errors.add("The 'system' field is required.");
        } else if (system.length() > 255) {
            errors.add("The 'system' field must not exceed 255 characters.");
        }
        return system;
    }

    private String versionValidate(final String version) {
        if (Objects.isNull(version)) {
            errors.add("The 'version' field is required.");
        } else if (version.length() > 30) {
            errors.add("The 'version' field must not exceed 30 characters.");
        }
        return version;
    }

    private String userValidate(final String user) {
        if (Objects.isNull(user) || user.isEmpty()) {
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
