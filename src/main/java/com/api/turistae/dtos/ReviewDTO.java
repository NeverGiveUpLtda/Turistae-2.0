package com.api.turistae.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {

     //  Atributos
     private long id;
     private String texto;
     private byte nota;
 
     //  Relacionamentos
     private long turismoId;
     private long usuarioId;
 
     //  Timestamps 
     private LocalDateTime dataCriacao;
     private LocalDateTime dataEdicao;
     
}
