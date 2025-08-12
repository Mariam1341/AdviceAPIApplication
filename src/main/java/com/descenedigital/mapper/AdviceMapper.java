package com.descenedigital.mapper;

import com.descenedigital.dto.advice.AdviceRequest;
import com.descenedigital.dto.advice.AdviceResponse;
import com.descenedigital.model.Advice;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdviceMapper {

    AdviceResponse toResponse(Advice advice);

    Advice toEntity(AdviceRequest request);

    List<AdviceResponse> toResponseList(List<Advice> advices);
}
