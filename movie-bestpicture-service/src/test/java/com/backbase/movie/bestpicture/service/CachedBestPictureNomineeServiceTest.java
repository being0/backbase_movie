package com.backbase.movie.bestpicture.service;

import com.backbase.movie.bestpicture.service.mapper.BestPictureMapper;
import com.backbase.movie.bestpicture.service.model.BestPictureNominee;
import com.backbase.movie.bestpicture.service.repository.BestPictureNomineeRepository;
import com.backbase.movie.bestpicture.to.BestPictureNomineeTo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CachedBestPictureNomineeServiceTest {

    @Mock
    BestPictureNomineeRepository bestPictureNomineeRepository;
    @Spy
    BestPictureMapper bestPictureMapper = new BestPictureMapper();

    @InjectMocks
    CachedBestPictureNomineeService cachedBestPictureNomineeService;

    @Test
    void testGetBestPictureMovieByTitle() throws MovieNotFoundException {
        // Given
        var nominee1 = new BestPictureNominee("123", "12 Angry men", "1957", "good to see", true);
        var nominee2 = new BestPictureNominee("456", "American Beauty", "1999", "Not an American dream!", false);
        when(bestPictureNomineeRepository.findAll()).thenReturn(List.of(nominee1, nominee2));
        cachedBestPictureNomineeService.updateCache();

        // When
        BestPictureNomineeTo nomineeTo = cachedBestPictureNomineeService.getBestPictureMovieByTitle("12 Angry men");

        // Then
        assertEquals(new BestPictureNomineeTo("123", "12 Angry men", "1957", "good to see", true), nomineeTo);
    }

    @Test
    void testGetBestPictureMovieByTitle_MovieNotFoundException(){
        // Given
        var nominee1 = new BestPictureNominee("123", "12 Angry men", "1957", "good to see", true);
        var nominee2 = new BestPictureNominee("456", "American Beauty", "1999", "Not an American dream!", false);
        when(bestPictureNomineeRepository.findAll()).thenReturn(List.of(nominee1, nominee2));
        cachedBestPictureNomineeService.updateCache();

        // When/Then
        assertThrows(MovieNotFoundException.class, () -> cachedBestPictureNomineeService.getBestPictureMovieByTitle("Unknown Movie!"));
    }

}