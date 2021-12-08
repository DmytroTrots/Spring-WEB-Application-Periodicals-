package com.periodical.trots.entities;

import javax.persistence.*;

/**
 * The type Periodical has subject entity.
 */
@Table(name = "periodical_has_subject", indexes = {@Index(name = "fk_periodical_has_subject_subject1_idx", columnList = "subject_id"), @Index(name = "fk_periodical_has_subject_periodical_idx", columnList = "periodical_id")})
@Entity
public class PeriodicalHasSubjectEntity {
    @EmbeddedId
    private PeriodicalHasSubjectEntityId id;

    /**
     * The Subject.
     */
    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "subject_id")
    SubjectEntity subject;

    /**
     * The Periodical.
     */
    @ManyToOne
    @MapsId("periodicalId")
    @JoinColumn(name = "periodical_id")
    PeriodicalEntity periodical;

    /**
     * Gets subject.
     *
     * @return the subject
     */
    public SubjectEntity getSubject() {
        return subject;
    }

    /**
     * Sets subject.
     *
     * @param subject the subject
     */
    public void setSubject(SubjectEntity subject) {
        this.subject = subject;
    }

    /**
     * Gets periodical.
     *
     * @return the periodical
     */
    public PeriodicalEntity getPeriodical() {
        return periodical;
    }

    /**
     * Sets periodical.
     *
     * @param periodical the periodical
     */
    public void setPeriodical(PeriodicalEntity periodical) {
        this.periodical = periodical;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public PeriodicalHasSubjectEntityId getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(PeriodicalHasSubjectEntityId id) {
        this.id = id;
    }
}