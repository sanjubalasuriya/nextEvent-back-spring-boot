package com.nextevent.service.serviceImpl;

import com.nextevent.dto.paginatedDto.ResponseOrganizerPaginatedDTO;
import com.nextevent.dto.requestDto.RequestUpdateOrganizerDTO;
import com.nextevent.dto.responsetDto.ResponseOrganizerDTO;
import com.nextevent.entity.EventOrganizer;
import com.nextevent.repository.EventOrganizerRepo;
import com.nextevent.service.EventOrganizerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class EventOrganizerServiceImpl implements EventOrganizerService {

    private final EventOrganizerRepo eventOrganizerRepo;
    @Override
    public ResponseOrganizerDTO findByOrganizerId(int organizerId) {
        if (eventOrganizerRepo.existsById(organizerId)){
            EventOrganizer eventOrganizer = eventOrganizerRepo.getReferenceById(organizerId);

            return new ResponseOrganizerDTO(
                    eventOrganizer.getOrganizerId(),
                    eventOrganizer.getMobile(),
                    eventOrganizer.getAddress()
            );
        }else {
            throw new RuntimeException("Not Found");
        }
    }

    @Override
    public String updateOrganizer(RequestUpdateOrganizerDTO requestUpdateOrganizerDTO) {
        if (eventOrganizerRepo.existsById(requestUpdateOrganizerDTO.getOrganizerId())){

            EventOrganizer eventOrganizer = eventOrganizerRepo.getReferenceById(requestUpdateOrganizerDTO.getOrganizerId());
            eventOrganizer.setMobile(requestUpdateOrganizerDTO.getMobile());
            eventOrganizer.setAddress(requestUpdateOrganizerDTO.getAddress());

            eventOrganizerRepo.save(eventOrganizer);
            return eventOrganizer.getOrganizerId()+ " updated";
        }else {
            throw new RuntimeException("Not Found");
        }
    }

    @Override
    public String deleteCustomer(int organizerId) {
        if (eventOrganizerRepo.existsById(organizerId)){
            EventOrganizer eventOrganizer = eventOrganizerRepo.getReferenceById(organizerId);
            eventOrganizerRepo.deleteById(eventOrganizer.getOrganizerId());

            return eventOrganizer.getOrganizerId()+ " Deleted";
        }
        return "Organizer not found";
    }

    @Override
    public List<ResponseOrganizerDTO> getAllOrganizers() {
        List<EventOrganizer> eventOrganizers = eventOrganizerRepo.findAll();

        List<ResponseOrganizerDTO> responseOrganizerDTOS = new ArrayList<>();

        for(EventOrganizer eventOrganizer : eventOrganizers){
            ResponseOrganizerDTO responseOrganizerDTO = new ResponseOrganizerDTO(
                    eventOrganizer.getOrganizerId(),
                    eventOrganizer.getMobile(),
                    eventOrganizer.getAddress()
            );
            responseOrganizerDTOS.add(responseOrganizerDTO);
        }
        return responseOrganizerDTOS;
    }

    @Override
    public ResponseOrganizerPaginatedDTO getAllOrganizersPaginate(int page, int size) {
        Page<EventOrganizer> eventOrganizers = eventOrganizerRepo.findAll(PageRequest.of(page, size));

        List<ResponseOrganizerDTO> responseOrganizerDTOS = new ArrayList<>();

        for(EventOrganizer eventOrganizer : eventOrganizers){
            ResponseOrganizerDTO responseOrganizerDTO = new ResponseOrganizerDTO(
                    eventOrganizer.getOrganizerId(),
                    eventOrganizer.getMobile(),
                    eventOrganizer.getAddress()
            );
            responseOrganizerDTOS.add(responseOrganizerDTO);
        }

        return new ResponseOrganizerPaginatedDTO(
                responseOrganizerDTOS,
                eventOrganizerRepo.count()
        );
    }
}
