package com.sophra.unistone.DTO;

import com.sophra.unistone.Entity.ScrapChat;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ScrapDTO {

    private String ScrapName;

    private Long projectId;

    private List<Long> chatIds;

}
