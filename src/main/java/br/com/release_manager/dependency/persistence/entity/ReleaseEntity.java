package br.com.release_manager.dependency.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDateTime releasedAt;

    @Column(name = "DELETED_AT")
    private LocalDateTime deletedAt;

    public ReleaseEntity() {
    }

    public ReleaseEntity(Long id, final String system, final String version, final String notes, final List<String> commits, final String user, final String userUpdate, LocalDateTime releasedAt, LocalDateTime deletedAt) {
        this.id = id;
        this.system = system;
        this.version = version;
        this.notes = notes;
        this.commits = commits;
        this.user = user;
        this.userUpdate = userUpdate;
        this.releasedAt = releasedAt;
        this.deletedAt = deletedAt;
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

    public LocalDateTime getReleasedAt() {
        return releasedAt;
    }

    public void setReleasedAt(LocalDateTime releasedAt) {
        this.releasedAt = releasedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}