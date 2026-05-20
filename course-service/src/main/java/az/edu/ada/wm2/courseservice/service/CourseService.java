public List<CourseResponseDto> getCoursesByStudentName(String name) {

    StudentDto student = studentFeignClient.getStudentByName(name);

    List<Long> courseIds = enrollmentRepository.findByStudentId(student.getId())
            .stream()
            .map(Enrollment::getCourseId)
            .toList();

    return courseIds.stream()
            .map(this::findCourseOrThrow)
            .map(this::toCourseResponseDto)
            .toList();
}