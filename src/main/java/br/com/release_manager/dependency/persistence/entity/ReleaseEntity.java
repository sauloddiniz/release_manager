package br.com.release_manager.dependency.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "RELEASE", schema = "MANAGER")
public class ReleaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SYSTEM", nullable = false)
    private String system;

    @Column(name = "VERSION", nullable = false)
    private String version;

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "COMMITS")
    private List<String> commits;

    @Column(name = "\"USER\"")
    private String user;

    @Column(name = "USER_UPDATE", nullable = false)
    private String userUpdate;

    @Column(name = "RELEASED_AT")
    private String releasedAt;

    @Column(name = "DELETED_AT")
    private String deletedAt;

    public ReleaseEntity() {
    }

    public ReleaseEntity(final String system, final String version, final String notes, final List<String> commits, final String user, final String userUpdate, String releasedAt) {
        this.system = system;
        this.version = version;
        this.notes = notes;
        this.commits = commits;
        this.user = user;
        this.userUpdate = userUpdate;
        this.releasedAt = releasedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<String> getCommits() {
        return commits;
    }

    public void setCommits(List<String> commits) {
        this.commits = commits;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate;
    }

    public String getReleasedAt() {
        return releasedAt;
    }

    public void setReleasedAt(String releasedAt) {
        this.releasedAt = releasedAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }
}