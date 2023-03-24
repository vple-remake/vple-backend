package com.app.vple.domain.dto;

import com.app.vple.domain.Evaluation;
import lombok.Data;

@Data
public class EvaluationDto {

    private Long id;

    private Integer delicious;

    private Integer fresh;

    private Integer kind;

    public EvaluationDto(Evaluation entity) {
        this.id = entity.getId();
        this.delicious = entity.getDelicious();
        this.fresh = entity.getFresh();
        this.kind = entity.getKind();
    }
}
