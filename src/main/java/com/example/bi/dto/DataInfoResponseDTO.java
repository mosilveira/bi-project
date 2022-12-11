package com.example.bi.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataInfoResponseDTO {

    private Long patientId;

    private String name;

    private String gender;

    private String level;

    private String microArea;

    private Integer careInfo;

    private Integer pa;

    private Integer uniqueCareInfo;

    private Integer uniquePa;

    private Integer paOrCareInfo;

}
