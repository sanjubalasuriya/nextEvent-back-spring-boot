package com.nextevent.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestUpdateEventDTO {

    private int eventId;
    private String eventName;
    private String eventLocation;
    private Date eventDate;
    private Date time;
    private String eventBanner;
}
