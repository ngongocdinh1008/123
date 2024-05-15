package com.example.ceramicjbw.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HealthInformationDTO {

    @NotBlank(message = "Yêu cầu nhập Tên!!")
    private String name;

    @NotBlank(message = "Yêu cầu nhập Thông tin!!")
    private String information;


    List<DetailsDTO> details;
}
