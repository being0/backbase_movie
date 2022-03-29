package com.backbase.movie.rate.service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.backbase.movie.rate.service.event.RateEvent.Type.create;
import static com.backbase.movie.rate.service.event.RateEvent.Type.delete;
import static com.backbase.movie.rate.service.event.RateEvent.Type.update;

/**
 * For simplicity and easier serialization/deserialization, instead of a hierarchy of objects use type
 *
 * @author <a href="mailto:raliakbari@gmail.com">Reza Aliakbari</a>
 * @version 1, 03/29/2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateEvent {

    private String userId;
    private String movieId;
    private Byte rate;
    private Byte prevRate;
    private Type type;

    public static RateEvent createEvent(String userId, String movieId, Byte rate) {

        return new RateEvent(userId, movieId, rate, null, create);
    }

    public static RateEvent updateEvent(String userId, String movieId, Byte rate, Byte oldRate) {

        return new RateEvent(userId, movieId, rate, oldRate, update);
    }

    public static RateEvent deleteEvent(String userId, String movieId, Byte oldRate) {

        return new RateEvent(userId, movieId, null, oldRate, delete);
    }

    public enum Type {
        create, update, delete
    }
}
