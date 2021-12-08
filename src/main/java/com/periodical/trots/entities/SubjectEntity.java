package com.periodical.trots.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * The type Subject entity.
 */
@Table(name = "subject")
@Entity
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "themes_of_books", nullable = false)
    private String themesOfBooks;

    /**
     * The Periodical has subject.
     */
    @OneToMany(mappedBy = "subject")
    Set<PeriodicalHasSubjectEntity> periodicalHasSubject;

    /**
     * Instantiates a new Subject entity.
     *
     * @param id the id
     */
    public SubjectEntity(Integer id) {
        this.id = id;
    }

    /**
     * Instantiates a new Subject entity.
     */
    public SubjectEntity() {
    }

    /**
     * Gets periodical has subject.
     *
     * @return the periodical has subject
     */
    public Set<PeriodicalHasSubjectEntity> getPeriodicalHasSubject() {
        return periodicalHasSubject;
    }

    /**
     * Sets periodical has subject.
     *
     * @param periodicalHasSubject the periodical has subject
     */
    public void setPeriodicalHasSubject(Set<PeriodicalHasSubjectEntity> periodicalHasSubject) {
        this.periodicalHasSubject = periodicalHasSubject;
    }

    /**
     * Gets themes of books.
     *
     * @return the themes of books
     */
    public String getThemesOfBooks() {
        return themesOfBooks;
    }

    /**
     * Sets themes of books.
     *
     * @param themesOfBooks the themes of books
     */
    public void setThemesOfBooks(String themesOfBooks) {
        this.themesOfBooks = themesOfBooks;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }
}