package com.descenedigital.service;

import com.descenedigital.dto.advice.AdviceRequest;
import com.descenedigital.dto.advice.AdviceResponse;
import com.descenedigital.mapper.AdviceMapper;
import com.descenedigital.model.Advice;
import com.descenedigital.repo.AdviceRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdviceService {

    private final AdviceRepo adviceRepository;
    private final AdviceMapper adviceMapper;

    public Page<AdviceResponse> getAllAdvices(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return adviceRepository.findAll(pageable)
                .map(adviceMapper::toResponse);
    }

    public AdviceResponse getAdviceById(Long id) {
        Advice advice = adviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Advice not found with id: " + id));
        return adviceMapper.toResponse(advice);
    }

    public AdviceResponse createAdvice(AdviceRequest request) {
        Advice advice = adviceMapper.toEntity(request);
        Advice saved = adviceRepository.save(advice);
        return adviceMapper.toResponse(saved);
    }

    public AdviceResponse updateAdvice(Long id, AdviceRequest request) {
        Advice advice = adviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Advice not found with id: " + id));
        advice.setMessage(request.getMessage());
        Advice updated = adviceRepository.save(advice);
        return adviceMapper.toResponse(updated);
    }

    public void deleteAdvice(Long id) {
        if (!adviceRepository.existsById(id)) {
            throw new RuntimeException("Advice not found with id: " + id);
        }
        adviceRepository.deleteById(id);
    }
}
