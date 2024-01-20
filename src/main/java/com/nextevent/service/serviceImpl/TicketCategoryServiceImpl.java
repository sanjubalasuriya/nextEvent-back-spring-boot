package com.nextevent.service.serviceImpl;

import com.nextevent.dto.paginatedDto.ResponseCategoryPaginatedDTO;
import com.nextevent.dto.requestDto.RequestTicketCategoryDTO;
import com.nextevent.dto.requestDto.RequestUpdateCategoryDTO;
import com.nextevent.dto.responsetDto.ResponseTicketCategoryDTO;
import com.nextevent.entity.Event;
import com.nextevent.entity.TicketCategory;
import com.nextevent.repository.EventRepo;
import com.nextevent.repository.TicketCategoryRepo;
import com.nextevent.service.TicketCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class TicketCategoryServiceImpl implements TicketCategoryService {

    private final TicketCategoryRepo ticketCategoryRepo;
    private final EventRepo eventRepo;
    @Override
    public String saveCategory(RequestTicketCategoryDTO requestTicketCategoryDTO) {

        if (eventRepo.existsById(requestTicketCategoryDTO.getEvent())) {
            Event event = eventRepo.getReferenceById(requestTicketCategoryDTO.getEvent());

            TicketCategory ticketCategory = new TicketCategory(
                    requestTicketCategoryDTO.getCategory(),
                    requestTicketCategoryDTO.getTicketPrice(),
                    requestTicketCategoryDTO.getTicketCount(),
                    event
            );

            ticketCategoryRepo.save(ticketCategory);
            return ticketCategory.getCategory() + " Saved";
        }
        else {
            throw new RuntimeException("Not Found");
        }
    }

    @Override
    public ResponseTicketCategoryDTO findByCategoryId(int categoryId) {
        if (ticketCategoryRepo.existsById(categoryId)){
            TicketCategory ticketCategory = ticketCategoryRepo.getReferenceById(categoryId);

            return new ResponseTicketCategoryDTO(
                    ticketCategory.getTicketCategoryId(),
                    ticketCategory.getCategory(),
                    ticketCategory.getTicketPrice(),
                    ticketCategory.getTicketCount()
            );
        }else {
            throw new RuntimeException("Not Found");
        }
    }

    @Override
    public String updateCategory(RequestUpdateCategoryDTO requestUpdateCategoryDTO) {
        if (ticketCategoryRepo.existsById(requestUpdateCategoryDTO.getCategoryId())) {
            TicketCategory ticketCategory = ticketCategoryRepo.getReferenceById(requestUpdateCategoryDTO.getCategoryId());


            ticketCategory.setCategory(requestUpdateCategoryDTO.getCategory());
            ticketCategory.setTicketPrice(requestUpdateCategoryDTO.getTicketPrice());
            ticketCategory.setTicketCount(requestUpdateCategoryDTO.getTicketCount());


            ticketCategoryRepo.save(ticketCategory);
            return ticketCategory.getCategory() + " Updated";
        }
        else {
            throw new RuntimeException("Not Found");
        }
    }

    @Override
    public String deleteCategory(int categoryId) {
        if (ticketCategoryRepo.existsById(categoryId)){
            TicketCategory ticketCategory = ticketCategoryRepo.getReferenceById(categoryId);
            ticketCategoryRepo.deleteById(ticketCategory.getTicketCategoryId());

            return ticketCategory.getCategory()+ " Deleted";
        }
        return "Organizer not found";
    }

    @Override
    public List<ResponseTicketCategoryDTO> getAllCategory() {
        List<TicketCategory> ticketCategoryList = ticketCategoryRepo.findAll();

        List<ResponseTicketCategoryDTO> responseTicketCategoryDTOS = new ArrayList<>();

        for(TicketCategory ticketCategory : ticketCategoryList){
            ResponseTicketCategoryDTO responseTicketCategoryDTO = new ResponseTicketCategoryDTO(
                    ticketCategory.getTicketCategoryId(),
                    ticketCategory.getCategory(),
                    ticketCategory.getTicketPrice(),
                    ticketCategory.getTicketCount()
            );
            responseTicketCategoryDTOS.add(responseTicketCategoryDTO);
        }
        return responseTicketCategoryDTOS;
    }

    @Override
    public ResponseCategoryPaginatedDTO getAllCategoryPaginate(int page, int size) {
        Page<TicketCategory> ticketCategoryList = ticketCategoryRepo.findAll(PageRequest.of(page, size));

        List<ResponseTicketCategoryDTO> responseTicketCategoryDTOS = new ArrayList<>();

        for(TicketCategory ticketCategory : ticketCategoryList){
            ResponseTicketCategoryDTO responseTicketCategoryDTO = new ResponseTicketCategoryDTO(
                    ticketCategory.getTicketCategoryId(),
                    ticketCategory.getCategory(),
                    ticketCategory.getTicketPrice(),
                    ticketCategory.getTicketCount()
            );
            responseTicketCategoryDTOS.add(responseTicketCategoryDTO);
        }

        return new ResponseCategoryPaginatedDTO(
                responseTicketCategoryDTOS,
                ticketCategoryRepo.count()
        );
    }

    @Override
    public List<ResponseTicketCategoryDTO> findCategoryByEventId(int eventId) {

        Event event = eventRepo.getReferenceById(eventId);

        List<TicketCategory> ticketCategoryList = ticketCategoryRepo.findAllByEvent(event);

        List<ResponseTicketCategoryDTO> responseTicketCategoryDTOS = new ArrayList<>();

        for(TicketCategory ticketCategory : ticketCategoryList){
            ResponseTicketCategoryDTO responseTicketCategoryDTO = new ResponseTicketCategoryDTO(
                    ticketCategory.getTicketCategoryId(),
                    ticketCategory.getCategory(),
                    ticketCategory.getTicketPrice(),
                    ticketCategory.getTicketCount()
            );
            responseTicketCategoryDTOS.add(responseTicketCategoryDTO);
        }
        return responseTicketCategoryDTOS;
    }
}
