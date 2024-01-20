package com.nextevent.controller;

import com.nextevent.dto.paginatedDto.ResponseCategoryPaginatedDTO;
import com.nextevent.dto.paginatedDto.ResponseCustomerPaginatedDTO;
import com.nextevent.dto.requestDto.RequestCustomerRegisterDTO;
import com.nextevent.dto.requestDto.RequestTicketCategoryDTO;
import com.nextevent.dto.requestDto.RequestUpdateCategoryDTO;
import com.nextevent.dto.requestDto.RequestUpdateCustomerDTO;
import com.nextevent.dto.responsetDto.ResponseCustomerDTO;
import com.nextevent.dto.responsetDto.ResponseTicketCategoryDTO;
import com.nextevent.service.TicketCategoryService;
import com.nextevent.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/ticket-category")
@CrossOrigin
public class TicketCategoryController {

    private final TicketCategoryService ticketCategoryService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveCategory(@RequestBody RequestTicketCategoryDTO requestTicketCategoryDTO) {

        String message = ticketCategoryService.saveCategory(requestTicketCategoryDTO);

        return new ResponseEntity<StandardResponse>(new StandardResponse(201, "success", message)
                , HttpStatus.CREATED);
    }

    @GetMapping(
            path = "/get-by-id",
            params = "categoryId"
    )
    public ResponseEntity<StandardResponse> getCategoryById(int categoryId) {

        ResponseTicketCategoryDTO responseTicketCategoryDTO = ticketCategoryService.findByCategoryId(categoryId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", responseTicketCategoryDTO)
                , HttpStatus.OK);
    }

    @GetMapping(
            path = "/get-by-eventId",
            params = "eventId"
    )
    public ResponseEntity<StandardResponse> getCategoryByEventId(int eventId) {

        List<ResponseTicketCategoryDTO> responseTicketCategoryDTO = ticketCategoryService.findCategoryByEventId(eventId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", responseTicketCategoryDTO)
                , HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<StandardResponse> updateCategory(@RequestBody RequestUpdateCategoryDTO requestTicketCategoryDTO) {
        String message = ticketCategoryService.updateCategory(requestTicketCategoryDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", message)
                , HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StandardResponse> deleteCategory(@PathVariable(value = "id") int categoryId) {
        String message = ticketCategoryService.deleteCategory(categoryId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", message)
                , HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<StandardResponse> getAllCategory() {
        List<ResponseTicketCategoryDTO> allCategories = ticketCategoryService.getAllCategory();
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", allCategories)
                , HttpStatus.OK);
    }

    @GetMapping(
            path = "/paginate-all",
            params = {"page", "size"}
    )
    public ResponseEntity<StandardResponse> getAllCategoryPaginate(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        ResponseCategoryPaginatedDTO responseCategoryPaginatedDTO = ticketCategoryService.getAllCategoryPaginate(page, size);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", responseCategoryPaginatedDTO)
                , HttpStatus.OK);
    }
}
