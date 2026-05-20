package az.edu.ada.wm2.courseservice.controller;

import az.edu.ada.wm2.courseservice.model.dto.CourseRequestDto;
import az.edu.ada.wm2.courseservice.model.dto.CourseResponseDto;
import az.edu.ada.wm2.courseservice.model.dto.CourseStudentsResponseDto;
import az.edu.ada.wm2.courseservice.model.dto.EnrollmentResponseDto;
import az.edu.ada.wm2.courseservice.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
@Tag(name = "Kurslar", description = "Kurs və qeydiyyat endpointləri")
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @Operation(summary = "Kurs yarat", description = "Yeni kurs yaradır.")
    public ResponseEntity<CourseResponseDto> createCourse(
            @Valid @RequestBody CourseRequestDto requestDto) {

        CourseResponseDto createdCourse =
                courseService.createCourse(requestDto);

        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(
            summary = "Bütün kursları gətir",
            description = "Bütün kursları qaytarır."
    )
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {

        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "ID-yə görə kurs gətir",
            description = "ID-yə görə bir kurs qaytarır."
    )
    public ResponseEntity<CourseResponseDto> getCourseById(
            @PathVariable Long id) {

        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping("/student-name")
    @Operation(
            summary = "Ada görə kursları gətir",
            description = "Tələbə adına görə kursları qaytarır."
    )
    public ResponseEntity<List<CourseResponseDto>> getCoursesByStudentName(
            @RequestParam String name) {

        return ResponseEntity.ok(
                courseService.getCoursesByStudentName(name)
        );
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Kursu yenilə",
            description = "ID-yə görə kursu yeniləyir."
    )
    public ResponseEntity<CourseResponseDto> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequestDto requestDto) {

        return ResponseEntity.ok(
                courseService.updateCourse(id, requestDto)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Kursu sil",
            description = "ID-yə görə kursu silir."
    )
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {

        courseService.deleteCourse(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{courseId}/students/{studentId}")
    @Operation(
            summary = "Tələbəni kursa yaz",
            description = "Tələbəni kursa qeydiyyatdan keçirir."
    )
    public ResponseEntity<EnrollmentResponseDto> enrollStudent(
            @PathVariable Long courseId,
            @PathVariable Long studentId) {

        EnrollmentResponseDto responseDto =
                courseService.enrollStudent(courseId, studentId);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{courseId}/students")
    @Operation(
            summary = "Kurs tələbələrini gətir",
            description = "Kursa aid tələbələri qaytarır."
    )
    public ResponseEntity<CourseStudentsResponseDto> getCourseStudents(
            @PathVariable Long courseId) {

        return ResponseEntity.ok(
                courseService.getCourseStudents(courseId)
        );
    }
}