package com.app.vple.service;

import com.app.vple.domain.RecommandRestaurant;
import com.app.vple.domain.dto.MapRestaurantListDto;
import com.app.vple.domain.dto.RecommandRestaurantDetailDto;
import com.app.vple.domain.dto.RecommandRestaurantListDto;
import com.app.vple.repository.RecommandRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RecommandRestaurantService {

    private final RecommandRestaurantRepository recommandRestaurantRepository;

    public Page<RecommandRestaurantListDto> findRecommandRestaurnatList(Pageable pageable) {
        Page<RecommandRestaurant> recommandRestaurants = recommandRestaurantRepository.findAll(pageable);

        return recommandRestaurants.map(RecommandRestaurantListDto::new);
    }

    public RecommandRestaurantDetailDto findRecommandRestaurantDetails(Long id) {
        RecommandRestaurant recommandRestaurant = recommandRestaurantRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("The restaurant does not exist.")
        );

        return new RecommandRestaurantDetailDto(recommandRestaurant);
    }

    public List<MapRestaurantListDto> findMapRestaurant() {
        List<RecommandRestaurant> recommandRestaurants = recommandRestaurantRepository.findAll();

        return recommandRestaurants.stream().map(
                MapRestaurantListDto::new
        ).collect(Collectors.toList());
    }

    public List<MapRestaurantListDto> searchMapRestaurnat(String keyword) {
        List<RecommandRestaurant> recommandRestaurants = recommandRestaurantRepository.findByNameContaining(keyword);
        recommandRestaurants.addAll(recommandRestaurantRepository.findByAddressContaining(keyword));

        if (recommandRestaurants.isEmpty()) {
            throw new NoSuchElementException("?????? ????????? ???????????? ????????????.");
        }

        return recommandRestaurants.stream().map(
                MapRestaurantListDto::new
        ).collect(Collectors.toList());
    }

    public Page<RecommandRestaurantListDto> searchRestaurant(String district, String city, Pageable pageable) {
        if (district.equals("??????") && city.equals("??????")) {
            Page<RecommandRestaurant> recommandRestaurants = recommandRestaurantRepository.findAll(pageable);
            return recommandRestaurants.map(RecommandRestaurantListDto::new);
        }
        else if (city.equals("??????")) {
            Page<RecommandRestaurant> recommandRestaurants = recommandRestaurantRepository.findByDistrict(district, pageable);
            return recommandRestaurants.map(RecommandRestaurantListDto::new);
        }

        Page<RecommandRestaurant> recommandRestaurants  = recommandRestaurantRepository.findByDistrictAndCity(district, city, pageable);
        return recommandRestaurants.map(RecommandRestaurantListDto::new);
    }
}
