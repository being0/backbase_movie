package com.backbase.movie.toprated.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/29/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionResult<T> {

    private List<T> result;
}
