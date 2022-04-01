package com.backbase.movie.bestpicture.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/27/2022
 */
@Data
@Entity
@Table(name = "best_picture_nominee")
@NoArgsConstructor
@AllArgsConstructor
public class BestPictureNominee {

    @Id
    private String id;
    private String title;
    private String year;
    @Column(name = "additional_info")
    private String additionalInfo;
    private boolean won;
}
