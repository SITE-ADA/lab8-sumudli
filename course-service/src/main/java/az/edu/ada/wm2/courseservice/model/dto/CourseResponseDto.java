package az.edu.ada.wm2.courseservice.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDto {

    @Schema(description = "Kurs ID-si", example = "1")
    private Long id;

    @Schema(description = "Kurs adı", example = "Data Structures")
    private String title;

    @Schema(description = "Kurs kodu", example = "CS201")
    private String code;

    @Schema(description = "Kredit sayı", example = "4")
    private Integer credits;

    @Schema(description = "Prerequisite kurs ID-si", example = "1", nullable = true)
    private Long prerequisiteCourseId;
}