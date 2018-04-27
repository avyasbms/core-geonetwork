/*
 * Copyright (C) 2001-2016 Food and Agriculture Organization of the
 * United Nations (FAO-UN), United Nations World Food Programme (WFP)
 * and United Nations Environment Programme (UNEP)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301, USA
 *
 * Contact: Jeroen Ticheler - FAO - Viale delle Terme di Caracalla 2,
 * Rome - Italy. email: geonetwork@osgeo.org
 */

package org.fao.geonet.domain.userfeedback;

import org.fao.geonet.domain.Localized;
import org.fao.geonet.domain.User;
import org.fao.geonet.domain.converter.BooleanToYNConverter;
import org.fao.geonet.entitylistener.RatingCriteriaEntityListenerManager;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Cacheable;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Map;


/**
 * An entity representing rating category class
 */
@Entity
@Table(name = "GUF_RatingCriteria")
@Cacheable
@Access(AccessType.PROPERTY)
@EntityListeners(RatingCriteriaEntityListenerManager.class)
@SequenceGenerator(name = RatingCriteria.ID_SEQ_NAME, initialValue = 100, allocationSize = 1)
public class RatingCriteria extends Localized implements Serializable {
    static final String ID_SEQ_NAME = "rating_criteria_id_seq";

    public static final Integer AVERAGE_ID = -1;

    private int _id;
    private String _name;
    private Boolean _isInternal;

    /**
     * Get the id of the rating criteria.
     * <p>
     * This is autogenerated and when a new rating criteria is created the rating criteria will be
     * assigned a new value.
     *
     * @return the id of the rating criteria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQ_NAME)
    @Column(nullable = false)
    public int getId() {
        return _id;
    }

    /**
     * Get the id of the rating criteria. <p> This is autogenerated and when a new rating criteria is created the rating criteria
     * will be assigned a new value. </p> <p> If you want to update an existing rating criteria then you
     * should set this id to the rating criteria you want to update and set the other values to the desired
     * values </p>
     *
     * @param id the id of the rating criteria.
     * @return this rating criteria object
     */
    public RatingCriteria setId(int id) {
        this._id = id;
        return this;
    }

    /**
     * Get the basic/default name of the rating criteria. This is non-translated and can be used to look up
     * the rating criteria like an id can. <p> This is a required property. <p> There is a max length to the
     * name allowed. See the annotation for the length value </p>
     *
     * @return rating criteria name
     */
    @Column(nullable = false, length = 32)
    public String getName() {
        return _name;
    }

    /**
     * Set the basic/default name of the rating criteria. This is non-translated and can be used to look up
     * the rating criteria like an id can. <p> This is a required property. <p> There is a max length to the
     * name allowed. See the annotation on {@link #getName()} for the length value </p>
     */
    public RatingCriteria setName(String name) {
        this._name = name;
        return this;
    }


    @Column(name = "isinternal", nullable = false, length = 1, columnDefinition="CHAR(1) DEFAULT 'y'")
    @Convert(converter = BooleanToYNConverter.class)
    public boolean isInternal() {
        if (_isInternal == null) {
            this._isInternal = true;
        }
        return _isInternal;
    }

    public RatingCriteria setInternal(Boolean isInternal) {
        this._isInternal = isInternal;
        return this;
    }


    /**
     * The label contains the translation for the rating category and also a description.
     * The separator between the 2 is a "#".
     *
     * eg. Service quality#The dataset is provided as a service or mediatype that is easy to work with?
     *
     * Note: That could be improved in the future to have 2 dedicated fields. Maybe this is something
     * to apply to all translations like category, groups. For example, group concept has a description
     * which is not translated. Category could have an extra description. Less true for languages.
     *
     * @return
     */
    @Override
    @ElementCollection(fetch = FetchType.LAZY, targetClass = String.class)
    @CollectionTable(joinColumns = @JoinColumn(name = "idDes"), name = "GUF_RatingCriteriaDes")
    @MapKeyColumn(name = "langId", length = 5)
    @Column(name = "label", nullable = false, length = 2000)
    public Map<String, String> getLabelTranslations() {
        return super.getLabelTranslations();
    }

    @Override
    public String toString() {
        return "rating criteria [_id=" + _id + ", _name=" + _name + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + _id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RatingCriteria other = (RatingCriteria) obj;
        if (_id != other._id)
            return false;
        return true;
    }
}
