function Course(
  fieldOfStudy,
  examNumber,
  description,
  room,
  professor,
  ects,
  kindOfSubject,
  recommendedSemester,
  studyFocus
) {
  this.description = description;
  this.room = room;
  this.professor = professor;
  this.ects = ects;
  this.grade = grade;
  this.fieldOfStudy = fieldOfStudy;
}

export default Course;
