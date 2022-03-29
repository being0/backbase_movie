package com.backbase.movie.toprated.to;

import lombok.Data;

import java.util.List;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/29/2022
 */
@Data
public class CollectionResult<T> {

    private List<T> result;
}
