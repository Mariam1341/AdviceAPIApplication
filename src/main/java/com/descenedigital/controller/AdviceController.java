package com.descenedigital.controller;

import com.descenedigital.dto.advice.AdviceRequest;
import com.descenedigital.dto.advice.AdviceResponse;
import com.descenedigital.service.AdviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/advices")
@RequiredArgsConstructor
public class AdviceController {

    private final AdviceService adviceService;

    @Operation(summary = "Get all advices (paginated)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of advices retrieved"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping
    public ResponseEntity<Page<AdviceResponse>> getAllAdvices(
            @Parameter(description = "Page number, starts from 0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(adviceService.getAllAdvices(page, size));
    }

    @Operation(summary = "Get advice by ID",
            description = "Retrieve a single advice by its ID. Accessible by both USER and ADMIN roles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Advice found and returned"),
            @ApiResponse(responseCode = "404", description = "Advice not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AdviceResponse> getAdviceById(@PathVariable Long id) {
        return ResponseEntity.ok(adviceService.getAdviceById(id));
    }
    @Operation(summary = "Create a new advice",
            description = "Add a new advice. Accessible by USER and ADMIN roles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Advice created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<AdviceResponse> createAdvice(@Valid @RequestBody AdviceRequest request) {
        AdviceResponse response = adviceService.createAdvice(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @Operation(summary = "Update an advice",
            description = "Modify an existing advice by ID. Accessible by USER and ADMIN roles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Advice updated successfully"),
            @ApiResponse(responseCode = "404", description = "Advice not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AdviceResponse> updateAdvice(@PathVariable Long id, @Valid @RequestBody AdviceRequest request) {
        AdviceResponse response = adviceService.updateAdvice(id, request);
        return ResponseEntity.ok(response);
    }
    @Operation(summary = "Delete an advice",
            description = "Remove an advice by ID. Accessible only by ADMIN role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Advice deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Advice not found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAdvice(@PathVariable Long id) {
        adviceService.deleteAdvice(id);
        return ResponseEntity.noContent().build();
    }
}
